package org.kaven.Cafeteria.persistence.crud;


public interface CrudProductoEntity extends CrudRepository<ProductoEntity, Long>{
    ProductoEntity findFirstByNombre(String nombre);
}
