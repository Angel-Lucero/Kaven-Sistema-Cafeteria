package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.persistence.entity.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEntregaRepository extends JpaRepository<EntregaEntity, Long> {

}
