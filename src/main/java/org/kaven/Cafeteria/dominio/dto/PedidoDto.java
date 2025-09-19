package org.kaven.Cafeteria.dominio.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoDto(
        Long id,
        Long studentid,
        LocalDate orderdate,
        BigDecimal total,
        String state
) {
}