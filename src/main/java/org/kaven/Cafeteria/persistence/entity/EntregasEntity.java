package org.kaven.Cafeteria.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import org.kaven.Cafeteria.dominio.DeliveryStatus;

@Entity
@Table(name = "Entregas")
@Data
public class EntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmpleadoEntity employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status", length = 20, nullable = false)
    private DeliveryStatus deliveryStatus;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;
}

