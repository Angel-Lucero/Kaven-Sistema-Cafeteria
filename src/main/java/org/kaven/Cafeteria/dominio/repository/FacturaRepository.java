package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.ModFacturaDto;
import org.kaven.Cafeteria.dominio.dto.FacturaDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FacturaRepository {
    List<FacturaDto> obtenerTodoFactura();
    FacturaDto obtenerFacturaPorCodigo(Long codigo);
    FacturaDto guardarFactura(FacturaDto facturaDto);
    FacturaDto modificarFactura(Long codigo, ModFacturaDto facturaDto);
    void eliminarFactura(Long codigo);
}
