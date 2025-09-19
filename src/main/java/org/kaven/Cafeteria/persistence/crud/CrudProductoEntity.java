package org.kaven.Cafeteria.persistence.crud;


import org.kaven.Cafeteria.persistence.entity.ProductoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudProductoEntity extends CrudRepository<ProductoEntity, Long> {
    ProductoEntity findFirstByNombre(String nombre);
}
