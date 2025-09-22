package org.kaven.Cafeteria.dominio.implementation;

import org.kaven.Cafeteria.dominio.dto.FacturaDto;
import org.kaven.Cafeteria.dominio.dto.ModFacturaDto;
import org.kaven.Cafeteria.dominio.repository.FacturaRepository;
import org.kaven.Cafeteria.dominio.repository.JpaFacturaRepository;
import org.kaven.Cafeteria.persistence.entity.FacturaEntity;
import org.kaven.Cafeteria.web.mapper.FacturaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FacturaRepositoryImpl implements FacturaRepository {

    private final JpaFacturaRepository jpaFacturaRepository;
    private final FacturaMapper facturaMapper;

    public FacturaRepositoryImpl(JpaFacturaRepository jpaFacturaRepository, FacturaMapper facturaMapper) {
        this.jpaFacturaRepository = jpaFacturaRepository;
        this.facturaMapper = facturaMapper;
    }

    @Override
    public List<FacturaDto> obtenerTodoFactura() {
        return facturaMapper.toDto(jpaFacturaRepository.findAll());
    }

    @Override
    public FacturaDto obtenerFacturaPorCodigo(Long codigo) {
        Optional<FacturaEntity> entity = jpaFacturaRepository.findById(codigo);
        return entity.map(facturaMapper::toDto).orElse(null);
    }

    @Override
    public FacturaDto guardarFactura(FacturaDto facturasDto) {
        FacturaEntity entity = facturaMapper.toEntity(facturasDto);
        FacturaEntity savedEntity = jpaFacturaRepository.save(entity);
        return facturaMapper.toDto(savedEntity);
    }

    @Override
    public FacturaDto modificarFactura(Long codigo, ModFacturaDto modFacturaDto) {
        Optional<FacturaEntity> existingEntity = jpaFacturaRepository.findById(codigo);
        if (existingEntity.isPresent()) {
            FacturaEntity entity = existingEntity.get();
            facturaMapper.modificarEntityFromDto(modFacturaDto, entity);
            FacturaEntity updatedEntity = jpaFacturaRepository.save(entity);
            return facturaMapper.toDto(updatedEntity);
        }
        return null;
    }

    @Override
    public void eliminarFactura(Long codigo) {
        jpaFacturaRepository.deleteById(codigo);
    }
}