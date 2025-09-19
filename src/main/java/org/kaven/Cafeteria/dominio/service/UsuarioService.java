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

    public List<UsuarioDto> obtenerTodoUsuario() {
        return this.usuarioRepository.obtenerTodoUsuario();
    }

    public UsuarioDto obtenerUsuarioPorCodigo(Long codigo) {
        return this.usuarioRepository.obtenerUsuarioPorCodigo(codigo);
    }

    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
        return this.usuarioRepository.guardarUsuario(usuarioDto);
    }

    public UsuarioDto modificarUsuario(Long codigo, ModUsuarioDto modUsuarioDto) {
        return this.usuarioRepository.modificarUsuario(codigo, modUsuarioDto);
    }

    public void eliminarUsuario(Long codigo) {
        this.usuarioRepository.eliminarUsuario(codigo);
    }
}