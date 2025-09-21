package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.FacturaDto;
import org.kaven.Cafeteria.dominio.dto.ModFacturaDto;
import org.kaven.Cafeteria.persistence.entity.FacturaEntity;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {InvoiceMapper.class})
public interface FacturaMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "paymentType", target = "paymentType", qualifiedByName = "paymentAString")
    FacturaDto toDto(FacturaEntity entity);

    List<FacturaDto> toDto(Iterable<FacturaEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "paymentType", target = "paymentType", qualifiedByName = "stringAPayment")
    FacturaEntity toEntity(FacturaDto dto);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "paymentType", target = "paymentType", qualifiedByName = "stringAPayment")
    void modificarEntityFromDto(ModFacturaDto dto, @MappingTarget FacturaEntity entity);
}

