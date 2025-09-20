package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModCarrerasDto(
        @NotBlank(message = "El nombre de la carrera es obligatorio")
        @Size(max = 120, message = "El nombre de la carrera no puede superar los 120 caracteres")
        String degreeName
) {
}

