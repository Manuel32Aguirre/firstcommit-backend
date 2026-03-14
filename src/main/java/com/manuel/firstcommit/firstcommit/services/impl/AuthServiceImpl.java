package com.manuel.firstcommit.firstcommit.services.impl;

import com.manuel.firstcommit.firstcommit.models.dto.request.LoginRequest;
import com.manuel.firstcommit.firstcommit.models.dto.response.LoginResponse;
import com.manuel.firstcommit.firstcommit.models.entities.Usuario;
import com.manuel.firstcommit.firstcommit.repositories.UsuarioRepositorio;
import com.manuel.firstcommit.firstcommit.security.JwtUtil;
import com.manuel.firstcommit.firstcommit.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        Usuario usuario = usuarioRepositorio.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("El usuario no se encuentra"));

        //Generar el token
        String token = jwtUtil.generarToken(usuario.getEmail());

        return new LoginResponse(
                token,
                usuario.getEmail(),
                usuario.getNombre(),
                "Bienvenido " + usuario.getNombre() + ", inicio de sesión exitoso"
        );
    }
}
