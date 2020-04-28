package com.donat.crypto.security;

import org.aspectj.lang.annotation.AfterReturning;
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
    private LoginSuccessEventPublisher successEventPublisher;


    @Autowired
    public void setPublisher(LoginFailureEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Autowired
    public void setSuccessEventPublisher(LoginSuccessEventPublisher successEventPublisher) {
        this.successEventPublisher = successEventPublisher;
    }


    @Pointcut("execution(* org.springframework.security.authentication.AuthenticationProvider.authenticate(..))")
    public void doAuthenticate(){}

    @AfterReturning(value = "com.donat.crypto.security.LoginAspect.doAuthenticate()",
        returning = "authentication")
    public void logAfterAuthenticate( Authentication authentication){
        System.out.println("This is after the Authenticate Method authentication: " + authentication.isAuthenticated());
        successEventPublisher.publishEvent(new LoginSuccessEvent(authentication));
    }


    @AfterThrowing("com.donat.crypto.security.LoginAspect.doAuthenticate() && args(authentication)")
    public void logAuthenicationException(Authentication authentication){
        String userDetails = (String) authentication.getPrincipal();
        System.out.println("Login failed for user: " + userDetails);
        publisher.publish(new LoginFailureEvent(authentication));
    }
}
