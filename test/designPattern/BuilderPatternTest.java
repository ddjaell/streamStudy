package designPattern;

import org.junit.Assert;
import org.junit.Test;
import pojo.UserWithFunctionBuilder;
import pojo.UserWithTraditionalBuilder;

public class BuilderPatternTest {

    @Test
    public void builderPatternTest() {
        UserWithTraditionalBuilder user = UserWithTraditionalBuilder.builder(1, "John")
                .emailAddress("johnKim@google.com")
                .verified(true)
                .build();
        System.out.println(user);
        Assert.assertEquals("johnKim@google.com", user.getEmailAddress().get());
    }

    @Test
    public void builderPatternWithFunctionTest() {
        UserWithFunctionBuilder user = UserWithFunctionBuilder.builder(1, "John")
                .with(consumer -> {
                    consumer.emailAddress = "johnKim@google.com";
                    consumer.isVerified = true;
                })
                .build();
        System.out.println(user);
        Assert.assertEquals("johnKim@google.com", user.getEmailAddress().get());
    }
}
