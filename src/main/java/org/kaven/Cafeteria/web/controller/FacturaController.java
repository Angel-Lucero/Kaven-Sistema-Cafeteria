package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kaven.Cafeteria.dominio.dto.FacturaDto;
import org.kaven.Cafeteria.dominio.dto.ModFacturaDto;
import org.kaven.Cafeteria.dominio.service.FacturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/facturas")
@Tag(name = "Facturas", description = "Operaciones(CRUD) para todas las facturas")
public class FacturaController {
    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las facturas",
            description = "Retorna una lista con todas las facturas del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<List<FacturaDto>> obtenerTodo() {
        return ResponseEntity.ok(this.facturaService.obtenerTodoFactura());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener factura a partir del código",
            description = "Retorna la factura que coincida con el código enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La factura fue encontrada con éxito"),
                    @ApiResponse(responseCode = "404", description = "La factura no fue encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<FacturaDto> obtenerFacturaPorCodigo(
            @Parameter(description = "Identificador de la factura a recuperar", example = "5")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.facturaService.obtenerFacturaPorCodigo(codigo));
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva factura",
            description = "Crea una nueva factura en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Factura creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "La factura ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<FacturaDto> guardarFactura(
            @Parameter(description = "Datos de la factura a crear")
            @RequestBody FacturaDto facturaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.facturaService
                .guardarFactura(facturaDto));
    }

    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar una factura existente",
            description = "Actualiza los datos de una factura existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Factura modificada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<FacturaDto> modificarFactura(
            @Parameter(description = "Identificador de la factura a modificar", example = "5")
            @PathVariable Long codigo,
            @Parameter(description = "Datos actualizados de la factura")
            @RequestBody @Valid ModFacturaDto modFacturaDto) {
        return ResponseEntity.ok(this.facturaService.modificarFactura(codigo, modFacturaDto));
    }

    @DeleteMapping("{codigo}")
    @Operation(
            summary = "Eliminar una factura",
            description = "Elimina una factura del sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Factura eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Factura no encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarFactura(
            @Parameter(description = "Identificador de la factura a eliminar", example = "5")
            @PathVariable Long codigo) {
        this.facturaService.eliminarFactura(codigo);
        return ResponseEntity.noContent().build();
    }
}