package stream;

import static pojo.Order.OrderStatus.*;
import org.junit.Assert;
import org.junit.Test;
import pojo.Order;
import pojo.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MapTest {

    @Test
    public void mapIntegerTest() {
        List<Integer> integerList = Arrays.asList(1,2,5,-10,20);
        List<Integer> integerListX2 = integerList.stream().map(x -> x*2).collect(Collectors.toList());
        Assert.assertEquals(Integer.valueOf(2), integerListX2.get(0));

        List<String> integerStringList = integerList.stream().map(x -> "This Number is " + x).collect(Collectors.toList());
        Assert.assertEquals("This Number is 1", integerStringList.get(0));

    }

    @Test
    public void userMapTest() {
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
        List<String> userEmailList = userList.stream().map(user -> user.getEmailAddress().orElse("no email")).collect(Collectors.toList());

        Assert.assertEquals("johnKim@google.com", userEmailList.get(0));
    }

    @Test
    public void orderMapTest() {
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

        List<Long> orderUserId = orderList.stream().map(Order::getUserId).collect(Collectors.toList());

        List<Long> errorOrderCreatedUserIdList = orderList.stream()
                .filter(order -> ERROR.equals(order.getOrderStatus()))
                .map(Order::getUserId)
                .collect(Collectors.toList());
        Assert.assertEquals(Long.valueOf(1002), errorOrderCreatedUserIdList.get(0));

        List<Long> errorOrderIdWithIn24HoursList = orderList.stream()
                .filter(order -> ERROR.equals(order.getOrderStatus()) )
                .filter(order -> now.minusHours(24).isBefore(order.getCreatedAt()))
                .map(Order::getOrderId)
                .collect(Collectors.toList());

        Assert.assertEquals(Long.valueOf(2022070104), errorOrderIdWithIn24HoursList.get(0));

    }

    @Test
    public void compareStreamToTraditionalWayTest(){
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
        List<String> unverifiedEmailList = new ArrayList<>();
        for(User user : userList){
            if(!user.isVerified()){
                unverifiedEmailList.add(user.getEmailAddress().orElse("no email"));
            }
        }
        Assert.assertEquals("tomLee@google.com", unverifiedEmailList.get(0));


        unverifiedEmailList = userList.stream()
                .filter(user -> !user.isVerified())
                .map(user -> user.getEmailAddress().orElse("no email"))
                .collect(Collectors.toList());
        Assert.assertEquals("tomLee@google.com", unverifiedEmailList.get(0));
    }
}
