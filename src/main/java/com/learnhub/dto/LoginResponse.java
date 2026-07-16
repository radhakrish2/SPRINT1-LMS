package com.learnhub.dto;

import com.learnhub.entity.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;

    private Long userId;

    private String name;

    private String email;

    private Roles role;

    private String message;

}