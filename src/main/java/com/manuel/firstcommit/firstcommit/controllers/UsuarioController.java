package com.manuel.firstcommit.firstcommit.controllers;

import com.manuel.firstcommit.firstcommit.models.dto.request.UsuarioCreateRequest;
import com.manuel.firstcommit.firstcommit.models.dto.request.UsuarioUpdateRequest;
import com.manuel.firstcommit.firstcommit.models.dto.response.UsuarioUpdateResponse;
import org.springframework.web.bind.annotation.RestController;

import com.manuel.firstcommit.firstcommit.models.dto.response.UsuarioCreateResponse;
import com.manuel.firstcommit.firstcommit.models.entities.Usuario;
import com.manuel.firstcommit.firstcommit.services.IUsuarioServicio;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioServicio usuarioServicio;

    @PostMapping
    public ResponseEntity<UsuarioCreateResponse> registrarUsuario(@Valid @RequestBody UsuarioCreateRequest usuarioCreateRequest, BindingResult result){
        if(result.hasFieldErrors()){
            throw new RuntimeException("Error en la validación de los datos");
        }


        Usuario user = new Usuario();
        user.setNombreUsuario(usuarioCreateRequest.getNombreUsuario());
        user.setNombre(usuarioCreateRequest.getNombre());
        user.setApellidoPaterno(usuarioCreateRequest.getApellidoPaterno());
        user.setApellidoMaterno(usuarioCreateRequest.getApellidoMaterno());
        user.setEmail(usuarioCreateRequest.getEmail());
        user.setPassword(usuarioCreateRequest.getPassword());
        user.setEdad(usuarioCreateRequest.getEdad());

        Usuario usuarioRegistrado = usuarioServicio.registrarUsuario(user,usuarioCreateRequest.getRol());
        UsuarioCreateResponse usuarioResponse = new UsuarioCreateResponse(
            usuarioRegistrado.getId(),
            usuarioRegistrado.getNombreUsuario()
        );
        return ResponseEntity.status(201).body(usuarioResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioUpdateResponse> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateRequest usuarioUpdateRequest, BindingResult result){
        if(result.hasFieldErrors()){
            throw new RuntimeException("Error en la validación de los datos");
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(usuarioUpdateRequest.getNombreUsuario());
        usuario.setNombre(usuarioUpdateRequest.getNombre());
        usuario.setApellidoPaterno(usuarioUpdateRequest.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioUpdateRequest.getApellidoMaterno());
        usuario.setEmail(usuarioUpdateRequest.getEmail());
        usuario.setEdad(usuarioUpdateRequest.getEdad());

        Usuario usuarioActualizado = usuarioServicio.actualizarUsuarioPorId(id, usuario);
        UsuarioUpdateResponse usuarioUpdateResponse = new UsuarioUpdateResponse();
        usuarioUpdateResponse.setNombreUsuario(usuarioActualizado.getNombreUsuario());
        usuarioUpdateResponse.setNombre(usuarioActualizado.getNombre());
        usuarioUpdateResponse.setApellidoPaterno(usuarioActualizado.getApellidoPaterno());
        usuarioUpdateResponse.setApellidoMaterno(usuarioActualizado.getApellidoMaterno());
        usuarioUpdateResponse.setEmail(usuarioActualizado.getEmail());
        usuarioUpdateResponse.setEdad(usuarioActualizado.getEdad());

        return ResponseEntity.status(200).body(usuarioUpdateResponse);
    }
    
    
}
