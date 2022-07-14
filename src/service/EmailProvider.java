package service;

import pojo.User;
import pojo.UserWithFunctionBuilder;

public interface EmailProvider {
    String getEmail(UserWithFunctionBuilder user);
}
