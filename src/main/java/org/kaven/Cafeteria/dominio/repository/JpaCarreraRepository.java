package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.persistence.entity.CarreraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCarreraRepository extends JpaRepository<CarreraEntity, Long> {
}