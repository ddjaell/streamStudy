package service.impl;

import pojo.User;
import pojo.UserWithFunctionBuilder;
import service.EmailProvider;

public class VerifyEmailProvider implements EmailProvider {
    @Override
    public String getEmail(UserWithFunctionBuilder user) {
        return "Verify your email address " + user.getEmailAddress();
    }
}
