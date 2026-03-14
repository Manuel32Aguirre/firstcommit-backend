package com.manuel.firstcommit.firstcommit.services;

import com.manuel.firstcommit.firstcommit.models.dto.request.LoginRequest;
import com.manuel.firstcommit.firstcommit.models.dto.response.LoginResponse;

public interface IAuthService {
    public LoginResponse login(LoginRequest loginRequest);
}
