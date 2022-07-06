package moreStream;

import org.junit.Assert;
import org.junit.Test;
import pojo.Order;
import pojo.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static pojo.Order.OrderStatus.*;
import static pojo.Order.OrderStatus.ERROR;

public class AllMatchAnyMatchTest {
    @Test
    public void allMatchTest() {
        Stream<Integer> intStream = Stream.of(1,2,-4,5,6);
        boolean allPositive = intStream
                .allMatch(x -> x>0);

        Assert.assertFalse(allPositive);

        User user1 = new User()
                .setName("John")
                .setId(1000)
                .setVerified(true)
                .setEmailAddress("johnKim@google.com");

        User user2 = new User()
                .setName("Tom")
                .setId(1001)
                .setVerified(false)
                .setEmailAddress("tomLee@google.com");

        User user3 = new User()
                .setName("Jerry")
                .setId(1002)
                .setVerified(true)
                .setEmailAddress("jerryOh@google.com");

        List<User> userList = Arrays.asList(user1, user2, user3);
        boolean allVerified = userList.stream()
                .allMatch(u -> u.isVerified() == true);

        Assert.assertFalse(allVerified);


    }

    @Test
    public void anyMatchTest() {
        Stream<Integer> intStream = Stream.of(1,2,-4,5,6);
        boolean anyNegativeNumber = intStream
                .anyMatch(x -> x < 0);

        Assert.assertTrue(anyNegativeNumber);

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
                .setOrderStatus(ERROR)
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

        Order order6 = new Order()
                .setOrderId(2022070106)
                .setOrderStatus(ERROR)
                .setUserId(1002)
                .setCreatedAt(now.minusMinutes(5));

        List<Order> orderList = Arrays.asList(order1, order2, order3, order4, order5, order6);

        boolean anyErrorOrder = orderList.stream()
                .anyMatch(od -> ERROR.equals(od.getOrderStatus()));

        Assert.assertTrue(anyErrorOrder);
    }
}
