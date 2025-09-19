package org.kaven.Cafeteria.web.controller;


package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kaven.Cafeteria.dominio.dto.PedidoDto;
import org.kaven.Cafeteria.dominio.dto.ModPedidoDto;
import org.kaven.Cafeteria.dominio.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
@Tag(name = "Pedidos", description = "Operaciones(CRUD) para todos los pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping 
    public ResponseEntity<List<PedidoDto>> obtenerTodo() {
        return ResponseEntity.ok(this.pedidoService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener pedido a partir del codigo",
            description = "Retorna el pedido que coincida con el codigo enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El pedido fue encontrado con exito"),
                    @ApiResponse(responseCode = "404", description = "El pedido no fue encontrado", content = @Content)
            }
    )
    public ResponseEntity<PedidoDto> obtenerPedidoPorCodigo
            (@Parameter(description = "Identificador del pedido a recuperar", example = "5")
             @PathVariable Long codigo) {
        return ResponseEntity.ok(this.pedidoService.obtenerPedidoPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<PedidoDto> guardarPedido(
            @RequestBody PedidoDto pedidoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService
                .guardarPedido(pedidoDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<PedidoDto> modificarPedido
            (@PathVariable Long codigo, @RequestBody @Valid ModPedidoDto modPedidoDto) {
        return ResponseEntity.ok(this.pedidoService.modificarPedido(codigo, modPedidoDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<PedidoDto> eliminarPedido(@PathVariable Long codigo) {
        this.pedidoService.eliminarPedido(codigo);
        return ResponseEntity.ok().build();
    }
}