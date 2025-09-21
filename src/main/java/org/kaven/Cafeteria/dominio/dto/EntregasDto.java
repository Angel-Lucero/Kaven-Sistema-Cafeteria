package org.kaven.Cafeteria.dominio.dto;

import java.util.LocalDate;

public record EntregasDto(
        Long id,
        Long OrderId,
        Long EmployeeId,
        String deliveryStatus,
        LocalDate devliveryDate
) {
}
