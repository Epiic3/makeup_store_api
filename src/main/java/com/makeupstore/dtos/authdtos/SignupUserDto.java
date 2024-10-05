package com.makeupstore.dtos.authdtos;

import lombok.Data;

@Data
public class SignupUserDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
