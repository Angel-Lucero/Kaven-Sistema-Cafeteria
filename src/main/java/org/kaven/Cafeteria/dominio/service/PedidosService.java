package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.repository.EmpleadoRepository;
import org.kaven.Cafeteria.dominio.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidosService {

    private final PedidoRepository pedidoRepository;

    public PedidosService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoDto> obtenerTodoPedido() {
        return this.pedidoRepository.obtenerTodoPedido();
    }

    public PedidoDto obtenerPedidoPorCodigo(Long codigo) {
        return this.pedidoRepository.obtenerPedidoPorCodigo(codigo);
    }

    public PedidoDto guardarPedido(PedidoDto empleadoDto) {
        return this.pedidoRepository.guardarPedido(empleadoDto);
    }

    public PedidoDto modificarPedido(Long codigo, ModPedidoDto modPedidoDto) {
        return this.pedidoRepository.modificarPedido(codigo, modPedidoDto);
    }

    public void eliminarPedido(Long codigo) {
        this.pedidoRepository.eliminarPedido(codigo);
    }

}
