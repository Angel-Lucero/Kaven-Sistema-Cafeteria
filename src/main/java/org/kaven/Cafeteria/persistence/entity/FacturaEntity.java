package org.kaven.Cafeteria.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import org.kaven.Cafeteria.dominio.PaymentType;

@Entity
@Table(name = "Facturas")
@Data
public class FacturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private EstudianteEntity student;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private PedidoEntity order;

    @Column(nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", length = 20, nullable = false)
    private PaymentType paymentType;
}
