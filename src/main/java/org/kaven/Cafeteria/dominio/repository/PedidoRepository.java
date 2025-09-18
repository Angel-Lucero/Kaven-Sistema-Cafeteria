package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.ModPedidoDto;
import org.kaven.Cafeteria.dominio.dto.PedidoDto;

import java.util.List;

public interface PedidoRepository{
    List<PedidoDto> obtenerTodoPedido();
    PedidoDto obtenerPedidoPorCodigo(Long codigo);
    PedidoDto guardarPedido(PedidoDto pedidoDto);
    PedidoDto modificarPedido(Long codigo, ModPedidoDto pedidooDto);
    void eliminarPedido(Long codigo);

}
