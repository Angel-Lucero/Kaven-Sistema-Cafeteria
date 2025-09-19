package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EstudianteRepository {
    List<EstudianteDto> obtenerTodoEstudiante();
    EstudianteDto obtenerEstudiantePorCodigo(Long codigo);
    EstudianteDto guardarEstudiante(EstudianteDto estudianteDto);
    EstudianteDto modificarEstudiante(Long codigo, ModEstudianteDto estudianteDto);
    void eliminarEstudiante(Long codigo);
}