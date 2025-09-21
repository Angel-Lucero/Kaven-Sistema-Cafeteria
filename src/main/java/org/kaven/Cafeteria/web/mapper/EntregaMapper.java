package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.EntregasDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregasDto;
import org.kaven.Cafeteria.persistence.entity.EntregaEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = { DeliveryMapper.class })
public interface EntregaMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "statusAString")
    EntregasDto toDto(EntregaEntity entity);

    List<EntregasDto> toDto(Iterable<EntregaEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "stringAStatus")
    EntregaEntity toEntity(EntregasDto dto);

    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "employeeId", target = "employee.id")
    @Mapping(source = "deliveryStatus", target = "deliveryStatus", qualifiedByName = "stringAStatus")
    void modificarEntityFromDto(ModEntregasDto dto, @MappingTarget EntregaEntity entity);
}
