package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.persistence.crud.CrudUsuarioEntity;
import org.kaven.Cafeteria.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioEntityRepository {
    private final CrudUsuarioEntity crudUsuario;
    private final UsuarioMapper usuarioMapper;

    public UsuarioEntityRepository(CrudUsuarioEntity crudUsuario, UsuarioMapper usuarioMapper) {
        this.crudUsuario = crudUsuario;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<UsuarioDto> obtenerTodo() {
        return this.usuarioMapper.toDto(this.crudUsuario.findAll());
    }

    @Override
    public UsuarioDto obtenerUsuarioPorCodigo(Long codigo) {
        UsuarioEntity usuarioEntity = this.crudUsuario.findById(codigo).orElse(null);
        return this.usuarioMapper.toDto(this.crudUsuario.findById(codigo).orElse(null));
        // Para despues cuando este la excepcion de UsuarioNoExiste
//        if (usuarioEntity == null) throw new UsuarioNoExisteException(codigo);
//        else {
//            return this.usuarioMapper.toDto(this.crudUsuario.findById(codigo).orElse(null));
//        }
    }

    @Override
    public UsuarioDto guardarUsuario(UsuarioDto usuarioDto) {

        if (this.crudUsuario.findFirstByNombre(usuarioDto.name()) != null){
            throw new UsuarioYaExisteException(usuarioDto.name());
        }
        UsuarioEntity usuario = this.usuarioMapper.toEntity(usuarioDto);
        this.crudUsuario.save(usuario);
        return this.usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto modificarUsuario(Long codigo, ModUsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = this.crudUsuario.findById(codigo).orElse(null);
        suarioEntity.setNombre(usuarioDto.name());
        usuarioEntity.setApellido(usuarioDto.apellido());
        usuarioEntity.setEmail(usuarioDto.email());
        usuarioEntity.setTelefono(usuarioDto.telefono());
        usuarioEntity.setDireccion(usuarioDto.direccion());
        this.crudUsuario.save(usuarioEntity);
        return this.usuarioMapper.toDto(usuarioEntity);

        /* para cuando este la excepcion de UsuarioNoExiste

        if (usuarioEntity == null) throw new UsuarioNoExisteException(codigo);
        else {
            usuarioEntity.setNombre(usuarioDto.name());
            usuarioEntity.setApellido(usuarioDto.apellido());
            usuarioEntity.setEmail(usuarioDto.email());
            usuarioEntity.setTelefono(usuarioDto.telefono());
            usuarioEntity.setDireccion(usuarioDto.direccion());
            this.crudUsuario.save(usuarioEntity);
            return this.usuarioMapper.toDto(usuarioEntity);
        }*/
    }

    @Override
    public void eliminarUsuario(Long codigo) {
        UsuarioEntity usuarioEntity = this.crudUsuario.findById(codigo).orElse(null);
        this.crudUsuario.deleteById(codigo);

        //falta la excepcion de UsuarioNoExiste
        /*if (usuarioEntity == null){
            throw new UsuarioNoExisteException(codigo);
        } else {
            this.crudUsuario.deleteById(codigo);
        }*/
    }
}
