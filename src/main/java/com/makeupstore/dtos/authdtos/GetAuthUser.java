package com.makeupstore.dtos.authdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAuthUser {
    private Long id;
    private String name;
    private String email;
    private Set<String> roles;
    private String token;
}
