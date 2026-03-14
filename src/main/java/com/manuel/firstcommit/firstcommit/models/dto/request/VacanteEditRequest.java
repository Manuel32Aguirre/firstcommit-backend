package com.manuel.firstcommit.firstcommit.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VacanteEditRequest {

    private String titulo;

    private String descripcion;

    List<Long> tecnologiasId;

    Long modalidadId;

    private Double sueldo;

    private boolean activa;

}
