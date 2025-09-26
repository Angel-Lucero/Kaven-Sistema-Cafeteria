package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.ModPedidoDto;
import org.kaven.Cafeteria.dominio.dto.PedidoDto;
import org.kaven.Cafeteria.dominio.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoDto> obtenerTodoPedido() {
        return this.pedidoRepository.obtenerTodoPedido();
    }

    public PedidoDto obtenerPedidoPorCodigo(Long codigo) {
        return this.pedidoRepository.obtenerPedidoPorCodigo(codigo);
    }

    public PedidoDto guardarPedido(PedidoDto pedidoDto) {
        return this.pedidoRepository.guardarPedido(pedidoDto);
    }

    public PedidoDto modificarPedido(Long codigo, ModPedidoDto modPedidoDto) {
        return this.pedidoRepository.modificarPedido(codigo, modPedidoDto);
    }

    public void eliminarPedido(Long codigo) {
        this.pedidoRepository.eliminarPedido(codigo);
    }
}