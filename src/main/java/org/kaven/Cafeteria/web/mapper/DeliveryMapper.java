package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.DeliveryStatus;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    @Named("stringAStatus")
    public DeliveryStatus stringAStatus(String status) {
        if (status == null) return null;
        String statusUpper = status.trim().toUpperCase();
        return switch (statusUpper) {
            case "PENDIENTE", "PENDING" -> DeliveryStatus.PENDING;
            case "ENTREGADO", "DELIVERED" -> DeliveryStatus.DELIVERED;
            case "CANCELADO", "CANCELLED" -> DeliveryStatus.CANCELLED;
            default -> throw new IllegalArgumentException("Estado de entrega no válido: " + status);
        };
    }

    @Named("statusAString")
    public String statusAString(DeliveryStatus status) {
        if (status == null) return null;
        return switch (status) {
            case PENDING -> "PENDING";
            case DELIVERED -> "DELIVERED";
            case CANCELLED -> "CANCELLED";
        };
    }
}

