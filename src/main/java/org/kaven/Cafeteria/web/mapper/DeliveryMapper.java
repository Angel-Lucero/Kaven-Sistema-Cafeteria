package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.DeliveryStatus;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {

    @Named("stringAStatus")
    public DeliveryStatus stringAStatus(String status) {
        if (status == null) return null;
        return switch (status.toUpperCase()) {
            case "PENDIENTE" -> DeliveryStatus.PENDING;
            case "ENTREGADO" -> DeliveryStatus.DELIVERED;
            case "CANCELADO" -> DeliveryStatus.CANCELLED;
            default -> null;
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

