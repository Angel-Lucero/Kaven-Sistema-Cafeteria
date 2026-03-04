package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record FacturaDto(
        Long id,

        @NotNull(message = "El ID del estudiante es obligatorio")
        Long studentId,

        @NotNull(message = "El ID de la orden es obligatorio")
        Long ordersId,

        @NotNull(message = "El total es obligatorio")
        @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
        BigDecimal total,

        @NotBlank(message = "El tipo de pago es obligatorio")
        String paymentType
) {
}
