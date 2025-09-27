package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.exception.EntregaYaExisteException;
import org.kaven.Cafeteria.dominio.exception.EntregaNoExisteException;
import org.kaven.Cafeteria.dominio.repository.EntregaRepository;
import org.kaven.Cafeteria.persistence.crud.CrudEntregaEntity;
import org.kaven.Cafeteria.persistence.crud.CrudPedidosEntity;
import org.kaven.Cafeteria.persistence.crud.CrudEmpleadoEntity;
import org.kaven.Cafeteria.persistence.entity.EntregaEntity;
import org.kaven.Cafeteria.persistence.entity.PedidoEntity;
import org.kaven.Cafeteria.persistence.entity.EmpleadoEntity;
import org.kaven.Cafeteria.web.mapper.EntregaMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntregaEntityRepository implements EntregaRepository {
    private final CrudEntregaEntity crudEntrega;
    private final CrudPedidosEntity crudPedido;
    private final CrudEmpleadoEntity crudEmpleado;
    private final EntregaMapper entregaMapper;

    public EntregaEntityRepository(CrudEntregaEntity crudEntrega,
                                   CrudPedidosEntity crudPedido,
                                   CrudEmpleadoEntity crudEmpleado,
                                   EntregaMapper entregaMapper) {
        this.crudEntrega = crudEntrega;
        this.crudPedido = crudPedido;
        this.crudEmpleado = crudEmpleado;
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
        if (entregaDto.id() != null && this.crudEntrega.existsById(entregaDto.id())) {
            throw new EntregaYaExisteException(String.valueOf(entregaDto.id()));
        }

        PedidoEntity pedido = crudPedido.findById(entregaDto.orderId())
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + entregaDto.orderId()));
        EmpleadoEntity empleado = crudEmpleado.findById(entregaDto.employeeId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + entregaDto.employeeId()));

        EntregaEntity entrega = this.entregaMapper.toEntity(entregaDto);
        entrega.setOrder(pedido);
        entrega.setEmployee(empleado);

        this.crudEntrega.save(entrega);
        return this.entregaMapper.toDto(entrega);
    }

    @Override
    public EntregaDto modificarEntrega(Long codigo, ModEntregaDto modEntregaDto) {
        // Verificar si la entrega existe
        EntregaEntity entregaEntity = this.crudEntrega.findById(codigo)
                .orElseThrow(() -> new EntregaNoExisteException(codigo.toString()));

        if (modEntregaDto.orderId() != null) {
            PedidoEntity pedido = crudPedido.findById(modEntregaDto.orderId())
                    .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id: " + modEntregaDto.orderId()));
            entregaEntity.setOrder(pedido);
        }

        if (modEntregaDto.employeeId() != null) {
            EmpleadoEntity empleado = crudEmpleado.findById(modEntregaDto.employeeId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado con id: " + modEntregaDto.employeeId()));
            entregaEntity.setEmployee(empleado);
        }

        this.entregaMapper.modificarEntityFromDto(modEntregaDto, entregaEntity);
        this.crudEntrega.save(entregaEntity);
        return this.entregaMapper.toDto(entregaEntity);
    }

    @Override
    public void eliminarEntrega(Long codigo) {
        if (!this.crudEntrega.existsById(codigo)) {
            throw new EntregaNoExisteException(codigo.toString());
        }
        this.crudEntrega.deleteById(codigo);
    }
}