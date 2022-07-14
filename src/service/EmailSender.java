package service;

import pojo.User;
import pojo.UserWithFunctionBuilder;

public class EmailSender {
    private EmailProvider emailProvider;

    public EmailSender setEmailProvider(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
        return this;
    }

    public void sendEmail(UserWithFunctionBuilder user) {
        System.out.println("sending email : " + emailProvider.getEmail(user));
    }
}
