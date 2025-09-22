package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.exception.EntregaYaExisteException;
import org.kaven.Cafeteria.dominio.exception.EntregaNoExisteException;
import org.kaven.Cafeteria.dominio.repository.EntregaRepository;
import org.kaven.Cafeteria.persistence.crud.CrudEntregaEntity;
import org.kaven.Cafeteria.persistence.entity.EntregaEntity;
import org.kaven.Cafeteria.web.mapper.EntregaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntregaEntityRepository implements EntregaRepository {
    private final CrudEntregaEntity crudEntrega;
    private final EntregaMapper entregaMapper;

    public EntregaEntityRepository(CrudEntregaEntity crudEntrega, EntregaMapper entregaMapper) {
        this.crudEntrega = crudEntrega;
        this.entregaMapper = entregaMapper;
    }

    @Override
    public List<EntregaDto> obtenerTodoEntregas() {
        return this.entregaMapper.toDto(this.crudEntrega.findAll());
    }

    @Override
    public EntregaDto obtenerEntregasPorCodigo(Long codigo) {
        EntregaEntity entregaEntity = this.crudEntrega.findById(codigo)
                .orElseThrow(() -> new EntregaNoExisteException(codigo.toString()));
        return this.entregaMapper.toDto(entregaEntity);
    }

    @Override
    public EntregaDto guardarEntrega(EntregaDto entregaDto) {
        if (this.crudEntrega.findFirstById(entregaDto.id()) != null) {
            throw new EntregaYaExisteException(String.valueOf(entregaDto.id()));
        }
        EntregaEntity entrega = this.entregaMapper.toEntity(entregaDto);
        this.crudEntrega.save(entrega);
        return this.entregaMapper.toDto(entrega);
    }

    @Override
    public EntregaDto modificarEntrega(Long codigo, ModEntregaDto modEntregaDto) {
        EntregaEntity entregaEntity = this.crudEntrega.findById(codigo)
                .orElseThrow(() -> new EntregaNoExisteException(codigo.toString()));

        this.entregaMapper.modificarEntityFromDto(modEntregaDto, entregaEntity);
        this.crudEntrega.save(entregaEntity);
        return this.entregaMapper.toDto(entregaEntity);
    }

    @Override
    public void eliminarEntrega(Long codigo) {
        EntregaEntity entregaEntity = this.crudEntrega.findById(codigo)
                .orElseThrow(() -> new EntregaNoExisteException(codigo.toString()));
        this.crudEntrega.deleteById(codigo);
    }
}