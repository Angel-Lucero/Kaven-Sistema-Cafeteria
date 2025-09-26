package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.ModUsuarioDto;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDto> obtenerTodoUsuario() {
        return usuarioRepository.obtenerTodoUsuario();
    }

    public UsuarioDto obtenerUsuarioPorCodigo(String codigo) {
        return usuarioRepository.obtenerUsuarioPorCodigo(codigo);
    }

    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
        return usuarioRepository.guardarUsuario(usuarioDto);
    }

    public UsuarioDto modificarUsuario(String codigo, ModUsuarioDto modUsuarioDto) {
        return usuarioRepository.modificarUsuario(codigo, modUsuarioDto);
    }

    public void eliminarUsuario(String codigo) {
        usuarioRepository.eliminarUsuario(codigo);
    }
}