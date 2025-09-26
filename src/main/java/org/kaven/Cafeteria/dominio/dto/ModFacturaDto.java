package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record ModFacturaDto(
        @NotNull(message = "El ID del estudiante es obligatorio")
        Long studentId,

        @NotNull(message = "El ID de la orden es obligatorio")
        Long orderId,

        @NotNull(message = "El total es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
        BigDecimal total,

        @NotBlank(message = "El tipo de pago es obligatorio")
        String paymentType
) {
}
