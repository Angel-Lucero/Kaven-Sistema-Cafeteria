package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ModPedidoDto(
        @NotNull(message = "El id del estudiante es obligatorio")
        Long studentid,

        @NotNull(message = "La fecha del pedido es obligatoria")
        @PastOrPresent(message = "La fecha del pedido no puede ser futura")
        LocalDate orderdate,

        @NotNull(message = "El total es obligatorio")
        @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
        BigDecimal total,

        @NotBlank(message = "El estado del pedido es obligatorio")
        String state
) {
}
