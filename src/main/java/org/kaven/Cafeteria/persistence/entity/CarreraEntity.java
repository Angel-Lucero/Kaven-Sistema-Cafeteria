package org.kaven.Cafeteria.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.kaven.Cafeteria.dominio.DegreeType;

@Entity
@Table(name = "Carreras")
@Data
public class CarreraEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 120, nullable = false)
    private DegreeType degreeName;
}
