package com.learnhub.service;

import com.learnhub.dto.ApiResponse;
import com.learnhub.dto.LoginRequest;
import com.learnhub.dto.LoginResponse;
import com.learnhub.dto.RegisterRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    ApiResponse register(RegisterRequest request);

}