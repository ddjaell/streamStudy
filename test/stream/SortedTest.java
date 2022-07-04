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

public class SortedTest {
    @Test
    public void sortedTest() {
        List<Integer> numberList = Arrays.asList(3, -1, 5, 10, 7);
        List<Integer> sortedNumberList = numberList.stream()
                .sorted()
                .collect(Collectors.toList());
        Assert.assertEquals(Integer.valueOf(-1), sortedNumberList.get(0));

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
        List<User> userListSortedByUserName = userList.stream()
                .sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .collect(Collectors.toList());

        Assert.assertEquals("Jerry", userListSortedByUserName.get(0).getName());


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
        List<Order> orderListSortedByCreatedAt = orderList.stream()
                .sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()))
                .collect(Collectors.toList());
        System.out.println(orderListSortedByCreatedAt);
        Assert.assertEquals(Long.valueOf(2022070105), Long.valueOf(orderListSortedByCreatedAt.get(0).getOrderId()));

    }
}
