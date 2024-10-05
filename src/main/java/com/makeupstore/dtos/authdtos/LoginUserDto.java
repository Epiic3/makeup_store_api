package com.makeupstore.dtos.authdtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
