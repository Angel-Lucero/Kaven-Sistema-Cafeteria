package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.ModUsuarioDto;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.exception.UsuarioYaExisteException;
import org.kaven.Cafeteria.dominio.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDto> obtenerTodo() {
        return this.usuarioRepository.obtenerTodo();
    }

    public UsuarioDto obtenerUsuarioPorCodigo(Long codigo) {
        return this.usuarioRepository.obtenerUsuarioPorCodigo(codigo);
    }

    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
        validarUsuario(usuarioDto);
        return this.usuarioRepository.guardarUsuario(usuarioDto);
    }

    public UsuarioDto modificarUsuario(Long codigo, ModUsuarioDto modUsuarioDto) {
        validarModUsuario(modUsuarioDto);
        return this.usuarioRepository.modificarUsuario(codigo, modUsuarioDto);
    }

    public void eliminarUsuario(Long codigo) {
        this.usuarioRepository.eliminarUsuario(codigo);
    }

    private void validarUsuario(UsuarioDto usuarioDto) {
        if (usuarioDto.nombre() == null || usuarioDto.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del usuario no puede estar vacío.");
        }
        if (usuarioDto.email() == null || !usuarioDto.email().contains("@")) {
            throw new IllegalArgumentException("El email del usuario no es válido.");
        }

        UsuarioDto existente = usuarioRepository.obtenerUsuarioPorCodigo(usuarioDto.codigo());
        if (existente != null) {
            throw new UsuarioYaExisteException(usuarioDto.codigo().toString());
        }
    }

    private void validarModUsuario(ModUsuarioDto modUsuarioDto) {
        if (modUsuarioDto.email() != null && !modUsuarioDto.email().contains("@")) {
            throw new IllegalArgumentException("El email del usuario modificado no es válido.");
        }
    }
}