package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.CarreraDto;
import org.kaven.Cafeteria.dominio.dto.ModCarreraDto;
import org.kaven.Cafeteria.persistence.entity.CarreraEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = DegreeMapper.class)
public interface CarreraMapper {

    @Mapping(source = "degreeName", target = "degreeName", qualifiedByName = "generarNombreCarreras")
    CarreraDto toDto(CarreraEntity entity);

    List<CarreraDto> toDto(Iterable<CarreraEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "degreeName", target = "degreeName", qualifiedByName = "generarCarrera")
    CarreraEntity toEntity(CarreraDto dto);

    @Mapping(source = "degreeName", target = "degreeName", qualifiedByName = "generarCarrera")
    void modificarEntityFromDto(ModCarreraDto dto, @MappingTarget CarreraEntity entity);
}
