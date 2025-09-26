package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.DegreeType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class DegreeMapper {

    @Named("generarCarrera")
    public DegreeType stringADegree(String degree) {
        if (degree == null) return null;
        return switch (degree.toUpperCase()) {
            case "INFORMATICA" -> DegreeType.COMPUTER_SCIENCE;
            case "DIBUJO_TECNICO" -> DegreeType.TECHNICAL_DESIGN;
            case "MECANICA" -> DegreeType.MECHANICS;
            case "ELECTRONICA" -> DegreeType.ELECTRONICS;
            case "ELECTRICIDAD" -> DegreeType.ELECTRICAL_STUDIES;
            default -> null;
        };
    }

    @Named("generarNombreCarreras")
    public String degreeAString(DegreeType degree) {
        if (degree == null) return null;
        return switch (degree) {
            case COMPUTER_SCIENCE -> "INFORMATICA";
            case TECHNICAL_DESIGN -> "DIBUJO_TECNICO";
            case MECHANICS -> "MECANICA";
            case ELECTRONICS -> "ELECTRONICA";
            case ELECTRICAL_STUDIES -> "ELECTRICIDAD";
            default -> null;
        };
    }
}
