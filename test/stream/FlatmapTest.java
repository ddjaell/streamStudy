package stream;

import org.junit.Assert;
import org.junit.Test;
import pojo.Order;
import pojo.OrderDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pojo.Order.OrderStatus.*;

public class FlatmapTest {
    @Test
    public void flatmapTest() {
        String[][] cities = {
                {"Seoul", "Busan"},
                {"New York", "LA"},
                {"Paris", "London"}
        };
        Stream<String[]> cityStream = Arrays.stream(cities);
        Stream<Stream<String>> cityStreamStream = cityStream.map(x -> Arrays.stream(x));
        List<Stream<String>> cityStreamList = cityStreamStream.collect(Collectors.toList());

        Stream<String[]> cityStream2 = Arrays.stream(cities);
        Stream<String> flatmapCityStream = cityStream2.flatMap(x -> Arrays.stream(x));
        List<String> cityList = flatmapCityStream.collect(Collectors.toList());

        Assert.assertEquals("Seoul", cityList.get(0));

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = new Order()
                .setOrderId(2022070101)
                .setOrderStatus(CREATED)
                .setUserId(1000)
                .setCreatedAt(now.minusMinutes(15))
                .setOrderDetailList(Arrays.asList(
                        new OrderDetail()
                                .setId(10001)
                                .setAmount(BigDecimal.valueOf(5000)),
                        new OrderDetail()
                                .setId(10001)
                                .setAmount(BigDecimal.valueOf(9000))
                        )

                );

        Order order2 = new Order()
                .setOrderId(2022070102)
                .setOrderStatus(PROCESSED)
                .setUserId(1000)
                .setCreatedAt(now.minusMinutes(10))
                .setOrderDetailList(Arrays.asList(
                                new OrderDetail()
                                        .setId(10003)
                                        .setAmount(BigDecimal.valueOf(56000)),
                                new OrderDetail()
                                        .setId(10004)
                                        .setAmount(BigDecimal.valueOf(1000))
                        )

                );

        Order order3 = new Order()
                .setOrderId(2022070103)
                .setOrderStatus(IN_PROGRESS)
                .setUserId(1001)
                .setCreatedAt(now.minusMinutes(8));

        Order order4 = new Order()
                .setOrderId(2022070104)
                .setOrderStatus(ERROR)
                .setUserId(1002)
                .setCreatedAt(now.minusMinutes(85))
                .setOrderDetailList(Arrays.asList(
                                new OrderDetail()
                                        .setId(10007)
                                        .setAmount(BigDecimal.valueOf(504500)),
                                new OrderDetail()
                                        .setId(10008)
                                        .setAmount(BigDecimal.valueOf(92000))
                        )

                );

        Order order5 = new Order()
                .setOrderId(2022070105)
                .setOrderStatus(PROCESSED)
                .setUserId(1002)
                .setCreatedAt(now.minusMinutes(100));

        List<Order> orderList = Arrays.asList(order1, order2, order3, order4, order5);

        List<Long> orderDetailIdList = orderList.stream()
                .filter(x -> x.getOrderDetailList() != null)
                .map(Order::getOrderDetailList)
                .flatMap(List::stream)
                .map(OrderDetail::getId)
                .collect(Collectors.toList());

        Assert.assertEquals(Long.valueOf(10001), orderDetailIdList.get(0));

    }
}
