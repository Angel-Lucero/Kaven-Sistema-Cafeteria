package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.PedidoDto;
import org.kaven.Cafeteria.dominio.exception.PedidoYaExisteException;
import org.kaven.Cafeteria.persistence.crud.CrudPedidosEntity;
import org.kaven.Cafeteria.persistence.entity.PedidoEntity;
import org.kaven.Cafeteria.web.mapper.PedidoMapper;

import java.util.List;

public class PedidosEntityRepository {
    private final CrudPedidosEntity crudPedido;
    private final PedidoMapper pedidoMapper;

    public PedidosEntityRepository(CrudPedidosEntity crudPedido, PedidoMapper pedidoMapper) {
        this.crudPedido = crudPedido;
        this.pedidoMapper = pedidoMapper;
    }

    public List<PedidoDto> obtenerTodo() {
        return this.pedidoMapper.toDto(this.crudPedido.findAll());
    }

    public PedidoDto obtenerPedidoPorCodigo(Long codigo) {
        PedidoEntity pedidoEntity = this.crudPedido.findById(codigo).orElse(null);
        return this.pedidoMapper.toDto(this.crudPedido.findById(codigo).orElse(null));
        // Para despues cuando este la excepcion de PedidoNoExiste
//        if (pedidoEntity == null) throw new PedidoNoExisteException(codigo);
//        else {
//            return this.pedidoMapper.toDto(this.crudPedido.findById(codigo).orElse(null));
//        }
    }

    public PedidoDto guardarPedido(PedidoDto pedidoDto) {
        if (this.crudPedido.findFirstByNombre(pedidoDto.name()) != null){
            throw new PedidoYaExisteException(pedidoDto.name());
        }
        PedidoEntity pedido = this.pedidoMapper.toEntity(pedidoDto);
        this.crudPedido.save(pedido);
        return this.pedidoMapper.toDto(pedido);
    }

    public PedidoDto modificarPedido(Long codigo, ModPedidoDto pedidoDto) {
        PedidoEntity pedidoEntity = this.crudPedido.findById(codigo).orElse(null);
        pedidoEntity.setNombre(pedidoDto.name());
        pedidoEntity.setApellido(pedidoDto.apellido());
        pedidoEntity.setEmail(pedidoDto.email());
        pedidoEntity.setTelefono(pedidoDto.telefono());
        pedidoEntity.setDireccion(pedidoDto.direccion());
        this.crudPedido.save(pedidoEntity);
        return this.pedidoMapper.toDto(pedidoEntity);

        /* para cuando este la excepcion de PedidoNoExiste

        if (pedidoEntity == null) throw new PedidoNoExisteException(codigo);
        else {
            pedidoEntity.setNombre(pedidoDto.name());
            pedidoEntity.setApellido(pedidoDto.apellido());
            pedidoEntity.setEmail(pedidoDto.email());
            pedidoEntity.setTelefono(pedidoDto.telefono());
            pedidoEntity.setDireccion(pedidoDto.direccion());
            this.crudPedido.save(pedidoEntity);
            return this.pedidoMapper.toDto(pedidoEntity);
        }*/
    }

    public void eliminarPedido(Long codigo) {
        PedidoEntity pedidoEntity = this.crudPedido.findById(codigo).orElse(null);
        this.crudPedido.deleteById(codigo);

        //falta la excepcion de PedidoNoExiste
        /*if (pedidoEntity == null){
            throw new PedidoNoExisteException(codigo);
        } else {
            this.crudPedido.deleteById(codigo);
        }*/
    }
}