package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kaven.Cafeteria.dominio.dto.CarreraDto;
import org.kaven.Cafeteria.dominio.dto.ModCarreraDto;
import org.kaven.Cafeteria.dominio.service.CarreraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/carreras")
@Tag(name = "Carreras", description = "Operaciones(CRUD) para todas las carreras")
public class CarreraController {
    private final CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todas las carreras",
            description = "Retorna una lista con todas las carreras del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de carreras obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<List<CarreraDto>> obtenerTodo() {
        return ResponseEntity.ok(this.carreraService.obtenerTodoCarreras());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener carrera a partir del código",
            description = "Retorna la carrera que coincida con el código enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La carrera fue encontrada con éxito"),
                    @ApiResponse(responseCode = "404", description = "La carrera no fue encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<CarreraDto> obtenerCarreraPorCodigo(
            @Parameter(description = "Identificador de la carrera a recuperar", example = "5")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.carreraService.obtenerCarreraPorCodigo(codigo));
    }

    @PostMapping
    @Operation(
            summary = "Crear una nueva carrera",
            description = "Crea una nueva carrera en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Carrera creada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "La carrera ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<CarreraDto> guardarCarrera(
            @Parameter(description = "Datos de la carrera a crear")
            @RequestBody CarreraDto carreraDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.carreraService
                .guardarCarreras(carreraDto));
    }

    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar una carrera existente",
            description = "Actualiza los datos de una carrera existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Carrera modificada exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Carrera no encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<CarreraDto> modificarCarrera(
            @Parameter(description = "Identificador de la carrera a modificar", example = "5")
            @PathVariable Long codigo,
            @Parameter(description = "Datos actualizados de la carrera")
            @RequestBody @Valid ModCarreraDto modCarreraDto) {
        return ResponseEntity.ok(this.carreraService.modificarCarreras(codigo, modCarreraDto));
    }

    @DeleteMapping("{codigo}")
    @Operation(
            summary = "Eliminar una carrera",
            description = "Elimina una carrera del sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Carrera eliminada exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Carrera no encontrada", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<Void> eliminarCarrera(
            @Parameter(description = "Identificador de la carrera a eliminar", example = "5")
            @PathVariable Long codigo) {
        this.carreraService.eliminarCarreras(codigo);
        return ResponseEntity.noContent().build();
    }
}