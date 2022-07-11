package moreStream;

import org.junit.Assert;
import org.junit.Test;
import pojo.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningByTest {
    @Test
    public void partitioningByTest() {
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Map<Boolean, List<Integer>> booleanListMap = integerList.stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        Integer evenNumSum = booleanListMap.get(true).stream()
                        .reduce(0, (x, y) -> x+y);


        Assert.assertEquals(Integer.valueOf(30), evenNumSum);
    }

    @Test
    public void partitioningByTestWithUsers() {
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

        Map<Boolean, List<User>> booleanListMap = userList.stream()
                .collect(Collectors.partitioningBy(user -> user.getFriendUserIdList().size() > 3));
        Assert.assertEquals(1001, booleanListMap.get(false).get(0).getId());
        
    }
}
