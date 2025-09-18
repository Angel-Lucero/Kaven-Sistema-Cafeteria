package org.kaven.Cafeteria.dominio.repository;

import java.util.List;

public interface PedidoRepository{
    List<PedidoDto> obtenerTodo();
    PedidoDto obtenerPedidoPorCodigo(Long codigo);
    PedidoDto guardarPedido(PedidoDto pedidoDto);
    PedidoDto modificarPedido(Long codigo,ModPedidooDto pedidooDto);
    void eliminarPedido(Long codigo);

}
