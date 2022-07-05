package optional;

import org.junit.Assert;
import org.junit.Test;
import pojo.User;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void optionalTest() {
        User user1 = new User()
                .setName("John")
                .setId(123)
                .setVerified(true)
                .setEmailAddress("JohnKim@google.com");

        User user2 = new User()
                .setName("John")
                .setId(123)
                .setVerified(false);
        //Assert.assertFalse(userEquals(user2, user1));

        String email = "JohnKim@google.com";
        String nullEmail = null;
        Optional<String> maybeEmail = Optional.of(email);
        if(maybeEmail.isPresent()){
            user2.setEmailAddress(maybeEmail.get());
            Assert.assertFalse(userEquals(user2, user1));
        }

        Optional<String> maybeEmail2 = Optional.empty();
        String defaultEmail = "JohnKim@google.com";
        email = maybeEmail2.orElse(defaultEmail);
        user2.setEmailAddress(email)
             .setVerified(true);
        Assert.assertTrue(userEquals(user1, user2));

        Optional<User> maybeUser = Optional.ofNullable(getUser(true));
        Optional<User> maybeUser2 = Optional.ofNullable(getUser(false));
        User gotUser = null;
        maybeUser.ifPresent(user -> System.out.println(user));

        Integer id = maybeUser.map(user -> user.getId()).orElse(100);
        Assert.assertEquals(Integer.valueOf(123), id);

        String userName = maybeUser
                .map(User::getName)
                .map(name -> "Name is " + name)
                .orElse("Name is empty");

        Assert.assertEquals("Name is John", userName);

        Optional<Optional<String>> optionalEmailAddress = maybeUser
                .map(User::getEmailAddress);
        Assert.assertEquals(Optional.of(Optional.of("JohnKim@google.com")), optionalEmailAddress);

        Optional<String> emailAddress = maybeUser
                .flatMap(User::getEmailAddress);
        Assert.assertEquals("JohnKim@google.com", emailAddress.get());


    }

    public boolean userEquals(User u1, User u2){
        return u1.getName().equals(u2.getName())
                && u1.getId() == u2.getId()
                && u1.getEmailAddress().equals(u2.getEmailAddress())
                && u1.isVerified() == u2.isVerified();
    }

    public User getUser(boolean returnUser){
        if(returnUser){
            return new User()
                    .setName("John")
                    .setId(123)
                    .setVerified(true)
                    .setEmailAddress("JohnKim@google.com");
        }
        return null;
    }
}
