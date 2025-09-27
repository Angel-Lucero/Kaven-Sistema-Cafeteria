package org.kaven.Cafeteria.persistence.crud;

import org.kaven.Cafeteria.persistence.entity.CarreraEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudCarreraEntity extends CrudRepository<CarreraEntity, Long> {
    CarreraEntity findFirstById(Long id);
}
