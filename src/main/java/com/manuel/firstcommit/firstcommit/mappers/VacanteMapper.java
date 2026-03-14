package com.manuel.firstcommit.firstcommit.mappers;

import com.manuel.firstcommit.firstcommit.models.dto.request.VacanteCreateRequest;
import com.manuel.firstcommit.firstcommit.models.dto.request.VacanteEditRequest;
import com.manuel.firstcommit.firstcommit.models.dto.response.VacanteCreateResponse;
import com.manuel.firstcommit.firstcommit.models.entities.Vacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VacanteMapper {

    //Para actualizar
    void updateFromDtoToEntity(VacanteEditRequest dto, @MappingTarget Vacante vacante);

    //Para registrar vacante
    //Mapeamos de Dto a Entidad
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tecnologiasRequeridas", ignore = true)
    @Mapping(target = "activa", ignore = true)
    @Mapping(target = "modalidad", ignore = true)
    Vacante createToEntity(VacanteCreateRequest vacanteCreateRequest);

    VacanteCreateResponse createToDto(Vacante vacante);


}
