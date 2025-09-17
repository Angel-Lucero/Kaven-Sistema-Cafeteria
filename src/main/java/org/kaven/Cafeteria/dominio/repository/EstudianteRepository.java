package org.kaven.Cafeteria.dominio.repository;

import java.util.List;

public interface EstudianteRepository {
    List<EstudianteDto> obtnerTodo();
    EstudianteDto obtenerEstudiantePorCodigo(Long codigo);
    EstudianteDto guardarEstudiante(EstudianteDto estudianteDto);
    EstudianteDto modificarEstudiante(Long codigo,ModEstudianteDto estudianteDto);
    void eliminarEstudiante(Long codigo);
}
