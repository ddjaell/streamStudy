package moreStream;

import org.junit.Assert;
import org.junit.Test;
import pojo.Order;
import pojo.OrderDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pojo.Order.OrderStatus.*;

public class ToMapTest {
    @Test
    public void toMapTest(){
        Stream<Integer> integerStream = Stream.of(1,2,3);
        Map<Integer, String> keyIntegerValueStringMap = integerStream
                .collect(Collectors.toMap(x -> x, x -> "The Number is " + x));
        Assert.assertEquals("The Number is 1", keyIntegerValueStringMap.get(1));

        Stream<Integer> integerStream2 = Stream.of(1,2,3);
        Map<Integer, String> keyIntegerValueStringMap2 = integerStream2
                .collect(Collectors.toMap(Function.identity(),x -> "The Number is " + x));
        Assert.assertEquals("The Number is 1", keyIntegerValueStringMap.get(1));
    }

    @Test
    public void toMapTestWithOrders() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setOrderId(2022070101)
                .setOrderStatus(CREATED)
                .setUserId(1000)
                .setCreatedAt(now.minusMinutes(15))
                .setOrderDetailList(Arrays.asList(
                        new OrderDetail()
                                .setId(10)
                                .setAmount(BigDecimal.valueOf(10000)),
                        new OrderDetail()
                                .setId(20)
                                .setAmount(BigDecimal.valueOf(2000))
                ));

        Order order2 = new Order()
                .setOrderId(2022070102)
                .setOrderStatus(PROCESSED)
                .setUserId(1000)
                .setCreatedAt(now.minusMinutes(10))
                .setOrderDetailList(Arrays.asList(
                        new OrderDetail()
                                .setId(30)
                                .setAmount(BigDecimal.valueOf(1000)),
                        new OrderDetail()
                                .setId(40)
                                .setAmount(BigDecimal.valueOf(20500))
                ));

        Order order3 = new Order()
                .setOrderId(2022070103)
                .setOrderStatus(IN_PROGRESS)
                .setUserId(1001)
                .setCreatedAt(now.minusMinutes(8))
                .setOrderDetailList(Arrays.asList(
                        new OrderDetail()
                                .setId(50)
                                .setAmount(BigDecimal.valueOf(100000)),
                        new OrderDetail()
                                .setId(60)
                                .setAmount(BigDecimal.valueOf(200050))
                ));

        Order order4 = new Order()
                .setOrderId(2022070104)
                .setOrderStatus(ERROR)
                .setUserId(1002)
                .setCreatedAt(now.minusMinutes(85));

        Order order5 = new Order()
                .setOrderId(2022070105)
                .setOrderStatus(PROCESSED)
                .setUserId(1002)
                .setCreatedAt(now.minusMinutes(100));

        List<Order> orderList = Arrays.asList(order1, order2, order3, order4, order5);

        Map<Long, Order> orderMap = orderList.stream()
                .collect(Collectors.toMap(Order::getOrderId, Function.identity()));

        Assert.assertEquals(orderMap.get(2022070104L), order4);

        Map<Long, Order.OrderStatus> keyOrderIdValueOrderStatusMap= orderList.stream()
                .collect(Collectors.toMap(Order::getOrderId, order -> order.getOrderStatus()));
        Assert.assertEquals(keyOrderIdValueOrderStatusMap.get(2022070104L), ERROR);
    }
}
