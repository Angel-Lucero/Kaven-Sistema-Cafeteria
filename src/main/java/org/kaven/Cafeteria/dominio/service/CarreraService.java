package org.kaven.Cafeteria.dominio.service;


import org.kaven.Cafeteria.dominio.dto.CarreraDto;
import org.kaven.Cafeteria.dominio.dto.ModCarreraDto;
import org.kaven.Cafeteria.dominio.repository.CarreraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {
    private final CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<CarreraDto> obtenerTodoCarreras() {
        return this.carreraRepository.obtenerTodoCarreras();
    }

    public CarreraDto obtenerCarreraPorCodigo(Long codigo) {
        return this.carreraRepository.obtenerCarreraPorCodigo(codigo);
    }

    public CarreraDto guardarCarreras(CarreraDto carrerasDto) {
        return this.carreraRepository.guardarCarreras(carrerasDto);
    }

    public CarreraDto modificarCarreras(Long codigo, ModCarreraDto modCarreraDto) {
        return this.carreraRepository.modificarCarreras(codigo, modCarreraDto);
    }

    public void eliminarCarreras(Long codigo) {
        this.carreraRepository.eliminarCarreras(codigo);
    }

}
