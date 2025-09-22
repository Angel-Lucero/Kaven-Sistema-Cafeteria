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
    @Operation(
            summary = "Obtener todos los pedidos",
            description = "Retorna una lista con todos los pedidos del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<List<PedidoDto>> obtenerTodo() {
        return ResponseEntity.ok(this.pedidoService.obtenerTodoPedido());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener pedido a partir del código",
            description = "Retorna el pedido que coincida con el código enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El pedido fue encontrado con éxito"),
                    @ApiResponse(responseCode = "404", description = "El pedido no fue encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<PedidoDto> obtenerPedidoPorCodigo(
            @Parameter(description = "Identificador del pedido a recuperar", example = "5")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.pedidoService.obtenerPedidoPorCodigo(codigo));
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo pedido",
            description = "Crea un nuevo pedido en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "El pedido ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<PedidoDto> guardarPedido(
            @Parameter(description = "Datos del pedido a crear")
            @RequestBody PedidoDto pedidoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService
                .guardarPedido(pedidoDto));
    }

    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar un pedido existente",
            description = "Actualiza los datos de un pedido existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Pedido modificado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Pedido no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<PedidoDto> modificarPedido(
            @Parameter(description = "Identificador del pedido a modificar", example = "5")
            @PathVariable Long codigo,
            @Parameter(description = "Datos actualizados del pedido")
            @RequestBody @Valid ModPedidoDto modPedidoDto) {
        return ResponseEntity.ok(this.pedidoService.modificarPedido(codigo, modPedidoDto));
    }

    @DeleteMapping("{codigo}")
    @Operation(
            summary = "Eliminar un pedido",
            description = "Elimina un pedido del sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Pedido no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarPedido(
            @Parameter(description = "Identificador del pedido a eliminar", example = "5")
            @PathVariable Long codigo) {
        this.pedidoService.eliminarPedido(codigo);
        return ResponseEntity.noContent().build();
    }
}