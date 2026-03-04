package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.PaymentType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper {

    @Named("stringAPayment")
    public PaymentType stringAPayment(String type) {
        if (type == null) return null;
        return switch (type.trim().toUpperCase()) {
            case "EFECTIVO" -> PaymentType.CASH;
            case "TARJETA_CREDITO" -> PaymentType.CREDIT_CARD;
            case "TRANSFERENCIA" -> PaymentType.BANK_TRANSFER;
            case "PAGO_MOVIL" -> PaymentType.MOBILE_PAYMENT;
            default -> throw new IllegalArgumentException("Tipo de pago no reconocido: " + type);
        };
    }

    @Named("paymentAString")
    public String paymentAString(PaymentType type) {
        if (type == null) return null;
        return switch (type) {
            case CASH -> "EFECTIVO";
            case CREDIT_CARD -> "TARJETA_CREDITO";
            case BANK_TRANSFER -> "TRANSFERENCIA";
            case MOBILE_PAYMENT -> "PAGO_MOVIL";
        };
    }
}
