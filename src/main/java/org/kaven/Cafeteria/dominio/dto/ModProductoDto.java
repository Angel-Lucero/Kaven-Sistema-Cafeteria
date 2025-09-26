package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record ModProductoDto(
        @NotBlank(message = "El nombre del producto es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String name,

        @NotBlank(message = "El tipo de producto es obligatorio")
        @Size(max = 50, message = "El tipo no puede superar los 50 caracteres")
        String type,

        @NotNull(message = "El precio es obligatorio")
        @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
        BigDecimal price,

        @NotNull(message = "La disponibilidad es obligatoria")
        Boolean availability
) {
}