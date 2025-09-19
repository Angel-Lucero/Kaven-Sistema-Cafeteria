package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.PedidoDto;
import org.kaven.Cafeteria.dominio.dto.ModPedidoDto;
import org.kaven.Cafeteria.persistence.entity.PedidoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    @Mapping(source = "estudiante.id", target = "idEstudiante")
    PedidoDto toDto(PedidoEntity entity);
    List<PedidoDto> toDto(Iterable<PedidoEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "idEstudiante", target = "estudiante.id")
    PedidoEntity toEntity(PedidoDto dto);

    @Mapping(source = "idEstudiante", target = "estudiante.id")
    @Mapping(source = "fechaPedido", target = "fechaPedido")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "estado", target = "estado")
    void modificarEntityFromDto(ModPedidoDto dto, @MappingTarget PedidoEntity entity);
}