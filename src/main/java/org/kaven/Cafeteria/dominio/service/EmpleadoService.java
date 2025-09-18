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

    public EmpleadoDto obtenerEstudiantePorCodigo(Long codigo) {
        return this.empleadoRepository.obtenerEstudiantePorCodigo(codigo);
    }

    public EmpleadoDto guardarEstudiante(EstudianteDto estudianteDto) {
        return this.estudianteRepository.guardarEstudiante(estudianteDto);
    }

    public EmpleadoDto modificarEstudiante(Long codigo, ModEstudianteDto modEstudianteDto) {
        return this.estudianteRepository.modificarEstudiante(codigo, modEstudianteDto);
    }

    public void eliminarEstudiante(Long codigo) {
        this.estudianteRepository.eliminarEstudiante(codigo);
    }

}
