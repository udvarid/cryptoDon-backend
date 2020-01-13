package com.donat.crypto.dto;

import lombok.Data;

@Data
public class RegisterDto {

    private String email;

    private String password;

    private String fullName;
}
