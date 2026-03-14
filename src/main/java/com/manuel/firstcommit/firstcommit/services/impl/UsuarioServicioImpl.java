package com.manuel.firstcommit.firstcommit.services.impl;

import java.util.List;
import java.util.Optional;

import com.manuel.firstcommit.firstcommit.models.entities.Rol;
import com.manuel.firstcommit.firstcommit.repositories.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manuel.firstcommit.firstcommit.models.entities.Usuario;
import com.manuel.firstcommit.firstcommit.repositories.UsuarioRepositorio;
import com.manuel.firstcommit.firstcommit.services.IUsuarioServicio;


@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    @Autowired
    public UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public RolRepositorio rolRepositorio;

    @Transactional
    @Override
    public Usuario registrarUsuario(Usuario usuario, String rol) {
        Optional<Rol> rolOptional = rolRepositorio.findByNombre(rol);
        if(rolOptional.isPresent()){
            usuario.setRol(rolOptional.get());
            return usuarioRepositorio.save(usuario);
        }else{
            throw new RuntimeException("Rol no encontrado en la base de datos");

        }

    }

    @Transactional(readOnly = true)
    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepositorio.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }

    @Transactional
    @Override
    public Usuario actualizarUsuarioPorId(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(id);
        if (usuarioOptional.isPresent() ) {
            Usuario usuario = usuarioOptional.get();
            if (!usuario.getNombreUsuario().equals(usuarioActualizado.getNombreUsuario())) {

                if (usuarioRepositorio.existsByNombreUsuario(usuarioActualizado.getNombreUsuario())) {
                    throw new RuntimeException("El nombre de usuario ya existe");
                }

                usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
            }

            if (!usuario.getEmail().equals(usuarioActualizado.getEmail())) {

                if (usuarioRepositorio.existsByEmail(usuarioActualizado.getEmail())) {
                    throw new RuntimeException("El correo electrónico ya existe");
                }
                usuario.setEmail(usuarioActualizado.getEmail());
            }

            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setApellidoPaterno(usuarioActualizado.getApellidoPaterno());
            usuario.setApellidoMaterno(usuarioActualizado.getApellidoMaterno());
            usuario.setEdad(usuarioActualizado.getEdad());

            return usuarioRepositorio.save(usuario);
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }

    }

    @Transactional
    @Override
    public void eliminarUsuarioPorId(Long id) {
        usuarioRepositorio.deleteById(id);
    }

}
