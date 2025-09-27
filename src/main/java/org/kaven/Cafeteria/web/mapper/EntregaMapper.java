package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.kaven.Cafeteria.persistence.entity.EntregaEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = { DeliveryMapper.class })
public interface EntregaMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "statusAString")
    EntregaDto toDto(EntregaEntity entity);

    List<EntregaDto> toDto(Iterable<EntregaEntity> entities);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "stringAStatus")
    EntregaEntity toEntity(EntregaDto dto);

    @Mapping(target = "order", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "stringAStatus")
    void modificarEntityFromDto(ModEntregaDto dto, @MappingTarget EntregaEntity entity);
}