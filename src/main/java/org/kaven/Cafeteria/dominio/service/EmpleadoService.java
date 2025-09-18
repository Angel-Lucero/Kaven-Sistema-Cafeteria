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

    public EstudianteDto obtenerEstudiantePorCodigo(Long codigo) {
        return this.estudianteRepository.obtenerEstudiantePorCodigo(codigo);
    }

    public EstudianteDto guardarEstudiante(EstudianteDto estudianteDto) {
        return this.estudianteRepository.guardarEstudiante(estudianteDto);
    }

    public EstudianteDto modificarEstudiante(Long codigo, ModEstudianteDto modEstudianteDto) {
        return this.estudianteRepository.modificarEstudiante(codigo, modEstudianteDto);
    }

    public void eliminarEstudiante(Long codigo) {
        this.estudianteRepository.eliminarEstudiante(codigo);
    }

}
