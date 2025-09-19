package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;
import org.kaven.Cafeteria.persistence.entity.EstudianteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PedidoMapper.class})
public interface EstudianteMapper {

    EstudianteDto toDto(EstudianteEntity entity);
    List<EstudianteDto> toDto(Iterable<EstudianteEntity> entities);

    @InheritInverseConfiguration
    EstudianteEntity toEntity(EstudianteDto dto);

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "correo", target = "mail")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "carrera", target = "career")
    void modificarEntityFromDto(ModEstudianteDto dto, @MappingTarget EstudianteEntity entity);
}