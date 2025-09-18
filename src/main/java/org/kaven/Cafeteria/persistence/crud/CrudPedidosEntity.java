package org.kaven.Cafeteria.persistence.crud;


public interface CrudPedidosEntity extends CrudRepository<PedidoEntity, Long>{
    PedidoEntity findFirstByNombre(String nombre);
}
