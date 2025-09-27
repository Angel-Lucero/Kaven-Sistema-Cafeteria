package org.kaven.Cafeteria.dominio.dto;

import java.math.BigDecimal;

public record FacturaDto(
        Long id,
        Long studentId,
        Long ordersId,
        BigDecimal total,
        String paymentType
) {
}
