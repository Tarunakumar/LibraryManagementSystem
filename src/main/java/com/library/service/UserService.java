package com.library.service;

import com.library.dto.LoginRequest;
import com.library.dto.RegisterRequest;

public interface UserService {

    String register(
            RegisterRequest request);

    String login(
            LoginRequest request);
}