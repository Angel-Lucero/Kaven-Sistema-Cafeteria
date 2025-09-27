package org.kaven.Cafeteria.dominio.dto;

import java.time.LocalDate;


public record EntregaDto(
        Long id,
        Long orderId,
        Long employeeId,
        String deliveryStatus,
        LocalDate deliveryDate
) {
}
