package stream;

import org.junit.Assert;
import org.junit.Test;
import pojo.Order;
import pojo.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static pojo.Order.OrderStatus.*;

public class DistinctTest {
    @Test
    public void distinctTest() {
        List<Integer> numberList = Arrays.asList(-1, 3, -1, 5, 5, 8, 10 ,8);
        List<Integer> disctinctNumberList = numberList.stream()
                .distinct()
                .collect(Collectors.toList());

        Assert.assertEquals(Integer.valueOf(-1), disctinctNumberList.get(0));

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setOrderId(2022070101)
                .setOrderStatus(CREATED)
                .setUserId(1000)
                .setCreatedAt(now.minusMinutes(15));

        Order order2 = new Order()
                .setOrderId(2022070102)
                .setOrderStatus(PROCESSED)
                .setUserId(1000)
                .setCreatedAt(now.minusMinutes(10));

        Order order3 = new Order()
                .setOrderId(2022070103)
                .setOrderStatus(IN_PROGRESS)
                .setUserId(1001)
                .setCreatedAt(now.minusMinutes(8));

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

        List<Long> disctinctCreateduserId = orderList.stream()
                .map(Order::getUserId)
                .distinct()
                .collect(Collectors.toList());

        Assert.assertEquals(Long.valueOf(1000), disctinctCreateduserId.get(0));
    }
}
