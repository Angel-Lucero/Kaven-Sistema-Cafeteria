package org.kaven.Cafeteria.dominio.dto;

import java.math.BigDecimal;

public record ProductoDto(
        Long id,
        String name,
        String guy, // pan, pastel, combo
        BigDecimal price,
        Boolean availability
) {
}