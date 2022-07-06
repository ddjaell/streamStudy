package moreStream;

import org.junit.Assert;
import org.junit.Test;
import pojo.Order;
import pojo.OrderDetail;
import pojo.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import static pojo.Order.OrderStatus.*;

public class ReduceTest {

    @Test
    public void reduceTest() {
        List<Integer> integerList = Arrays.asList(1,2,3);
        int sumInteger = integerList.stream()
                .reduce((x,y) -> x + y)
                .get();

        Assert.assertEquals(6, sumInteger);

        int min = integerList.stream()
                .reduce((x,y) -> x > y ? y : x)
                .get();
        Assert.assertEquals(1, min);

        List<String> strIntegerList = Arrays.asList("1","2","3");
        int sumOfStrInteger = strIntegerList.stream()
                .map(Integer::parseInt)
                .reduce(0, (x, y) -> x+y);
        Assert.assertEquals(6, sumOfStrInteger);

        int productOfStrInteger = strIntegerList.stream()
                        .map(Integer::parseInt)
                                .reduce(2, (x,y) -> x*y);
        Assert.assertEquals(12, productOfStrInteger);
    }

    @Test
    public void reduceTestWithUser() {
        User user1 = new User()
                .setName("John")
                .setId(1000)
                .setVerified(true)
                .setEmailAddress("johnKim@google.com")
                .setFriendUserIdList(Arrays.asList(1000,1001,1002,1003));

        User user2 = new User()
                .setName("Tom")
                .setId(1001)
                .setVerified(false)
                .setEmailAddress("tomLee@google.com")
                .setFriendUserIdList(Arrays.asList(2000,1001,1002));

        User user3 = new User()
                .setName("Jerry")
                .setId(1002)
                .setVerified(true)
                .setEmailAddress("jerryOh@google.com")
                .setFriendUserIdList(Arrays.asList(1000,1001,1002,1003));

        List<User> userList = Arrays.asList(user1, user2, user3);


        int sumOfUsersFriends = userList.stream()
                .map(User::getFriendUserIdList)
                .map(List::size)
                .reduce(0, (x,y) -> x+y);
        Assert.assertEquals(11, sumOfUsersFriends);

    }

    @Test
    public void reduceTestWithOrders() {
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
        BigDecimal sumOfOrderDetails = orderList.stream()
                .filter(order -> order.getOrderDetailList() != null)
                .map(Order::getOrderDetailList)
                .flatMap(List::stream)
                .map(OrderDetail::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Assert.assertEquals(BigDecimal.valueOf(333550), sumOfOrderDetails);
    }
}
