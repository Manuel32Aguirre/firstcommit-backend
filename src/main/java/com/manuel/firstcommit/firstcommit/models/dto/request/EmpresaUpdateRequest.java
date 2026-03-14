package com.manuel.firstcommit.firstcommit.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaUpdateRequest {

    private String nombre;
    private String razonSocial;
    private String rfc;
    private String sitioWeb;
    private String descripcion;


}
