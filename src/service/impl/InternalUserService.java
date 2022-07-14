package service.impl;

import pojo.UserWithFunctionBuilder;
import service.AbstractUserService;

public class InternalUserService extends AbstractUserService {
    @Override
    protected boolean validateUser(UserWithFunctionBuilder user) {
        System.out.println("InternalUserService validateUser " + user.getName());
        return true;
    }

    @Override
    protected void saveUser(UserWithFunctionBuilder user) {
        System.out.println("InternalUserService saving user " + user.getName());
    }



}
