package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.CarreraDto;
import org.kaven.Cafeteria.dominio.dto.ModCarreraDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository {
    List<CarreraDto> obtenerTodoCarreras();
    CarreraDto obtenerCarreraPorCodigo(Long codigo);
    CarreraDto guardarCarreras(CarreraDto carrerasDto);
    CarreraDto modificarCarreras(Long codigo, ModCarreraDto carrerasDto);
    void eliminarCarreras(Long codigo);
}
