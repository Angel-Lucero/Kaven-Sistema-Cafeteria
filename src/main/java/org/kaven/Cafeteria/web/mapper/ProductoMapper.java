package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.ProductoDto;
import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.persistence.entity.ProductoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ProductoMapper {

    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "tipo", target = "type")
    @Mapping(source = "precio", target = "price")
    @Mapping(source = "disponibilidad", target = "availability")
    ProductoDto toDto(ProductoEntity entity);

    List<ProductoDto> toDto(Iterable<ProductoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    ProductoEntity toEntity(ProductoDto dto);

    @Mapping(source = "name", target = "nombre")
    @Mapping(source = "type", target = "tipo")
    @Mapping(source = "price", target = "precio")
    @Mapping(source = "availability", target = "disponibilidad")
    void modificarEntityFromDto(ModProductoDto dto, @MappingTarget ProductoEntity entity);
}