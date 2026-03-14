package com.manuel.firstcommit.firstcommit.services;

import com.manuel.firstcommit.firstcommit.models.dto.request.VacanteEditRequest;
import com.manuel.firstcommit.firstcommit.models.entities.Vacante;

import java.util.List;
import java.util.Optional;

public interface IVacanteServicio {
    public Vacante registrarVacante(Vacante vacante, List<Long> tecnologiasRequeridasId, Long modalidadId);
    public Optional<Vacante> obtenerVacanate(Long id);
    public List<Vacante> obtenerTodasLasVacantes();
    public Vacante editarVacantePorId(Long id, VacanteEditRequest vacanteEditRequest);
    public void EliminarVacantePorId(Long id);
}
