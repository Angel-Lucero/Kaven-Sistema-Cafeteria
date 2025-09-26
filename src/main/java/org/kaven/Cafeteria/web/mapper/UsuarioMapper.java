package org.kaven.Cafeteria.web.mapper;

import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.dto.ModUsuarioDto;
import org.kaven.Cafeteria.persistence.entity.UsuarioEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "correo", target = "mail")
    @Mapping(source = "contrasena", target = "password")
    @Mapping(source = "rol", target = "usertype")
    @Mapping(source = "estudiante.id", target = "studentid")
    UsuarioDto toDto(UsuarioEntity entity);

    List<UsuarioDto> toDto(Iterable<UsuarioEntity> entities);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    UsuarioEntity toEntity(UsuarioDto dto);

    @Mapping(source = "mail", target = "correo")
    @Mapping(source = "password", target = "contrasena")
    @Mapping(source = "usertype", target = "rol")
    void modificarEntityFromDto(ModUsuarioDto dto, @MappingTarget UsuarioEntity entity);
}