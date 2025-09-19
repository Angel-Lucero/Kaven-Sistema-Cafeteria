package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.persistence.entity.UsuarioEntity;
import org.kaven.Cafeteria.persistence.entity.EstudianteEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "correo", target = "mail")
    @Mapping(source = "contrasena", target = "password")
    @Mapping(source = "estudiante.id", target = "studentid")
    @Mapping(target = "id", ignore = true) // porque en UsuarioEntity no existe un campo id
    UsuarioDto toUsuarioDto(UsuarioEntity usuarioEntity);

    @Mapping(source = "mail", target = "correo")
    @Mapping(source = "password", target = "contrasena")
    @Mapping(source = "studentid", target = "estudiante", qualifiedByName = "mapStudentIdToEntity")
    UsuarioEntity toUsuarioEntity(UsuarioDto usuarioDto);

    @Named("mapStudentIdToEntity")
    default EstudianteEntity mapStudentIdToEntity(Long id) {
        if (id == null) return null;
        EstudianteEntity estudiante = new EstudianteEntity();
        estudiante.setId(id);
        return estudiante;
    }
}
