package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record ModEntregasDto(
        @NotNull(message = "El ID de la orden es obligatorio")
        Long orderId,

        @NotNull(message = "El ID del empleado es obligatorio")
        Long employeeId,

        @NotBlank(message = "El estado de la entrega es obligatorio")
        String deliveryStatus,

        @NotNull(message = "La fecha de entrega es obligatoria")
        LocalDate deliveryDate
) {
}