package org.kaven.Cafeteria.dominio.dto;

import java.util.List;

public record EstudianteDto(
        Long id,
        String name,
        String mail,
        String phone,
        String career,
        List<PedidoDto> orders
) {
}
