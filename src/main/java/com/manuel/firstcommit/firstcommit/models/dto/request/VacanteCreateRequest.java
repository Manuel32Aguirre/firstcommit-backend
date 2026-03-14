package com.manuel.firstcommit.firstcommit.models.dto.request;

import com.manuel.firstcommit.firstcommit.models.entities.Modalidad;
import com.manuel.firstcommit.firstcommit.models.entities.Tecnologia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacanteCreateRequest {

    private String titulo;

    private String descripcion;

    private List<Long> tecnologiasRequeridasId;

    private Double sueldo;

    private Long modalidadId;
}
