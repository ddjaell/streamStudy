package service.impl;

import pojo.UserWithFunctionBuilder;
import service.AbstractUserService;

public class UserService extends AbstractUserService {
    @Override
    protected boolean validateUser(UserWithFunctionBuilder user) {
        System.out.println("UserService validateUser " + user.getName());
        return user.getName() != null && user.getEmailAddress().isPresent();
    }

    @Override
    protected void saveUser(UserWithFunctionBuilder user) {
        System.out.println("saving user completed");
    }
}
