package com.manuel.firstcommit.firstcommit.controllers;

import com.manuel.firstcommit.firstcommit.models.dto.request.EmpresaCreateRequest;
import com.manuel.firstcommit.firstcommit.models.dto.response.EmpresaCreateResponse;
import com.manuel.firstcommit.firstcommit.models.entities.Empresa;
import com.manuel.firstcommit.firstcommit.services.IEmpresaServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaServicio empresaServicio;

    @PostMapping
    public ResponseEntity<EmpresaCreateResponse> registrarEmpresa(@Valid @RequestBody EmpresaCreateRequest empresaCreateRequest, BindingResult result){
        if(result.hasFieldErrors()){
            throw new RuntimeException("Error al enviar la solicitud");
        }
        Empresa empresa = new Empresa();
        empresa.setNombre(empresaCreateRequest.getNombre());
        empresa.setRazonSocial(empresaCreateRequest.getRazonSocial());
        empresa.setRfc(empresaCreateRequest.getRfc());
        empresa.setSitioWeb(empresaCreateRequest.getDescripcion());
        empresa.setActiva(empresaCreateRequest.isActiva());

        Empresa empresaRegistrada = empresaServicio.registrarEmpresa(empresa);

        EmpresaCreateResponse empresaCreateResponse = new EmpresaCreateResponse();
        empresaCreateResponse.setNombre(empresaRegistrada.getNombre());
        empresaCreateResponse.setRazonSocial(empresaRegistrada.getRazonSocial());
        empresaCreateResponse.setRfc(empresaRegistrada.getRfc());
        empresaCreateResponse.setSitioWeb(empresaRegistrada.getSitioWeb());
        empresaCreateResponse.setDescripcion(empresaRegistrada.getDescripcion());
        empresaCreateResponse.setActiva(empresaRegistrada.isActiva());

        return ResponseEntity.status(201).body(empresaCreateResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerDatosEmpresaPorId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(empresaServicio.obtenerDatosEmpresaPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Empresa> obtenerDatosEmpresaPorNombre(@RequestParam String nombre){
        return ResponseEntity.status(200).body(empresaServicio.obtenerDatosEmpresaPorNombre(nombre));
    }

    @PutMapping("/validar/{id}")
    public ResponseEntity<?> validarEmpresaPorAdministrador(@PathVariable Long id){
        empresaServicio.validarEmpresaPorAdministrador(id);
        Map<String, String> body = new HashMap<>();
        return ResponseEntity.status(204).body(body);
    }



}
