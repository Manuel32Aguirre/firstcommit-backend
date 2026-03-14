package com.manuel.firstcommit.firstcommit.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${SECRET_KEY}")
    private String LLAVE_SECRETA;
    private static final Long TIEMPO_EXPIRACION = 86400000L;

    private Key llave;

    @PostConstruct
    public void postConstruct(){
        this.llave = Keys.hmacShaKeyFor(LLAVE_SECRETA.getBytes());
    }

    //Generamos el token
    public String generarToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_EXPIRACION))
                .signWith(llave, SignatureAlgorithm.HS256)
                .compact();

    }
}
