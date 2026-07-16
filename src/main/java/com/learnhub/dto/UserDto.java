package com.learnhub.dto;

import com.learnhub.entity.Roles;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Roles role;

}