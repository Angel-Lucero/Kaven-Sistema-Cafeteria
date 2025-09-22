package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModFacturaDto;
import org.kaven.Cafeteria.dominio.dto.FacturaDto;
import org.kaven.Cafeteria.dominio.exception.FacturaYaExisteException;
import org.kaven.Cafeteria.dominio.exception.FacturaNoExisteException;
import org.kaven.Cafeteria.dominio.repository.FacturaRepository;
import org.kaven.Cafeteria.persistence.crud.CrudFacturaEntity;
import org.kaven.Cafeteria.persistence.entity.FacturaEntity;
import org.kaven.Cafeteria.web.mapper.FacturaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FacturaEntityRepository implements FacturaRepository {
    private final CrudFacturaEntity crudFactura;
    private final FacturaMapper facturaMapper;

    public FacturaEntityRepository(CrudFacturaEntity crudFactura, FacturaMapper facturaMapper) {
        this.crudFactura = crudFactura;
        this.facturaMapper = facturaMapper;
    }

    @Override
    public List<FacturaDto> obtenerTodoFactura() {
        return this.facturaMapper.toDto(this.crudFactura.findAll());
    }

    @Override
    public FacturaDto obtenerFacturaPorCodigo(Long codigo) {
        FacturaEntity facturaEntity = this.crudFactura.findById(codigo)
                .orElseThrow(() -> new FacturaNoExisteException(codigo.toString()));
        return this.facturaMapper.toDto(facturaEntity);
    }

    @Override
    public FacturaDto guardarFactura(FacturaDto facturaDto) {
        if (this.crudFactura.findFirstById(facturaDto.id()) != null) {
            throw new FacturaYaExisteException(String.valueOf(facturaDto.id()));
        }
        FacturaEntity factura = this.facturaMapper.toEntity(facturaDto);
        this.crudFactura.save(factura);
        return this.facturaMapper.toDto(factura);
    }

    @Override
    public FacturaDto modificarFactura(Long codigo, ModFacturaDto modFacturaDto) {
        FacturaEntity facturaEntity = this.crudFactura.findById(codigo)
                .orElseThrow(() -> new FacturaNoExisteException(codigo.toString()));

        this.facturaMapper.modificarEntityFromDto(modFacturaDto, facturaEntity);
        this.crudFactura.save(facturaEntity);
        return this.facturaMapper.toDto(facturaEntity);
    }

    @Override
    public void eliminarFactura(Long codigo) {
        FacturaEntity facturaEntity = this.crudFactura.findById(codigo)
                .orElseThrow(() -> new FacturaNoExisteException(codigo.toString()));
        this.crudFactura.deleteById(codigo);
    }
}