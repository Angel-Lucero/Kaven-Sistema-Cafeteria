package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;
import org.kaven.Cafeteria.dominio.repository.EmpleadoRepository;
import org.kaven.Cafeteria.dominio.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<EmpleadoDto> obtenerTodoEmpleado() {
        return this.empleadoRepository.obtenerTodoEmpleado();
    }

    public EmpleadoDto obtenerEmpleadoPorCodigo(Long codigo) {
        return this.empleadoRepository.obtenerEmpleadoPorCodigo(codigo);
    }

    public EmpleadoDto guardarEmpleado(EmpleadoDto empleadoDto) {
        return this.empleadoRepository.guardarEmpleado(empleadoDto);
    }

    public EmpleadoDto modificarEmpleado(Long codigo, ModEmpleadoDto modEmpleadoDto) {
        return this.empleadoRepository.modificarEmpleado(codigo, modEmpleadoDto);
    }

    public void eliminarEmpleado(Long codigo) {
        this.empleadoRepository.eliminarEmpleado(codigo);
    }

}
