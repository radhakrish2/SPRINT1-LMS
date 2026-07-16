package com.learnhub.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.RegisterRequest;
import com.learnhub.dto.UserDto;
import com.learnhub.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    // Create User
    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(
            @Valid @RequestBody RegisterRequest regRequest) {

        UserDto user = userService.createUser(regRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "User created successfully.",
                        user));
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody RegisterRequest regRequest) {

        UserDto user = userService.updateUser(id, regRequest);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User updated successfully.",
                        user));
    }

    // Get User By Id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(
            @PathVariable Long id) {

        UserDto user = userService.getUserById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User retrieved successfully.",
                        user));
    }

    // Get User By Email
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<UserDto>> getUserByEmail(
            @PathVariable String email) {

        UserDto user = userService.getUserByEmail(email);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User retrieved successfully.",
                        user));
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {

        List<UserDto> users = userService.getAllUsers();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Users retrieved successfully.",
                        users));
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User deleted successfully.",
                        null));
    }

}