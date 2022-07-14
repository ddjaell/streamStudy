package service;

import pojo.UserWithFunctionBuilder;

public abstract class AbstractUserService {
    protected abstract boolean validateUser(UserWithFunctionBuilder user);

    protected abstract void saveUser(UserWithFunctionBuilder user);

    public void createUser(UserWithFunctionBuilder user){
        if(validateUser(user)){
            saveUser(user);
        }else{
            System.out.println("saving user error");
        }
    }
}
