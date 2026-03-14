package com.manuel.firstcommit.firstcommit.services;

import java.util.List;

import com.manuel.firstcommit.firstcommit.models.entities.Usuario;

public interface IUsuarioServicio {
    Usuario registrarUsuario(Usuario usuario, String rol);

    Usuario obtenerUsuarioPorId(Long id);

    List<Usuario> obtenerTodosLosUsuarios();

    Usuario actualizarUsuarioPorId(Long id, Usuario usuarioActualizado);

    void eliminarUsuarioPorId(Long id);

}
