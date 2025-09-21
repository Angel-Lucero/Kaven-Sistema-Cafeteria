package org.kaven.Cafeteria.dominio.service;


import org.kaven.Cafeteria.dominio.dto.CarrerasDto;
import org.kaven.Cafeteria.dominio.dto.ModCarrerasDto;
import org.kaven.Cafeteria.dominio.repository.CarreraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {
    private final CarreraRepository carreraRepository;

    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<CarrerasDto> obtenerTodoCarreras() {
        return this.carreraRepository.obtenerTodoCarreras();
    }

    public CarrerasDto obtenerCarreraPorCodigo(Long codigo) {
        return this.carreraRepository.obtenerCarreraPorCodigo(codigo);
    }

    public CarrerasDto guardarCarreras(CarrerasDto carrerasDto) {
        return this.carreraRepository.guardarCarreras(carrerasDto);
    }

    public CarrerasDto modificarCarreras(Long codigo, ModCarrerasDto modCarrerasDto) {
        return this.carreraRepository.modificarCarreras(codigo, modCarrerasDto);
    }

    public void eliminarCarreras(Long codigo) {
        this.carreraRepository.eliminarCarreras(codigo);
    }

}
