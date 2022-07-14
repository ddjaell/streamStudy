package service;

import pojo.UserWithFunctionBuilder;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class UserServiceInFunctionalWay {
    private final Predicate<UserWithFunctionBuilder> validateUser;
    private final Consumer<UserWithFunctionBuilder> saveUser;

    public UserServiceInFunctionalWay(Predicate<UserWithFunctionBuilder> validateUser, Consumer<UserWithFunctionBuilder> saveuser) {
        this.validateUser = validateUser;
        this.saveUser = saveuser;
    }

    public void createUser(UserWithFunctionBuilder user){
        if(validateUser.test(user)){
            saveUser.accept(user);
        }else{
            System.out.println("saving user error");
        }
    }
}
