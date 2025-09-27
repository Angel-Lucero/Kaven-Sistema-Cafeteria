package org.kaven.Cafeteria.persistence.crud;

import org.kaven.Cafeteria.persistence.entity.EntregaEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudEntregaEntity extends CrudRepository<EntregaEntity, Long> {
    EntregaEntity findFirstById(Long id);
}
