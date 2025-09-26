package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.ModPedidoDto;
import org.kaven.Cafeteria.dominio.dto.PedidoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PedidoRepository{
    List<PedidoDto> obtenerTodoPedido();
    PedidoDto obtenerPedidoPorCodigo(Long codigo);
    PedidoDto guardarPedido(PedidoDto pedidoDto);
    PedidoDto modificarPedido(Long codigo, ModPedidoDto pedidooDto);
    void eliminarPedido(Long codigo);

}
