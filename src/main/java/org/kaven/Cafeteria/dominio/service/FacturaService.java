package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.FacturaDto;
import org.kaven.Cafeteria.dominio.dto.ModFacturaDto;
import org.kaven.Cafeteria.dominio.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaDto> obtenerTodoFactura() {
        return this.facturaRepository.obtenerTodoFactura();
    }

    public FacturaDto obtenerFacturaPorCodigo(Long codigo) {
        return this.facturaRepository.obtenerFacturaPorCodigo(codigo);
    }

    public FacturaDto guardarFactura(FacturaDto facturaDto) {
        return this.facturaRepository.guardarFactura(facturaDto);
    }

    public FacturaDto modificarFactura(Long codigo, ModFacturaDto modFacturaDto) {
        return this.facturaRepository.modificarFactura(codigo, modFacturaDto);
    }

    public void eliminarFactura(Long codigo) {
        this.facturaRepository.eliminarFactura(codigo);
    }

}
