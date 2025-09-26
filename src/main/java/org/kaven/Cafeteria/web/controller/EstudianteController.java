package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;
import org.kaven.Cafeteria.dominio.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/estudiantes")
@Tag(name = "Estudiantes", description = "Operaciones(CRUD) para todos los estudiantes")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los estudiantes",
            description = "Retorna una lista con todos los estudiantes del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<List<EstudianteDto>> obtenerTodo() {
        return ResponseEntity.ok(this.estudianteService.obtenerTodoEstudiante());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener estudiante a partir del código",
            description = "Retorna el estudiante que coincida con el código enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El estudiante fue encontrado con éxito"),
                    @ApiResponse(responseCode = "404", description = "El estudiante no fue encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<EstudianteDto> obtenerEstudiantePorCodigo(
            @Parameter(description = "Identificador del estudiante a recuperar", example = "5")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.estudianteService.obtenerEstudiantePorCodigo(codigo));
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo estudiante",
            description = "Crea un nuevo estudiante en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Estudiante creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "El estudiante ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<EstudianteDto> guardarEstudiante(
            @Parameter(description = "Datos del estudiante a crear")
            @RequestBody EstudianteDto estudianteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.estudianteService
                .guardarEstudiante(estudianteDto));
    }

    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar un estudiante existente",
            description = "Actualiza los datos de un estudiante existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estudiante modificado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<EstudianteDto> modificarEstudiante(
            @Parameter(description = "Identificador del estudiante a modificar", example = "5")
            @PathVariable Long codigo,
            @Parameter(description = "Datos actualizados del estudiante")
            @RequestBody @Valid ModEstudianteDto modEstudianteDto) {
        return ResponseEntity.ok(this.estudianteService.modificarEstudiante(codigo, modEstudianteDto));
    }

    @DeleteMapping("{codigo}")
    @Operation(
            summary = "Eliminar un estudiante",
            description = "Elimina un estudiante del sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Estudiante eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Estudiante no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarEstudiante(
            @Parameter(description = "Identificador del estudiante a eliminar", example = "5")
            @PathVariable Long codigo) {
        this.estudianteService.eliminarEstudiante(codigo);
        return ResponseEntity.noContent().build();
    }
}