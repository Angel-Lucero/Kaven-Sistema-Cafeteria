package org.kaven.Cafeteria.persistence.crud;

import org.kaven.Cafeteria.persistence.entity.EmpleadoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudEmpleadoEntity extends CrudRepository<EmpleadoEntity, Long> {
    EmpleadoEntity findFirstByNombre(String nombre);
}
