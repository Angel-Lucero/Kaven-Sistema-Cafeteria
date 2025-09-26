package org.kaven.Cafeteria.dominio.dto;

public record UsuarioDto (
        Long id,
        String mail,
        String password,
        String usertype,
        Long studentid
) {
}
