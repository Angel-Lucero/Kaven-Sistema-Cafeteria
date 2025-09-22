package org.kaven.Cafeteria.dominio.implementation;

import org.kaven.Cafeteria.dominio.dto.EntregasDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregasDto;
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
    public List<EntregasDto> obtenerTodoEntregas() {
        List<EntregaEntity> entities = jpaEntregaRepository.findAll();
        return entregaMapper.toDto(entities);
    }

    @Override
    public EntregasDto obtenerEntregasPorCodigo(Long codigo) {
        Optional<EntregaEntity> entity = jpaEntregaRepository.findById(codigo);
        return entity.map(entregaMapper::toDto).orElse(null);
    }

    @Override
    public EntregasDto guardarEntrega(EntregasDto entregasDto) {
        EntregaEntity entity = entregaMapper.toEntity(entregasDto);
        EntregaEntity savedEntity = jpaEntregaRepository.save(entity);
        return entregaMapper.toDto(savedEntity);
    }

    @Override
    public EntregasDto modificarEntrega(Long codigo, ModEntregasDto modEntregasDto) {
        Optional<EntregaEntity> existingEntity = jpaEntregaRepository.findById(codigo);
        if (existingEntity.isPresent()) {
            EntregaEntity entity = existingEntity.get();
            entregaMapper.modificarEntityFromDto(modEntregasDto, entity);
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


