package org.kaven.Cafeteria.dominio.implementation;

import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.kaven.Cafeteria.dominio.repository.EntregaRepository;
import org.kaven.Cafeteria.dominio.repository.JpaEntregaRepository;
import org.kaven.Cafeteria.persistence.entity.EntregaEntity;
import org.kaven.Cafeteria.web.mapper.EntregaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EntregaRepositoryImpl implements EntregaRepository {

    private final JpaEntregaRepository jpaEntregaRepository;
    private final EntregaMapper entregaMapper;

    public EntregaRepositoryImpl(JpaEntregaRepository jpaEntregaRepository, EntregaMapper entregaMapper) {
        this.jpaEntregaRepository = jpaEntregaRepository;
        this.entregaMapper = entregaMapper;
    }

    @Override
    public List<EntregaDto> obtenerTodoEntregas() {
        List<EntregaEntity> entities = jpaEntregaRepository.findAll();
        return entregaMapper.toDto(entities);
    }

    @Override
    public EntregaDto obtenerEntregasPorCodigo(Long codigo) {
        Optional<EntregaEntity> entity = jpaEntregaRepository.findById(codigo);
        return entity.map(entregaMapper::toDto).orElse(null);
    }

    @Override
    public EntregaDto guardarEntrega(EntregaDto entregaDto) {
        EntregaEntity entity = entregaMapper.toEntity(entregaDto);
        EntregaEntity savedEntity = jpaEntregaRepository.save(entity);
        return entregaMapper.toDto(savedEntity);
    }

    @Override
    public EntregaDto modificarEntrega(Long codigo, ModEntregaDto modEntregaDto) {
        Optional<EntregaEntity> existingEntity = jpaEntregaRepository.findById(codigo);
        if (existingEntity.isPresent()) {
            EntregaEntity entity = existingEntity.get();
            entregaMapper.modificarEntityFromDto(modEntregaDto, entity);
            EntregaEntity updatedEntity = jpaEntregaRepository.save(entity);
            return entregaMapper.toDto(updatedEntity);
        }
        return null;
    }

    @Override
    public void eliminarEntrega(Long codigo) {
        jpaEntregaRepository.deleteById(codigo);
    }
}


