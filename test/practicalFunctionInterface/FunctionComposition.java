package practicalFunctionInterface;

import org.junit.Assert;
import org.junit.Test;
import pojo.Order;
import pojo.OrderDetail;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionComposition {
    @Test
    public void andThenTest() {
        Function<Integer, Integer> addTen = x -> x + 10;
        Function<Integer, Integer> divideByTwo = x -> x / 2;

        Function<Integer, Integer> addTenThenDivideByTwo = addTen.andThen(divideByTwo);
        Assert.assertEquals(Integer.valueOf(6), addTenThenDivideByTwo.apply(2));
    }

    @Test
    public void andThenTestWithOrder() {
        Order unProcessedOrder = new Order()
                .setOrderId(1001)
                .setOrderStatus(Order.OrderStatus.IN_PROGRESS)
                .setOrderDetailList(Arrays.asList(
                        new OrderDetail().setAmount(BigDecimal.valueOf(1000)),
                        new OrderDetail().setAmount(BigDecimal.valueOf(2000))
                ));

        Function<Order, Order> getOrderAmount = x -> {
            return x.setAmount(x.getOrderDetailList().stream()
                            .map(OrderDetail::getAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
        };

        Function<Order,Order> addTax = x -> {
            return x.setAmount(x.getAmount().multiply(BigDecimal.valueOf(1.1)));
        };

        Function<Order,Order> changeOrderStatus = x -> {
            return x.setOrderStatus(Order.OrderStatus.PROCESSED);
        };


        List<Function<Order, Order>> orderProcessingFunctionList = Arrays.asList(getOrderAmount, addTax, changeOrderStatus);
        Function<Order, Order> orderCompleteFunction = orderProcessingFunctionList.stream()
                .reduce(Function.identity(), Function::andThen);

        Order processedOrder = orderCompleteFunction.apply(unProcessedOrder);
        Assert.assertEquals(Order.OrderStatus.PROCESSED, processedOrder.getOrderStatus());

    }

}
