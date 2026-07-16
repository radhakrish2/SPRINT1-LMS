package com.learnhub.service;

import java.util.List;

import com.learnhub.dto.RegisterRequest;
import com.learnhub.dto.UserDto;

public interface UserService {

    // Create new user
    UserDto createUser(RegisterRequest userDto);


    // Update existing user
    UserDto updateUser(Long id, RegisterRequest userDto);


    // Get user by id
    UserDto getUserById(Long id);


    // Get user by email
    UserDto getUserByEmail(String email);


    // Get all users
    List<UserDto> getAllUsers();


    // Delete user
    void deleteUser(Long id);


    // Check email availability
    boolean existsByEmail(String email);

}