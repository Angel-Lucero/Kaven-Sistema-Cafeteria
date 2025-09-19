package org.kaven.Cafeteria.dominio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModEstudianteDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 120, message = "El nombre no puede superar los 120 caracteres")
        String name,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "Debe ser un correo válido")
        String mail,

        @NotBlank(message = "El teléfono es obligatorio")
        @Size(max = 20, message = "El teléfono no puede superar los 20 caracteres")
        String phone,

        @NotBlank(message = "La carrera es obligatoria")
        @Size(max = 80, message = "La carrera no puede superar los 80 caracteres")
        String career
) {
}