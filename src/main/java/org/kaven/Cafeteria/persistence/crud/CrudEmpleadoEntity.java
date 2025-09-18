package org.kaven.Cafeteria.persistence.crud;

public interface CrudEmpleadoEntity extends CrudRepository<EmpleadoEntity, Long>{
    EmpleadoEntity findFirstByNombre(String nombre);
}
