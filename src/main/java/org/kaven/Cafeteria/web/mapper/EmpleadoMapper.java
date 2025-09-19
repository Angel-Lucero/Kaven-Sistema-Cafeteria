package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;
import org.kaven.Cafeteria.persistence.entity.EmpleadoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = EmployeeMapper.class)
public interface EmpleadoMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "rol", target = "role")
    @Mapping(source = "turno", target = "shift")
    EmpleadoDto toDto(EmpleadoEntity entity);

    List<EmpleadoDto> toDto(Iterable<EmpleadoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    EmpleadoEntity toEntity(EmpleadoDto dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "role", target = "rol")
    @Mapping(source = "shift", target = "turno")
    void modificarEntityFromDto(ModEmpleadoDto dto, @MappingTarget EmpleadoEntity entity);
}