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
        return this.estudianteRepository.guardarEstudiante(estudianteDto);
    }

    public EstudianteDto modificarEstudiante(Long codigo, ModEstudianteDto modEstudianteDto) {
        return this.estudianteRepository.modificarEstudiante(codigo, modEstudianteDto);
    }

    public void eliminarEstudiante(Long codigo) {
        this.estudianteRepository.eliminarEstudiante(codigo);
    }


}