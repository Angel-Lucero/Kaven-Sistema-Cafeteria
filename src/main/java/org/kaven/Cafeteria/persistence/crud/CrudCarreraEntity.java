package org.kaven.Cafeteria.persistence.crud;

import org.kaven.Cafeteria.persistence.entity.CarrerasEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudCarreraEntity extends CrudRepository<CarrerasEntity, Long> {
    CarrerasEntity findFirstByNombre(Long codgio);
}
