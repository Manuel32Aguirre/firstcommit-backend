package com.manuel.firstcommit.firstcommit.security;

import com.manuel.firstcommit.firstcommit.models.entities.Usuario;
import com.manuel.firstcommit.firstcommit.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(email).orElseThrow( () ->
                new UsernameNotFoundException("El correo no pertenece a ningún usuario en la base de datos")
        );
        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                new ArrayList<>()
        );
    }
}
