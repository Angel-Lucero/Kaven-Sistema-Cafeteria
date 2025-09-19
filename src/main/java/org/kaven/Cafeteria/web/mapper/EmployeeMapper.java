package org.kaven.Cafeteria.web.mapper;

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
            case "ADMINISTRADOR" -> EmployeeType.ADMINISTRATION;
            case "REPARTIDOR" -> EmployeeType.DEALER;
            case "BARISTA" -> EmployeeType.ATM; // Asumiendo que barista es cajero
            default -> null;
        };
    }

    @Named("generarRolEmpleado")
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