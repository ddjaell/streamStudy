package designPattern;

import org.junit.Test;
import pojo.Order;
import pojo.OrderDetail;
import service.OrderProcessStep;

import java.math.BigDecimal;
import java.util.Arrays;

import static pojo.Order.OrderStatus.CREATED;

public class ChainOfResponsibilityPatternTest {
    @Test
    public void chainOfResponsibilityPatternTestWithOrder() {
        OrderProcessStep initializeStep = new OrderProcessStep(order -> {

            if(Order.OrderStatus.CREATED.equals(order.getOrderStatus())){
                System.out.println("order " + order.getOrderId() + " set status to in_progress");
                order.setOrderStatus(Order.OrderStatus.IN_PROGRESS);
            }
        });

        OrderProcessStep setAmountStep = new OrderProcessStep(order -> {

            if(Order.OrderStatus.IN_PROGRESS.equals(order.getOrderStatus())){
                System.out.println("order " + order.getOrderId() + " set amount");
                order.setAmount(order.getOrderDetailList().stream().map(OrderDetail::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add));
            }
        });

        OrderProcessStep verifyStep = new OrderProcessStep(order -> {

            if(Order.OrderStatus.IN_PROGRESS.equals(order.getOrderStatus())){
                System.out.println("checking order details' amount, order " + order.getOrderId());
                if(order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    order.setOrderStatus(Order.OrderStatus.ERROR);
                    System.out.println("verifying order failed, check your order");
                }
            }
        });

        OrderProcessStep errorHandler = new OrderProcessStep(order -> {

            if(Order.OrderStatus.ERROR.equals(order.getOrderStatus())){
                System.out.println("order "+ order.getOrderId() + " failed");
            }
        });

        OrderProcessStep completeOrderStep = new OrderProcessStep(order -> {

           if(Order.OrderStatus.IN_PROGRESS.equals(order.getOrderStatus())){
               System.out.println("payment started");
               order.setOrderStatus(Order.OrderStatus.PROCESSED);
           }
        });

        OrderProcessStep chainedOrderProcess = initializeStep
                .setNextStep(setAmountStep)
                .setNextStep(verifyStep)
                .setNextStep(errorHandler)
                .setNextStep(completeOrderStep);

        Order order1 = new Order()
                .setOrderId(2022070101)
                .setOrderStatus(CREATED)
                .setUserId(1000)
                .setOrderDetailList(Arrays.asList(
                        new OrderDetail()
                                .setId(10)
                                .setAmount(BigDecimal.valueOf(10000)),
                        new OrderDetail()
                                .setId(20)
                                .setAmount(BigDecimal.valueOf(2000))
                ));
        //chainedOrderProcess.process(order1);

        Order order2 = new Order()
                .setOrderId(2022070101)
                .setOrderStatus(CREATED)
                .setUserId(1000)
                .setOrderDetailList(Arrays.asList(
                        new OrderDetail()
                                .setId(10)
                                .setAmount(BigDecimal.valueOf(-10000)),
                        new OrderDetail()
                                .setId(20)
                                .setAmount(BigDecimal.valueOf(2000))
                ));
        chainedOrderProcess.process(order2);
    }
}
