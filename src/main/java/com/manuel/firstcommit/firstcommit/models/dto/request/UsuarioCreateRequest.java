package com.manuel.firstcommit.firstcommit.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCreateRequest {

    private String nombreUsuario;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String email;

    private String password;

    private Integer edad;

    private String rol;


}
