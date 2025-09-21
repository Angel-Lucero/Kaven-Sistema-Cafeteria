package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.EntregasDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregasDto;
import org.kaven.Cafeteria.dominio.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public List<EntregasDto> obtenerTodoEntregas() {
        return this.entregaRepository.obtenerTodoEntregas();
    }

    public EntregasDto obtenerEntregasPorCodigo(Long codigo) {
        return this.entregaRepository.obtenerEntregasPorCodigo(codigo);
    }

    public EntregasDto guardarEntrega(EntregasDto entregasDto) {
        return this.entregaRepository.guardarEntrega(entregasDto);
    }

    public EntregasDto modificarEntrega(Long codigo, ModEntregasDto modEntregasDto) {
        return this.entregaRepository.modificarEntrega(codigo, modEntregasDto);
    }

    public void eliminarEntrega(Long codigo) {
        this.entregaRepository.eliminarEntrega(codigo);
    }

}
