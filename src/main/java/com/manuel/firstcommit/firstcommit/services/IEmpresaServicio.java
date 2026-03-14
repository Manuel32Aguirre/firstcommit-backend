package com.manuel.firstcommit.firstcommit.services;

import com.manuel.firstcommit.firstcommit.models.entities.Empresa;

public interface IEmpresaServicio {
    //Lo hace ROLE_RECLUTADOR, crea una empresa y su campo activo es false por defecto
    public Empresa registrarEmpresa(Empresa empresa);
    //Obtener los datos de la empresa
    public Empresa obtenerDatosEmpresaPorId(Long id);
    //Obtener los datos de la empresa
    public Empresa obtenerDatosEmpresaPorNombre(String nombre);
    //El ADMIN decide si la empresa es válida y si la persona que la registra es oficial
    public void validarEmpresaPorAdministrador(Long id);
    public Empresa actualizarDatosEmpresa(Long id, Empresa empresaActualizada);

}
