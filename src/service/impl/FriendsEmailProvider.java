package service.impl;

import pojo.User;
import pojo.UserWithFunctionBuilder;
import service.EmailProvider;

public class FriendsEmailProvider implements EmailProvider {
    @Override
    public String getEmail(UserWithFunctionBuilder user) {
        return "Make more friends on our service " + user.getName();
    }
}
