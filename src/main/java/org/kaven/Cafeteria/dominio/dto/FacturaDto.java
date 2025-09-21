package org.kaven.Cafeteria.dominio.dto;

public record FacturaDto(
        Long id,
        Long studentId,
        Long ordersId,
        BigDecimal total,
        String paymentType
) {
}
