package com.donat.crypto.controller;


import com.donat.crypto.dto.RegisterDto;
import com.donat.crypto.dto.UserDto;
import com.donat.crypto.dto.UserLoginDto;
import com.donat.crypto.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@Transactional
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto) {
        userService.registerUser(registerDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    //TODO session rotate és Csrf token bekötése
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {
        userService.login(userLoginDto, request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/oneUser")
    public UserDto makeListOfUsers(@RequestParam String email) {
        return userService.getOneUser(email);
    }

    @GetMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }


}

