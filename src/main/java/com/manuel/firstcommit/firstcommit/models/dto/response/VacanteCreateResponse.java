package com.manuel.firstcommit.firstcommit.models.dto.response;

import com.manuel.firstcommit.firstcommit.models.entities.Modalidad;
import com.manuel.firstcommit.firstcommit.models.entities.Tecnologia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacanteCreateResponse {

    private Long id;

    private String titulo;

    private String descripcion;

    private List<Tecnologia> tecnologiasRequeridas;

    private Double sueldo;

    private Modalidad modalidad;
}
