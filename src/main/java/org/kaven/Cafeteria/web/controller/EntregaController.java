package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.kaven.Cafeteria.dominio.service.EntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/entregas")
@Tag(name = "Entregas", description = "Operaciones(CRUD) para todas las entregas")
public class EntregaController {
    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las entregas",
            description = "Retorna una lista con todas las entregas del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de entregas obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<List<EntregaDto>> obtenerTodo() {
        return ResponseEntity.ok(this.entregaService.obtenerTodoEntregas());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener entrega a partir del código",
            description = "Retorna la entrega que coincida con el código enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La entrega fue encontrada con éxito"),
                    @ApiResponse(responseCode = "404", description = "La entrega no fue encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<EntregaDto> obtenerEntregaPorCodigo(
            @Parameter(description = "Identificador de la entrega a recuperar", example = "5")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.entregaService.obtenerEntregasPorCodigo(codigo));
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva entrega",
            description = "Crea una nueva entrega en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Entrega creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "La entrega ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<EntregaDto> guardarEntrega(
            @Parameter(description = "Datos de la entrega a crear")
            @RequestBody EntregaDto entregaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.entregaService
                .guardarEntrega(entregaDto));
    }

    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar una entrega existente",
            description = "Actualiza los datos de una entrega existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Entrega modificada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Entrega no encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<EntregaDto> modificarEntrega(
            @Parameter(description = "Identificador de la entrega a modificar", example = "5")
            @PathVariable Long codigo,
            @Parameter(description = "Datos actualizados de la entrega")
            @RequestBody @Valid ModEntregaDto modEntregaDto) {
        return ResponseEntity.ok(this.entregaService.modificarEntrega(codigo, modEntregaDto));
    }

    @DeleteMapping("{codigo}")
    @Operation(
            summary = "Eliminar una entrega",
            description = "Elimina una entrega del sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Entrega eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Entrega no encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<Void> eliminarEntrega(
            @Parameter(description = "Identificador de la entrega a eliminar", example = "5")
            @PathVariable Long codigo) {
        this.entregaService.eliminarEntrega(codigo);
        return ResponseEntity.noContent().build();
    }
}