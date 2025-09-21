package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.EntregasDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregasDto;

import java.util.List;

public interface EntregaRepository {
    List<EntregasDto> obtenerTodoEntregas();
    EntregasDto obtenerEntregasPorCodigo(Long codigo);
    EntregasDto guardarEntrega(EntregasDto entregasDto);
    EntregasDto modificarEntrega(Long codigo, ModEntregasDto entregasDto);
    void eliminarEntrega(Long codigo);
}
