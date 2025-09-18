package org.kaven.Cafeteria.persistence.crud;


import org.kaven.Cafeteria.persistence.entity.PedidoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudPedidosEntity extends CrudRepository<PedidoEntity, Long> {
    PedidoEntity findFirstByNombre(String nombre);
}
