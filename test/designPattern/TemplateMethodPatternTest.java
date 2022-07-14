package designPattern;

import org.junit.Test;
import pojo.UserWithFunctionBuilder;
import service.UserServiceInFunctionalWay;
import service.impl.InternalUserService;
import service.impl.UserService;

import java.util.Arrays;

public class TemplateMethodPatternTest {
    @Test
    public void templateMethodPatternInTraditionalWayTest() {
        UserWithFunctionBuilder user1 = UserWithFunctionBuilder.builder(1, "John")
                .with(consumer -> {
                    //consumer.emailAddress = "johnKim@google.com";
                    consumer.isVerified = true;
                    consumer.friendUserIdList = Arrays.asList(100,101,102,103);
                })
                .build();

        UserService userService = new UserService();
        InternalUserService internalUserService = new InternalUserService();

        userService.createUser(user1);
        internalUserService.createUser(user1);
    }

    @Test
    public void templateMethodPatternInFunctionalWayTest() {
        UserWithFunctionBuilder user1 = UserWithFunctionBuilder.builder(1, "John")
                .with(consumer -> {
                    consumer.emailAddress = "johnKim@google.com";
                    consumer.isVerified = true;
                    consumer.friendUserIdList = Arrays.asList(100,101,102,103);
                })
                .build();

        UserServiceInFunctionalWay service = new UserServiceInFunctionalWay(user -> {
            System.out.println("UserService validateUser " + user.getName());
            return user.getName() != null && user.getEmailAddress().isPresent();
        }, user -> {
            System.out.println("saving user completed");
        });
        service.createUser(user1);
    }
}
