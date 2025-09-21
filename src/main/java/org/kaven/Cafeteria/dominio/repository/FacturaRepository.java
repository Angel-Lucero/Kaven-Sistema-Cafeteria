package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.ModFacturaDto;
import org.kaven.Cafeteria.dominio.dto.FacturaDto;

import java.util.List;

public interface FacturaRepository {
    List<FacturaDto> obtenerTodoFactura();
    FacturaDto obtenerFacturaPorCodigo(Long codigo);
    FacturaDto guardarFactura(FacturaDto facturaDto);
    FacturaDto modificarFactura(Long codigo, ModFacturaDto facturaDto);
    void eliminarFactura(Long codigo);
}
