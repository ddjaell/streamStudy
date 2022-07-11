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
import java.util.Set;
import java.util.stream.Collectors;

import static pojo.Order.OrderStatus.*;

public class GroupByTest {
    @Test
    public void groupByTest() {
        List<Integer> integers = Arrays.asList(1,2,5,7,8,10,22,2);

        Map<Integer, List<Integer>> integerListMap = integers.stream()
                .collect(Collectors.groupingBy(integer -> integer % 3));
        Assert.assertEquals(4, integerListMap.get(2).size());

        Map<Integer, Set<Integer>> integerSetMap = integers.stream()
                .collect(Collectors.groupingBy(integer -> integer%3, Collectors.toSet()));

        Assert.assertEquals(3, integerSetMap.get(2).size());

        Map<Integer, List<String>> integerStringListMap = integers.stream()
                .collect(Collectors.groupingBy(integer -> integer%3, Collectors.mapping(integer -> "the number is " + integer,Collectors.toList())));
        Assert.assertEquals("the number is 1", integerStringListMap.get(1).get(0));
    }

    @Test
    public void groupByTestWithOrders() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setAmount(BigDecimal.valueOf(12000))
                .setOrderId(2022070101)
                .setOrderStatus(PROCESSED)
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
                .setAmount(BigDecimal.valueOf(21500))
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
                .setAmount(BigDecimal.valueOf(300050))
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

        Map<Order.OrderStatus, List<Order>> orderStatusListMap = orderList.stream()
                .collect(Collectors.groupingBy(Order::getOrderStatus));

        Assert.assertEquals(2022070103, orderStatusListMap.get(IN_PROGRESS).get(0).getOrderId());

        Map<Order.OrderStatus, BigDecimal> orderStatusBigDecimalMap = orderList.stream()
                .filter(order -> order.getAmount() != null)
                .collect(Collectors.groupingBy(Order::getOrderStatus,
                        Collectors.mapping(Order::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        Assert.assertEquals(BigDecimal.valueOf(33500), orderStatusBigDecimalMap.get(PROCESSED));

    }
}
