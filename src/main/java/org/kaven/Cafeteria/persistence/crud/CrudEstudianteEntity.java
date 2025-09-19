package org.kaven.Cafeteria.persistence.crud;

import org.kaven.Cafeteria.persistence.entity.EstudianteEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudEstudianteEntity extends CrudRepository<EstudianteEntity, Long> {
    EstudianteEntity findFirstByNombre(String nombre);
}
