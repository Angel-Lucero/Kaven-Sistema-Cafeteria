package org.kaven.Cafeteria.persistence.crud;

import org.kaven.Cafeteria.persistence.entity.EstudianteEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudEstudianteEntity extends CrudRepository<EstudianteEntity, Integer> {
    EstudianteEntity findFirstByNombre(String nombre);
}
