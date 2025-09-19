package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.EmployeeType;
import org.kaven.Cafeteria.dominio.EmployeeType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    @Named("generarRol")
    public EmployeeType stringARol(String rol) {
        if (rol == null) return null;
        return switch (rol.toUpperCase()) {
            case "CAJERO" -> EmployeeType.ATM;
            case "COCINERO" -> EmployeeType.CHEF;
            case "ADMINISTRATION" -> EmployeeType.ADMINISTRATION;
            case "REPARTIDOR" -> EmployeeType.DEALER;
            default -> null;
        };
    }

    @Named("generarRolEmple595ado")
    public String rolAString(EmployeeType rol) {
        if (rol == null) return null;
        return switch (rol) {
            case ATM -> "CAJERO";
            case CHEF -> "COCINERO";
            case DEALER -> "REPARTIDOR";
            case ADMINISTRATION -> "ADMINISTRADOR";
            default -> null;
        };
    }
}
