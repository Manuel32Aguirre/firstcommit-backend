package com.manuel.firstcommit.firstcommit.controllers;

import com.manuel.firstcommit.firstcommit.mappers.VacanteMapper;
import com.manuel.firstcommit.firstcommit.models.dto.request.VacanteCreateRequest;
import com.manuel.firstcommit.firstcommit.models.dto.response.VacanteCreateResponse;
import com.manuel.firstcommit.firstcommit.models.entities.Vacante;
import com.manuel.firstcommit.firstcommit.services.IVacanteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vacante")
public class VacanteController {

    @Autowired
    private IVacanteServicio vacanteServicio;

    @Autowired
    private VacanteMapper vacanteMapper;

    @PostMapping
    public ResponseEntity<VacanteCreateResponse> registrarVacante(@Valid @RequestBody VacanteCreateRequest vacanteCreateRequest, BindingResult result){
        if(result.hasFieldErrors()){
            throw new RuntimeException("Se ha producido un error");
        }
        Vacante vacante = vacanteMapper.createToEntity(vacanteCreateRequest);
        Vacante vacanteBd = vacanteServicio.registrarVacante(vacante, vacanteCreateRequest.getTecnologiasRequeridasId(), vacanteCreateRequest.getModalidadId());
        return ResponseEntity.status(201).body(vacanteMapper.createToDto(vacanteBd));
    }
}
