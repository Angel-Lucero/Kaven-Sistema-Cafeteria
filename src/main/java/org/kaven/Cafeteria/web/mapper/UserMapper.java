package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.UserType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Named("generateUserType")
    public UserType stringToUserType(String type) {
        if (type == null) return null;
        return switch (type.toUpperCase()) {
            case "ADMINISTRACION" -> UserType.ADMIN;
            case "ESTUDIANTE" -> UserType.STUDENT;
            default -> null;
        };
    }

    @Named("generateUserTypeString")
    public String userTypeToString(UserType type) {
        if (type == null) return null;
        return switch (type) {
            case ADMIN -> "ADMINISTRACION";
            case STUDENT -> "ESTUDIANTE";
        };
    }
}



