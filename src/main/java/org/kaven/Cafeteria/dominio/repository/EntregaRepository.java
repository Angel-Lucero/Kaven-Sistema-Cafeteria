package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EntregaRepository {
    List<EntregaDto> obtenerTodoEntregas();
    EntregaDto obtenerEntregasPorCodigo(Long codigo);
    EntregaDto guardarEntrega(EntregaDto entregaDto);
    EntregaDto modificarEntrega(Long codigo, ModEntregaDto entregasDto);
    void eliminarEntrega(Long codigo);
}
