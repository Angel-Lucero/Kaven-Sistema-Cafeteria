package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.CarrerasDto;
import org.kaven.Cafeteria.dominio.dto.ModCarrerasDto;
import org.kaven.Cafeteria.persistence.entity.CarreraEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = DegreeMapper.class)
public interface CarreraMapper {

    @Mapping(source = "degreeName", target = "degreeName", qualifiedByName = "degreeAString")
    CarrerasDto toDto(CarreraEntity entity);
    List<CarrerasDto> toDto(Iterable<CarreraEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "degreeName", target = "degreeName", qualifiedByName = "stringADegree")
    CarreraEntity toEntity(CarrerasDto dto);

    @Mapping(source = "degreeName", target = "degreeName", qualifiedByName = "stringADegree")
    void modificarEntityFromDto(ModCarrerasDto dto, @MappingTarget CarreraEntity entity);
}
