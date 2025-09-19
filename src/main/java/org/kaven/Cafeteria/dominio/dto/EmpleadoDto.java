package org.kaven.Cafeteria.dominio.dto;

public record EmpleadoDto(
        Long id,
        String name,
        String role,   // barista, cajero, administrador
        String shift  // mañana, tarde, noche
) {
}
