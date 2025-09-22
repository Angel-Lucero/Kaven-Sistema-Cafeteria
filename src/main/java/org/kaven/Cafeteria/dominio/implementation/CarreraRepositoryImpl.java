package org.kaven.Cafeteria.dominio.implementation;

import org.kaven.Cafeteria.dominio.dto.CarreraDto;
import org.kaven.Cafeteria.dominio.dto.ModCarreraDto;
import org.kaven.Cafeteria.dominio.repository.CarreraRepository;
import org.kaven.Cafeteria.dominio.repository.JpaCarreraRepository;
import org.kaven.Cafeteria.persistence.entity.CarreraEntity;
import org.kaven.Cafeteria.web.mapper.CarreraMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarreraRepositoryImpl implements CarreraRepository {
    private final JpaCarreraRepository jpaCarreraRepository;
    private final CarreraMapper carreraMapper;

    public CarreraRepositoryImpl(JpaCarreraRepository jpaCarreraRepository, CarreraMapper carreraMapper) {
        this.jpaCarreraRepository = jpaCarreraRepository;
        this.carreraMapper = carreraMapper;
    }

    @Override
    public List<CarreraDto> obtenerTodoCarreras() {
        List<CarreraEntity> entities = jpaCarreraRepository.findAll();
        return carreraMapper.toDto(entities);
    }

    @Override
    public CarreraDto obtenerCarreraPorCodigo(Long codigo) {
        Optional<CarreraEntity> entity = jpaCarreraRepository.findById(codigo);
        return entity.map(carreraMapper::toDto).orElse(null);
    }

    @Override
    public CarreraDto guardarCarreras(CarreraDto carrerasDto) {
        CarreraEntity entity = carreraMapper.toEntity(carrerasDto);
        CarreraEntity savedEntity = jpaCarreraRepository.save(entity);
        return carreraMapper.toDto(savedEntity);
    }

    @Override
    public CarreraDto modificarCarreras(Long codigo, ModCarreraDto modCarreraDto) {
        Optional<CarreraEntity> existingEntity = jpaCarreraRepository.findById(codigo);
        if (existingEntity.isPresent()) {
            CarreraEntity entity = existingEntity.get();
            carreraMapper.modificarEntityFromDto(modCarreraDto, entity);
            CarreraEntity updatedEntity = jpaCarreraRepository.save(entity);
            return carreraMapper.toDto(updatedEntity);
        }
        return null;
    }

    @Override
    public void eliminarCarreras(Long codigo) {
        jpaCarreraRepository.deleteById(codigo);
    }
}