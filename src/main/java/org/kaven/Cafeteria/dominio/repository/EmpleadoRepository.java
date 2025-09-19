package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;

import java.util.List;

public interface EmpleadoRepository {
    List<EmpleadoDto> obtenerTodoEmpleado();
    EmpleadoDto obtenerEmpleadoPorCodigo(Long codigo);
    EmpleadoDto guardarEmpleado(EmpleadoDto empleadoDto);
    EmpleadoDto modificarEmpleado(Long codigo,ModEmpleadoDto empleadoDto);
    void eliminarEmpleado(Long codigo);
}
