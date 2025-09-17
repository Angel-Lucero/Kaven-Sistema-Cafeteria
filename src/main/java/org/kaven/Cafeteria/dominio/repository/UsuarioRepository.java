package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.UsuarioDto;

import java.util.List;

public interface UsuarioRepository {
    List<UsuarioDto> obtenerTodo();
    UsuarioDto obtenerUsuarioPorCodigo(Long codigo);
    UsuarioDto guardarUsuario(UsuarioDto usuarioDto);
    UsuarioDto modificarUsuario(Long codigo,ModUsuarioDto usuarioDto);
    void eliminarUsuario(Long codigo);
}
