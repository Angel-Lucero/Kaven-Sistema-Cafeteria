package org.kaven.Cafeteria.dominio.repository;

public interface EmpleadoRepository {
    List<EmpleadoDto> obtenerTodoEmpleado();
    EmpleadoDto obtenerEmpleadoPorCodigo(Long codigo);
    EmpleadoDto guardarEmpleado(EmpleadoDto empleadoDto);
    EmpleadoDto modificarEmpleado(Long codigo,ModEmpleadoDto empleadoDto);
    void eliminarEmpleado(Long codigo);
}
