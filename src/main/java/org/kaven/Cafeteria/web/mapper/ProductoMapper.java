package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.ProductoDto;
import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.persistence.entity.ProductoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoDto toDto(ProductoEntity entity);
    List<ProductoDto> toDto(Iterable<ProductoEntity> entities);

    @InheritInverseConfiguration
    ProductoEntity toEntity(ProductoDto dto);

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "tipo", target = "guy")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "disponibilidad", target = "availability")
    void modificarEntityFromDto(ModProductoDto dto, @MappingTarget ProductoEntity entity);
}