package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;
import org.kaven.Cafeteria.dominio.exception.EstudianteYaExisteException;
import org.kaven.Cafeteria.dominio.repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<EstudianteDto> obtenerTodo() {
        return this.estudianteRepository.obtnerTodo();
    }

    public EstudianteDto obtenerEstudiantePorCodigo(Long codigo) {
        return this.estudianteRepository.obtenerEstudiantePorCodigo(codigo);
    }

    public EstudianteDto guardarEstudiante(EstudianteDto estudianteDto) {
        validarEstudiante(estudianteDto);
        return this.estudianteRepository.guardarEstudiante(estudianteDto);
    }

    public EstudianteDto modificarEstudiante(Long codigo, ModEstudianteDto modEstudianteDto) {
        validarModEstudiante(modEstudianteDto);
        return this.estudianteRepository.modificarEstudiante(codigo, modEstudianteDto);
    }

    public void eliminarEstudiante(Long codigo) {
        this.estudianteRepository.eliminarEstudiante(codigo);
    }

    private void validarEstudiante(EstudianteDto estudianteDto) {
        if (estudianteDto.nombre() == null || estudianteDto.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del estudiante no puede estar vacío.");
        }
        if (estudianteDto.edad() != null && estudianteDto.edad() <= 0) {
            throw new IllegalArgumentException("La edad del estudiante debe ser mayor a 0.");
        }

        EstudianteDto existente = estudianteRepository.obtenerEstudiantePorCodigo(estudianteDto.codigo());
        if (existente != null) {
            throw new EstudianteYaExisteException(estudianteDto.nombre());
        }
    }

    private void validarModEstudiante(ModEstudianteDto modEstudianteDto) {
        if (modEstudianteDto.edad() != null && modEstudianteDto.edad() <= 0) {
            throw new IllegalArgumentException("La edad modificada del estudiante debe ser mayor a 0.");
        }
    }
}