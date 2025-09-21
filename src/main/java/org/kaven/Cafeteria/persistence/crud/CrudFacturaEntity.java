package org.kaven.Cafeteria.persistence.crud;

import org.kaven.Cafeteria.persistence.entity.FacturaEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudFacturaEntity extends CrudRepository<FacturaEntity, Long> {
    FacturaEntity findFirstByNombre(Long codgio);
}
