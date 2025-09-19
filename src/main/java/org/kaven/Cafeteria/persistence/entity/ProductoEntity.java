package org.kaven.Cafeteria.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "Productos")
@Data
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String tipo;

    @Column(precision = 6, scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Boolean disponibilidad;
}
