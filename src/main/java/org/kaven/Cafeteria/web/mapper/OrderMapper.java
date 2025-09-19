package org.kaven.Cafeteria.web.mapper;


import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import org.kaven.Cafeteria.dominio.OrderStatus;

@Component
public class OrderMapper {

    @Named("generarEstado")
    public OrderStatus stringAEstado(String estado) {
        if (estado == null) return null;
        return switch (estado.toUpperCase()) {
            case "PENDIENTE" -> OrderStatus.EARRING;
            case "EN_PROCESO" -> OrderStatus.IN_PROGRESS;
            case "ENTREGADO" -> OrderStatus.DELIVERED;
            case "CANCELADO" -> OrderStatus.CANCELED;
            default -> null;
        };
    }

    @Named("generarEstadoString")
    public String estadoAString(OrderStatus estado) {
        if (estado == null) return null;
        return switch (estado) {
            case EARRING -> "PENDIENTE";
            case IN_PROGRESS -> "EN_PROCESO";
            case DELIVERED -> "ENTREGADO";
            case CANCELED -> "CANCELADO";
        };
    }

}
