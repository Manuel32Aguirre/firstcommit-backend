package com.manuel.firstcommit.firstcommit.services.impl;

import com.manuel.firstcommit.firstcommit.mappers.VacanteMapper;
import com.manuel.firstcommit.firstcommit.models.dto.request.VacanteEditRequest;
import com.manuel.firstcommit.firstcommit.models.entities.Modalidad;
import com.manuel.firstcommit.firstcommit.models.entities.Tecnologia;
import com.manuel.firstcommit.firstcommit.models.entities.Vacante;
import com.manuel.firstcommit.firstcommit.repositories.ModalidadRepositorio;
import com.manuel.firstcommit.firstcommit.repositories.TecnologiaRepositorio;
import com.manuel.firstcommit.firstcommit.repositories.VacanteRepositorio;
import com.manuel.firstcommit.firstcommit.services.IVacanteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VacanteServicioImpl implements IVacanteServicio {
    @Autowired
    private VacanteRepositorio vacanteRepositorio;

    @Autowired
    private ModalidadRepositorio modalidadRepositorio;

    @Autowired
    private TecnologiaRepositorio tecnologiaRepositorio;

    //Mappers
    @Autowired
    private VacanteMapper vacanteMapper;

    @Transactional
    @Override
    public Vacante registrarVacante(Vacante vacante, List<Long> tecnologiasRequeridasId, Long modalidadId) {
        List<Tecnologia> tecnologiasRequeridas = new ArrayList<>();
        for(Long i: tecnologiasRequeridasId){
            Optional<Tecnologia> tecActual = tecnologiaRepositorio.findById(i);
            if(tecActual.isPresent()){
                tecnologiasRequeridas.add(tecActual.get());
            }else{
                throw new RuntimeException("Alguna de las tecnologías en la lista no existe en la base de datos");
            }
        }
        vacante.setTecnologiasRequeridas(tecnologiasRequeridas);
        Optional<Modalidad> modalidad = modalidadRepositorio.findById(modalidadId);
        if(modalidad.isPresent()){
            vacante.setModalidad(modalidad.get());
        }else{
            throw new RuntimeException("La modalidad no existe en la base de datos");
        }
        return vacanteRepositorio.save(vacante);
    }

    @Override
    public Optional<Vacante> obtenerVacanate(Long id) {
        return vacanteRepositorio.findById(id);
    }

    @Override
    public List<Vacante> obtenerTodasLasVacantes() {
        return (List<Vacante>) vacanteRepositorio.findAll();
    }

    @Transactional
    @Override
    public Vacante editarVacantePorId(Long id, VacanteEditRequest vacanteEditRequest) {
        Vacante vacanteBd = vacanteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("La vacante no se encontró en la base de datos"));

        vacanteMapper.updateFromDtoToEntity(vacanteEditRequest, vacanteBd);

        if(vacanteEditRequest.getModalidadId() != null){
            Modalidad modalidad = modalidadRepositorio.findById(vacanteEditRequest.getModalidadId())
                    .orElseThrow(() -> new RuntimeException("No se encontró la modalidad en la base de datos"));
            vacanteBd.setModalidad(modalidad);
        }else{
            throw new RuntimeException("Debe seleccionar una modalidad");
        }

        if(!vacanteEditRequest.getTecnologiasId().isEmpty()){
            List<Long> tecnologiasId = vacanteEditRequest.getTecnologiasId();
            for(Long idTech: tecnologiasId){
                if(!tecnologiaRepositorio.existsById(idTech)){
                    throw new RuntimeException("La tecnología " + tecnologiaRepositorio.findById(idTech) + " no se encontró en la Base de datos");
                }
            }
            List<Tecnologia> listaVacantes = (List<Tecnologia>) tecnologiaRepositorio.findAllById(tecnologiasId);
            vacanteBd.setTecnologiasRequeridas(listaVacantes);
        }else{
            throw new RuntimeException("Debe seleccionar al menos 1 tecnología para la vacante");
        }

        return vacanteRepositorio.save(vacanteBd);

    }

    @Override
    public void EliminarVacantePorId(Long id) {
        vacanteRepositorio.deleteById(id);
    }
}
