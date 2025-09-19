package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.ModUsuarioDto;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsuarioRepository {
    List<UsuarioDto> obtenerTodoUsuario();
    UsuarioDto obtenerUsuarioPorCodigo(String codigo);
    UsuarioDto guardarUsuario(UsuarioDto usuarioDto);
    UsuarioDto modificarUsuario(String codigo, ModUsuarioDto usuarioDto);
    void eliminarUsuario(String codigo);
}