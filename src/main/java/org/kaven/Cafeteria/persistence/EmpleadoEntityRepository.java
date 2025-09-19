package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.exception.EmpleadoYaExisteException;
import org.kaven.Cafeteria.dominio.exception.EmpleadoNoExisteException;
import org.kaven.Cafeteria.dominio.repository.EmpleadoRepository;
import org.kaven.Cafeteria.persistence.crud.CrudEmpleadoEntity;
import org.kaven.Cafeteria.persistence.entity.EmpleadoEntity;
import org.kaven.Cafeteria.web.mapper.EmpleadoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmpleadoEntityRepository implements EmpleadoRepository {
    private final CrudEmpleadoEntity crudEmpleado;
    private final EmpleadoMapper empleadoMapper;

    public EmpleadoEntityRepository(CrudEmpleadoEntity crudEmpleado, EmpleadoMapper empleadoMapper) {
        this.crudEmpleado = crudEmpleado;
        this.empleadoMapper = empleadoMapper;
    }

    @Override
    public List<EmpleadoDto> obtenerTodoEmpleado() {
        return this.empleadoMapper.toDto(this.crudEmpleado.findAll());
    }

    @Override
    public EmpleadoDto obtenerEmpleadoPorCodigo(Long codigo) {
        EmpleadoEntity empleadoEntity = this.crudEmpleado.findById(codigo)
                .orElseThrow(() -> new EmpleadoNoExisteException(codigo.toString()));
        return this.empleadoMapper.toDto(empleadoEntity);
    }

    @Override
    public EmpleadoDto guardarEmpleado(EmpleadoDto empleadoDto) {
        if (this.crudEmpleado.findFirstByNombre(empleadoDto.name()) != null) {
            throw new EmpleadoYaExisteException(empleadoDto.name());
        }
        EmpleadoEntity empleado = this.empleadoMapper.toEntity(empleadoDto);
        this.crudEmpleado.save(empleado);
        return this.empleadoMapper.toDto(empleado);
    }

    @Override
    public EmpleadoDto modificarEmpleado(Long codigo, ModEmpleadoDto modEmpleadoDto) {
        EmpleadoEntity empleadoEntity = this.crudEmpleado.findById(codigo)
                .orElseThrow(() -> new EmpleadoNoExisteException(codigo.toString()));

        this.empleadoMapper.modificarEntityFromDto(modEmpleadoDto, empleadoEntity);
        this.crudEmpleado.save(empleadoEntity);
        return this.empleadoMapper.toDto(empleadoEntity);
    }

    @Override
    public void eliminarEmpleado(Long codigo) {
        EmpleadoEntity empleadoEntity = this.crudEmpleado.findById(codigo)
                .orElseThrow(() -> new EmpleadoNoExisteException(codigo.toString()));
        this.crudEmpleado.deleteById(codigo);
    }
}