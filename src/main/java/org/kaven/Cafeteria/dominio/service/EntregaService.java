package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.kaven.Cafeteria.dominio.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public List<EntregaDto> obtenerTodoEntregas() {
        return this.entregaRepository.obtenerTodoEntregas();
    }

    public EntregaDto obtenerEntregasPorCodigo(Long codigo) {
        return this.entregaRepository.obtenerEntregasPorCodigo(codigo);
    }

    public EntregaDto guardarEntrega(EntregaDto entregaDto) {
        return this.entregaRepository.guardarEntrega(entregaDto);
    }

    public EntregaDto modificarEntrega(Long codigo, ModEntregaDto modEntregaDto) {
        return this.entregaRepository.modificarEntrega(codigo, modEntregaDto);
    }

    public void eliminarEntrega(Long codigo) {
        this.entregaRepository.eliminarEntrega(codigo);
    }

}
