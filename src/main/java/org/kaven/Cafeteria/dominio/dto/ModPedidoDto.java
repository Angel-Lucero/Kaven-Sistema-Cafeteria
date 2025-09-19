package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ModPedidoDto(
        @NotNull(message = "El ID del estudiante es obligatorio")
        Long studentid,

        @NotNull(message = "La fecha del pedido es obligatoria")
        LocalDate orderdate,

        @NotNull(message = "El total es obligatorio")
        @Positive(message = "El total debe ser positivo")
        BigDecimal total,

        @NotBlank(message = "El estado es obligatorio")
        String state
) {
}