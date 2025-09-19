package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.PedidoDto;
import org.kaven.Cafeteria.dominio.dto.ModPedidoDto;
import org.kaven.Cafeteria.persistence.entity.PedidoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface PedidoMapper {

    @Mapping(source = "estudiante.id", target = "studentid")
    @Mapping(source = "fechaPedido", target = "orderdate")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "estado", target = "state")
    PedidoDto toDto(PedidoEntity entity);

    List<PedidoDto> toDto(Iterable<PedidoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studentid", target = "estudiante.id")
    PedidoEntity toEntity(PedidoDto dto);

    @Mapping(source = "studentid", target = "estudiante.id")
    @Mapping(source = "orderdate", target = "fechaPedido")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "state", target = "estado")
    void modificarEntityFromDto(ModPedidoDto dto, @MappingTarget PedidoEntity entity);
}