package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository {
    List<EmpleadoDto> obtenerTodoEmpleado();
    EmpleadoDto obtenerEmpleadoPorCodigo(Long codigo);
    EmpleadoDto guardarEmpleado(EmpleadoDto empleadoDto);
    EmpleadoDto modificarEmpleado(Long codigo,ModEmpleadoDto empleadoDto);
    void eliminarEmpleado(Long codigo);
}
