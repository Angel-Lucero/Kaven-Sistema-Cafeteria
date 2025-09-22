package org.kaven.Cafeteria.dominio.dto;

import java.time.LocalDate;

public record EntregasDto(
        Long id,
        Long OrderId,
        Long EmployeeId,
        String deliveryStatus,
        LocalDate devliveryDate
) {
}
