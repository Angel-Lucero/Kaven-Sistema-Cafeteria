package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.exception.EstudianteYaExisteException;
import org.kaven.Cafeteria.persistence.crud.CrudEstudianteEntity;
import org.kaven.Cafeteria.persistence.entity.EmpleadoEntity;
import org.kaven.Cafeteria.persistence.entity.UsuarioEntity;
import org.kaven.Cafeteria.web.mapper.EstudianteMapper;

import java.util.List;

public class EmpleadoEntityRepository {
    private final CrudEstudianteEntity crudEmpleado;
    private final EstudianteMapper empleadoMapper;

    public EmpleadoEntityRepository(CrudEstudianteEntity crudEmpleado, EstudianteMapper empleadoMapper) {
        this.crudEmpleado = crudEmpleado;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public List<EmpleadoDto> obtenerTodo() {
        return this.empleadoMapper.toDto(this.crudEmpleado.findAll());
    }

    @Override
    public EmpleadoDto obtenerEmpleadoPorCodigo(Long codigo) {
        EmpleadoEntity empleadoEntity = this.crudEmpleado.findById(codigo).orElse(null);
        return this.empleadoMapper.toDto(this.crudEmpleado.findById(codigo).orElse(null));
        // Para despues cuando este la excepcion de UsuarioNoExiste
//        if (usuarioEntity == null) throw new UsuarioNoExisteException(codigo);
//        else {
//            return this.usuarioMapper.toDto(this.crudUsuario.findById(codigo).orElse(null));
//        }
    }

    @Override
    public EmpleadoDto guardarEmpleado(EmpleadoDto empleadoDto) {

        if (this.crudEmpleado.findFirstByNombre(empleadoDto.name()) != null){
            throw new EstudianteYaExisteException(empleadoDto.name());
        }
        EmpleadoEntity empleado = this.empleadoMapper.toEntity(empleadoDto);
        this.crudEmpleado.save(empleado);
        return this.empleadoMapper.toDto(empleado);
    }

    @Override
    public EmpleadoDto modificarEmpleado(Long codigo, ModEmpleadoDto usuarioDto) {
        EmpleadoEntity empleadoEntity = this.crudEmpleado.findById(codigo).orElse(null);
        //todos los setters de empleados
        this.crudEmpleado.save(empleadoEntity);
        return this.empleadoMapper.toDto(empleadoEntity);

    }

    @Override
    public void eliminarEmpleado(Long codigo) {
        UsuarioEntity usuarioEntity = this.crudEmpleado.findById(codigo).orElse(null);
        this.crudEmpleado.deleteById(codigo);

        //falta la excepcion de UsuarioNoExiste
        /*if (usuarioEntity == null){
            throw new UsuarioNoExisteException(codigo);
        } else {
            this.crudUsuario.deleteById(codigo);
        }*/
    }





}
