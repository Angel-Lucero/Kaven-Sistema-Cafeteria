package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.CarrerasDto;
import org.kaven.Cafeteria.dominio.dto.ModCarrerasDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarreraRepository {
    List<CarrerasDto> obtenerTodoCarreras();
    CarrerasDto obtenerCarreraPorCodigo(Long codigo);
    CarrerasDto guardarCarreras(CarrerasDto carrerasDto);
    CarrerasDto modificarCarreras(Long codigo, ModCarrerasDto carrerasDto);
    void eliminarCarreras(Long codigo);
}
