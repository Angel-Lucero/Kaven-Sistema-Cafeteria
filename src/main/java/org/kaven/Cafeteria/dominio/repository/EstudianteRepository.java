package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;

import java.util.List;

public interface EstudianteRepository {
    List<EstudianteDto> obtnerTodo();
    EstudianteDto obtenerEstudiantePorCodigo(Long codigo);
    EstudianteDto guardarEstudiante(EstudianteDto estudianteDto);
    EstudianteDto modificarEstudiante(Long codigo, ModEstudianteDto estudianteDto);
    void eliminarEstudiante(Long codigo);
}
