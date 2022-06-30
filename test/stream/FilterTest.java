package stream;

import org.junit.Assert;
import org.junit.Test;
import pojo.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilterTest {

    @Test
    public void filterTest() {
        List<Integer> integerList = Stream.of(-1, 2, 5, -10)
                .filter(x -> x > 0)
                .collect(Collectors.toList());
        Assert.assertEquals(Integer.valueOf(2), integerList.get(0));


    }

    @Test
    public void specificFilterTest() {
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

        Stream<User> userStream = Stream.of(user1, user2, user3);
        List<User> verifiedUserList = userStream
                .filter(User::isVerified)
                .collect(Collectors.toList());

        Assert.assertEquals("John", verifiedUserList.get(0).getName());
    }
}
