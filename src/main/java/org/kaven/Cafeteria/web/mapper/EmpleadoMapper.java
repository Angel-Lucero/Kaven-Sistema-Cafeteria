package org.kaven.Cafeteria.web.mapper;

<<<<<<< Updated upstream
import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;
import org.kaven.Cafeteria.persistence.entity.EmpleadoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    EmpleadoDto toDto(EmpleadoEntity entity);
    List<EmpleadoDto> toDto(Iterable<EmpleadoEntity> entities);

    @InheritInverseConfiguration
    EmpleadoEntity toEntity(EmpleadoDto dto);

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "rol", target = "role")
    @Mapping(source = "turno", target = "shift")
    void modificarEntityFromDto(ModEmpleadoDto dto, @MappingTarget EmpleadoEntity entity);
}
=======
public interface EmpleadoMapper {
}
>>>>>>> Stashed changes
