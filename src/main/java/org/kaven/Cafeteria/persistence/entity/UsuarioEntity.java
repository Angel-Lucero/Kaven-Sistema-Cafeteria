package org.kaven.Cafeteria.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Usuarios")
@Data
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, unique = true, nullable = false)
    private String correo;

    @Column(length = 255, nullable = false)
    private String contrasena;

    @OneToOne
    @JoinColumn(name = "id_estudiante", unique = true)
    private EstudianteEntity estudiante;
}