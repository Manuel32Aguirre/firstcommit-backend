package com.manuel.firstcommit.firstcommit.services.impl;

import com.manuel.firstcommit.firstcommit.models.entities.Empresa;
import com.manuel.firstcommit.firstcommit.repositories.EmpresaRepositorio;
import com.manuel.firstcommit.firstcommit.services.IEmpresaServicio;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EmpresaServicioImpl implements IEmpresaServicio {

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    @Transactional
    @Override
    public Empresa registrarEmpresa(Empresa empresa) {
        if(!empresaRepositorio.existsByNombre(empresa.getNombre())){
            return empresaRepositorio.save(empresa);
        }else{
            throw new RuntimeException("El nombre de la empresa ya existe, use otro por favor");
        }

    }

    @Transactional(readOnly = true)
    @Override
    public Empresa obtenerDatosEmpresaPorId(Long id) {
        return empresaRepositorio.findById(id).orElseThrow();
    }

    @Override
    public Empresa obtenerDatosEmpresaPorNombre(String nombre) {
        Optional<Empresa> empresaOptional = empresaRepositorio.findByNombre(nombre);
        if(empresaOptional.isPresent()){
            return empresaOptional.get();

        }
        throw new RuntimeException("La empresa que ingresó no se encuentra en la base de datos");

    }

    @Override
    public void validarEmpresaPorAdministrador(Long id) {
        Optional<Empresa> empresaOptional = empresaRepositorio.findById(id);
        if(empresaOptional.isPresent()){
            Empresa empresaBd= empresaOptional.get();
            empresaBd.setActiva(true);
            empresaRepositorio.save(empresaBd);
        }else{
            throw new RuntimeException("No se encontró la empresa con el ID proporcionado");
        }

    }

    @Override
    public Empresa actualizarDatosEmpresa(Long id, Empresa empresaActualizada) {
        Optional<Empresa> empresaOptional = empresaRepositorio.findById(id);
        if(empresaOptional.isPresent()){
            Empresa empresa = empresaOptional.get();
            //Verificar que otra empresa no tenga el mismo name
            if(!empresaActualizada.getNombre().equals(empresa.getNombre())){
                if(!empresaRepositorio.existsByNombre(empresaActualizada.getNombre())){
                    empresa.setNombre(empresaActualizada.getNombre());
                }else{
                    throw new RuntimeException("El nombre que intenta registrar ya pertenece a otra empresa");
                }
            }
            if(!empresaActualizada.getRfc().equals(empresa.getRfc())){
                if(!empresaRepositorio.existsByRfc(empresaActualizada.getRfc())){
                    empresa.setRfc(empresaActualizada.getRfc());
                } else {
                    throw new RuntimeException("El RFC ya está registrado por otra empresa");
                }
            }
            empresa.setRazonSocial(empresaActualizada.getRazonSocial());;
            empresa.setSitioWeb(empresaActualizada.getSitioWeb());
            empresa.setDescripcion(empresaActualizada.getDescripcion());
            Empresa empresaBd = empresaRepositorio.save(empresa);
            return empresaBd;
        }
            throw new RuntimeException("Hubo un problema al actualizar los datos de la empresa");
    }

}
