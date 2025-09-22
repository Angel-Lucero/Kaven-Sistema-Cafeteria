package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModCarreraDto;
import org.kaven.Cafeteria.dominio.dto.CarreraDto;
import org.kaven.Cafeteria.dominio.exception.CarreraYaExisteException;
import org.kaven.Cafeteria.dominio.exception.CarreraNoExisteException;
import org.kaven.Cafeteria.dominio.repository.CarreraRepository;
import org.kaven.Cafeteria.persistence.crud.CrudCarreraEntity;
import org.kaven.Cafeteria.persistence.entity.CarreraEntity;
import org.kaven.Cafeteria.web.mapper.CarreraMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarreraEntityRepository implements CarreraRepository {
    private final CrudCarreraEntity crudCarrera;
    private final CarreraMapper carreraMapper;

    public CarreraEntityRepository(CrudCarreraEntity crudCarrera, CarreraMapper carreraMapper) {
        this.crudCarrera = crudCarrera;
        this.carreraMapper = carreraMapper;
    }

    @Override
    public List<CarreraDto> obtenerTodoCarreras() {
        return this.carreraMapper.toDto(this.crudCarrera.findAll());
    }

    @Override
    public CarreraDto obtenerCarreraPorCodigo(Long codigo) {
        CarreraEntity carreraEntity = this.crudCarrera.findById(codigo)
                .orElseThrow(() -> new CarreraNoExisteException(codigo.toString()));
        return this.carreraMapper.toDto(carreraEntity);
    }

    @Override
    public CarreraDto guardarCarreras(CarreraDto carreraDto) {
        if (this.crudCarrera.findFirstById(carreraDto.id()) != null) {
            throw new CarreraYaExisteException(String.valueOf(carreraDto.id()));
        }
        CarreraEntity carrera = this.carreraMapper.toEntity(carreraDto);
        this.crudCarrera.save(carrera);
        return this.carreraMapper.toDto(carrera);
    }

    @Override
    public CarreraDto modificarCarreras(Long codigo, ModCarreraDto modCarreraDto) {
        CarreraEntity carreraEntity = this.crudCarrera.findById(codigo)
                .orElseThrow(() -> new CarreraNoExisteException(codigo.toString()));

        this.carreraMapper.modificarEntityFromDto(modCarreraDto, carreraEntity);
        this.crudCarrera.save(carreraEntity);
        return this.carreraMapper.toDto(carreraEntity);
    }

    @Override
    public void eliminarCarreras(Long codigo) {
        CarreraEntity carreraEntity = this.crudCarrera.findById(codigo)
                .orElseThrow(() -> new CarreraNoExisteException(codigo.toString()));
        this.crudCarrera.deleteById(codigo);
    }
}