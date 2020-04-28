package com.donat.crypto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.donat.crypto.domain.User;
import com.donat.crypto.service.UserService;

@Component
public class LoginSuccessEventHandler implements ApplicationListener<LoginSuccessEvent> {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("LoginEvent Succes for: " + name);
        updateUserAccount(name);
    }

    public void updateUserAccount(String name){
        User user = userService.findByEmail(name);

        if (user != null) { //user found
            user.setFailedLoginAttempts(0);

            System.out.println("Good login, resetting failed attempts");
            userService.saveOrUpdate(user);
        }
    }
}
