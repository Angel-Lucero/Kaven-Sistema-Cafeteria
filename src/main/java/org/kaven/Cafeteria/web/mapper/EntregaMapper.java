package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.kaven.Cafeteria.persistence.entity.EntregaEntity;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Mapper(componentModel = "spring", uses = { DeliveryMapper.class })
public abstract class EntregaMapper {

    @Autowired
    protected DeliveryMapper deliveryMapper;

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "statusAString")
    public abstract EntregaDto toDto(EntregaEntity entity);

    public abstract List<EntregaDto> toDto(Iterable<EntregaEntity> entities);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "deliveryStatus", expression = "java(deliveryMapper.stringAStatus(dto.deliveryStatus()))")
    public abstract EntregaEntity toEntity(EntregaDto dto);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "deliveryStatus", expression = "java(deliveryMapper.stringAStatus(dto.deliveryStatus()))")
    public abstract void modificarEntityFromDto(ModEntregaDto dto, @MappingTarget EntregaEntity entity);
}