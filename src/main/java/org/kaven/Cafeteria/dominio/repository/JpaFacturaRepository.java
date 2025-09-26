package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.persistence.entity.FacturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFacturaRepository extends JpaRepository<FacturaEntity, Long> {
}
