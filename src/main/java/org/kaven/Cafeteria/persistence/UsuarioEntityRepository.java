package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModUsuarioDto;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.exception.UsuarioNoExisteException;
import org.kaven.Cafeteria.dominio.exception.UsuarioYaExisteException;
import org.kaven.Cafeteria.dominio.repository.UsuarioRepository;
import org.kaven.Cafeteria.persistence.crud.CrudUsuarioEntity;
import org.kaven.Cafeteria.persistence.entity.UsuarioEntity;
import org.kaven.Cafeteria.web.mapper.UsuarioMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioEntityRepository implements UsuarioRepository {
    private final CrudUsuarioEntity crudUsuario;
    private final UsuarioMapper usuarioMapper;

    public UsuarioEntityRepository(CrudUsuarioEntity crudUsuario, UsuarioMapper usuarioMapper) {
        this.crudUsuario = crudUsuario;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioDto> obtenerTodoUsuario() {
        return this.usuarioMapper.toDto(this.crudUsuario.findAll());
    }

    @Override
    public UsuarioDto obtenerUsuarioPorCodigo(String codigo) {
        UsuarioEntity usuarioEntity = this.crudUsuario.findById(Long.valueOf(codigo))
                .orElseThrow(() -> new UsuarioNoExisteException(codigo));
        return this.usuarioMapper.toDto(usuarioEntity);
    }

    @Override
    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {
        if (this.crudUsuario.existsById(Long.valueOf(usuarioDto.mail()))) {
            throw new UsuarioYaExisteException(usuarioDto.mail());
        }

        UsuarioEntity usuario = this.usuarioMapper.toEntity(usuarioDto);
        UsuarioEntity usuarioGuardado = this.crudUsuario.save(usuario);
        return this.usuarioMapper.toDto(usuarioGuardado);
    }

    @Override
    public UsuarioDto modificarUsuario(String codigo, ModUsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = this.crudUsuario.findById(Long.valueOf(codigo))
                .orElseThrow(() -> new UsuarioNoExisteException(codigo));

        usuarioEntity.setCorreo(usuarioDto.mail());
        usuarioEntity.setContrasena(usuarioDto.password());

        UsuarioEntity usuarioActualizado = this.crudUsuario.save(usuarioEntity);
        return this.usuarioMapper.toDto(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(String codigo) {
        if (!this.crudUsuario.existsById(Long.valueOf(codigo))) {
            throw new UsuarioNoExisteException(codigo);
        }
        this.crudUsuario.deleteById(Long.valueOf(codigo));
    }
}