package moreStream;

import org.junit.Assert;
import org.junit.Test;
import pojo.Order;
import pojo.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static pojo.Order.OrderStatus.*;

public class MixMinCountTest {

    @Test
    public void mixTest() {
        Optional<Integer> optionalMaxInt = Stream.of(1,3,4,5,5)
                .max(Integer::compareTo);

        Assert.assertEquals(Integer.valueOf(5), optionalMaxInt.get());
    }

    @Test
    public void minTest() {
        User user1 = new User()
                .setName("John")
                .setId(1000)
                .setVerified(true)
                .setEmailAddress("johnKim@google.com");

        User user2 = new User()
                .setId(1001)
                .setVerified(false)
                .setEmailAddress("tomLee@google.com");

        User user3 = new User()
                .setName("Jerry")
                .setId(1002)
                .setVerified(true)
                .setEmailAddress("jerryOh@google.com");

        Stream<User> userStream = Stream.of(user1, user2, user3);
        String firstUserName = userStream
                .filter(user -> user.getName() != null)
                .min((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .get().getName();
        Assert.assertEquals("Jerry", firstUserName);

    }

    @Test
    public void countTest(){
        Stream<Integer> integerStream = Stream.of(1,2,-5,-3,-27);
        Long positivieNumberCount = integerStream.filter(x -> x > 0)
                .count();
        Assert.assertEquals(Long.valueOf(2), positivieNumberCount);
    }

    @Test
    public void maxMinCountTestWithObject() {
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
        Long errorOrderIn10Min = orderList.stream()
                .filter(order -> ERROR.equals(order.getOrderStatus()))
                .filter(order -> order.getCreatedAt().isAfter(now.minusMinutes(10)))
                .count();

        Assert.assertEquals(Long.valueOf(2), errorOrderIn10Min);
    }
}
