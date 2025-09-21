package org.kaven.Cafeteria.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Carreras")
@Data
public class CarrerasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    private String degreeName;
}
