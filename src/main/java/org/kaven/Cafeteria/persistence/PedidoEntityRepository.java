package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModPedidoDto;
import org.kaven.Cafeteria.dominio.dto.PedidoDto;
import org.kaven.Cafeteria.dominio.exception.PedidoNoExisteException;
import org.kaven.Cafeteria.dominio.exception.PedidoYaExisteException;
import org.kaven.Cafeteria.dominio.repository.PedidoRepository;
import org.kaven.Cafeteria.persistence.crud.CrudPedidosEntity;
import org.kaven.Cafeteria.persistence.entity.PedidoEntity;
import org.kaven.Cafeteria.web.mapper.PedidoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PedidoEntityRepository implements PedidoRepository {
    private final CrudPedidosEntity crudPedido;
    private final PedidoMapper pedidoMapper;

    public PedidoEntityRepository(CrudPedidosEntity crudPedido, PedidoMapper pedidoMapper) {
        this.crudPedido = crudPedido;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public List<PedidoDto> obtenerTodoPedido() {
        return this.pedidoMapper.toDto(this.crudPedido.findAll());
    }

    @Override
    public PedidoDto obtenerPedidoPorCodigo(Long codigo) {
        PedidoEntity pedidoEntity = this.crudPedido.findById(codigo)
                .orElseThrow(() -> new PedidoNoExisteException(codigo));
        return this.pedidoMapper.toDto(pedidoEntity);
    }

    @Override
    public PedidoDto guardarPedido(PedidoDto pedidoDto) {
        if (pedidoDto.id() != null && this.crudPedido.existsById(pedidoDto.id())) {
            throw new PedidoYaExisteException("ID: " + pedidoDto.id());
        }

        PedidoEntity pedido = this.pedidoMapper.toEntity(pedidoDto);
        PedidoEntity pedidoGuardado = this.crudPedido.save(pedido);
        return this.pedidoMapper.toDto(pedidoGuardado);
    }

    @Override
    public PedidoDto modificarPedido(Long codigo, ModPedidoDto modPedidoDto) {
        PedidoEntity pedidoEntity = this.crudPedido.findById(codigo)
                .orElseThrow(() -> new PedidoNoExisteException(codigo));

        this.pedidoMapper.modificarEntityFromDto(modPedidoDto, pedidoEntity);
        PedidoEntity pedidoActualizado = this.crudPedido.save(pedidoEntity);
        return this.pedidoMapper.toDto(pedidoActualizado);
    }

    @Override
    public void eliminarPedido(Long codigo) {
        if (!this.crudPedido.existsById(codigo)) {
            throw new PedidoNoExisteException(codigo);
        }
        this.crudPedido.deleteById(codigo);
    }
}