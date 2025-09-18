package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;

import java.util.List;

public interface EmpleadoRepository {
    List<EmpleadoDto> obtenerTodoEmpleado();
    EmpleadoDto obtenerEmpleadoPorCodigo(Long codigo);
    EmpleadoDto guardarEmpleado(EmpleadoDto empleadoDto);
<<<<<<< Updated upstream
    EmpleadoDto modificarEmpleado(Long codigo,ModEmpleadoDto empleadoDto);
=======
    EmpleadoDto modificarEmpleado(Long codigo, ModEmpleadoDto empleadoDto);
>>>>>>> Stashed changes
    void eliminarEmpleado(Long codigo);
}
