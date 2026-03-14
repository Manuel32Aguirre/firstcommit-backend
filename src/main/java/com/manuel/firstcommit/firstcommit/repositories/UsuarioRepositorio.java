package com.manuel.firstcommit.firstcommit.repositories;

import org.springframework.data.repository.CrudRepository;

import com.manuel.firstcommit.firstcommit.models.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>{
    Boolean existsByNombreUsuario(String usuario);
    Boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String email);
}
