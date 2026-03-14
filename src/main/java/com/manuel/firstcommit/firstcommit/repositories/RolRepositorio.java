package com.manuel.firstcommit.firstcommit.repositories;

import com.manuel.firstcommit.firstcommit.models.entities.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolRepositorio extends CrudRepository<Rol, Long> {
    public Optional<Rol> findByNombre(String rol);
    public Boolean existsByNombre(String nombre);
}
