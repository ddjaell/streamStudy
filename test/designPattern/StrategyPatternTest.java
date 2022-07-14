package designPattern;

import org.junit.Test;
import pojo.UserWithFunctionBuilder;
import service.EmailProvider;
import service.EmailSender;
import service.impl.FriendsEmailProvider;
import service.impl.VerifyEmailProvider;

import java.util.Arrays;
import java.util.List;

public class StrategyPatternTest {
    @Test
    public void strategyPatternTest() {
        UserWithFunctionBuilder user1 = UserWithFunctionBuilder.builder(1, "John")
                .with(consumer -> {
                    consumer.emailAddress = "johnKim@google.com";
                    consumer.isVerified = true;
                    consumer.friendUserIdList = Arrays.asList(100,101,102,103);
                })
                .build();

        UserWithFunctionBuilder user2 = UserWithFunctionBuilder.builder(1, "Tim")
                .with(consumer -> {
                    consumer.emailAddress = "timDoe@google.com";
                    consumer.isVerified = false;
                    consumer.friendUserIdList = Arrays.asList(100,101,102,103,104,105);
                })
                .build();

        UserWithFunctionBuilder user3 = UserWithFunctionBuilder.builder(1, "Owen")
                .with(consumer -> {
                    consumer.emailAddress = "owenKimberly@google.com";
                    consumer.isVerified = false;
                    consumer.friendUserIdList = Arrays.asList(100,101,102,103,106,107,108);
                })
                .build();
        List<UserWithFunctionBuilder> userList = Arrays.asList(user1, user2, user3);

        EmailProvider makeFriendsEmailProvider = new FriendsEmailProvider();
        EmailProvider verifyYourEmailProvider = new VerifyEmailProvider();
        EmailSender emailSender = new EmailSender();

        emailSender.setEmailProvider(makeFriendsEmailProvider);
        userList.stream()
                .filter(user -> user.getFriendUserIdList().size() < 5)
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(verifyYourEmailProvider);
        userList.stream()
                .filter(user -> !user.isVerified())
                .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(user -> "Test Email sending to : " + user.getEmailAddress());
        userList.stream()
                .forEach(emailSender::sendEmail);



    }
}
