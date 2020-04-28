package com.donat.crypto.security;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspect {

    private LoginFailureEventPublisher publisher;

    @Autowired
    public void setPublisher(LoginFailureEventPublisher publisher) {
        this.publisher = publisher;
    }


    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
    public void doAuthenticate(){}

    @AfterThrowing("com.donat.crypto.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logAuthenicationException(Authentication authentication){
        String userDetails = (String) authentication.getPrincipal();
        System.out.println("Login failed for user: " + userDetails);
        publisher.publish(new LoginFailureEvent(authentication));
    }
}
