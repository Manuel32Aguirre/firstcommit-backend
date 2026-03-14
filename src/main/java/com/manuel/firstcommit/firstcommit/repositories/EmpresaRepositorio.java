package com.manuel.firstcommit.firstcommit.repositories;

import com.manuel.firstcommit.firstcommit.models.entities.Empresa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmpresaRepositorio extends CrudRepository<Empresa, Long> {
    public Boolean existsByNombre(String nombre);
    public Optional<Empresa> findByNombre(String nombre);
    public Optional<Empresa> findById(Long id);
    public Boolean existsByRfc(String rfc);
}
