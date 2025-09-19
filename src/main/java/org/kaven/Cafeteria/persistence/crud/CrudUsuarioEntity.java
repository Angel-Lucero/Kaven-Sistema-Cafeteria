package org.kaven.Cafeteria.persistence.crud;

import org.kaven.Cafeteria.persistence.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudUsuarioEntity extends CrudRepository<UsuarioEntity, Long> {
    UsuarioEntity findFirstByCorreo(String correo);
}