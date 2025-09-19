package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.ProductType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Named("generarProductType")
    public ProductType stringAProductType(String tipo) {
        if (tipo == null) return null;
        return switch (tipo.toUpperCase()) {
            case "SALCHIPAPA" -> ProductType.SALCHIPAPA;
            case "PASTEL" -> ProductType.CAKE;
            case "COMBO_PIZZA" -> ProductType.PIZZA_COMBO;
            case "AGUA_PURA" -> ProductType.AGUAPURA_BOTTLE;
            case "PEPSI" -> ProductType.PEPSI;
            case "CAFE" -> ProductType.COFFEE;
            case "GALLETA" -> ProductType.COOKIE;
            default -> null;
        };
    }

    @Named("generarProductTypeString")
    public String productTypeAString(ProductType tipo) {
        if (tipo == null) return null;
        return switch (tipo) {
            case SALCHIPAPA -> "SALCHIPAPA";
            case CAKE -> "PASTEL";
            case PIZZA_COMBO -> "COMBO_PIZZA";
            case AGUAPURA_BOTTLE -> "AGUA_PURA";
            case PEPSI -> "PEPSI";
            case COFFEE -> "CAFE";
            case COOKIE -> "GALLETA";
        };
    }

}
