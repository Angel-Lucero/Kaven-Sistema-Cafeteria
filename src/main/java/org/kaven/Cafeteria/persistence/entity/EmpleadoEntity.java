package org.kaven.Cafeteria.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Empleados")
@Data
public class EmpleadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String rol; // barista, cajero, administrador

    @Column(length = 30, nullable = false)
    private String turno; // mañana, tarde, noche

}
