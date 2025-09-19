package org.kaven.Cafeteria.persistence.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Estudiantes")
@Data
public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    private String nombre;

    @Column(length = 120, unique = true, nullable = false)
    private String correo;

    @Column(length = 20, nullable = false)
    private String telefono;

    @Column(length = 80, nullable = false)
    private String carrera;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoEntity> pedidos;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private UsuarioEntity usuario;
}
