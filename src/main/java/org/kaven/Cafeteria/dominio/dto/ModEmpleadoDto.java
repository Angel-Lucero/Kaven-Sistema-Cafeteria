package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModEmpleadoDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 120, message = "El nombre no puede superar los 120 caracteres")
        String name,

        @NotBlank(message = "El rol es obligatorio")
        @Size(max = 50, message = "El rol no puede superar los 50 caracteres")
        String role,

        @NotBlank(message = "El turno es obligatorio")
        @Size(max = 30, message = "El turno no puede superar los 30 caracteres")
        String shift
) {
}