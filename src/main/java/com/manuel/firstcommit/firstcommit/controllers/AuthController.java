package com.manuel.firstcommit.firstcommit.controllers;

import com.manuel.firstcommit.firstcommit.models.dto.request.LoginRequest;
import com.manuel.firstcommit.firstcommit.models.dto.response.LoginResponse;
import com.manuel.firstcommit.firstcommit.security.JwtUtil;
import com.manuel.firstcommit.firstcommit.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> iniciarSesion(@RequestBody LoginRequest loginRequest){
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.status(200).body(response);
    }

}
