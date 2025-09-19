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
    public ResponseEntity<List<EstudianteDto>> obtenerTodo() {
        return ResponseEntity.ok(this.estudianteService.obtenerTodoEstudiante());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener estudiante a partir del codigo",
            description = "Retorna el estudiante que coincida con el codigo enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El estudiante fue encontrado con exito"),
                    @ApiResponse(responseCode = "404", description = "El estudiante no fue encontrado", content = @Content)
            }
    )

    public ResponseEntity<EstudianteDto> obtenerEstudiantePorCodigo
            (@Parameter(description = "Identificador del estudainte a recuperar", example = "5")
             @PathVariable Long codigo) {
        return ResponseEntity.ok(this.estudianteService.obtenerEstudiantePorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<EstudianteDto> guardarEstudiante(
            @RequestBody EstudianteDto estudianteDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.estudianteService
                .guardarEstudiante(estudianteDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<EstudianteDto> modificarEstudiante
            (@PathVariable Long codigo, @RequestBody @Valid ModEstudianteDto modEstudianteDto) {
        return ResponseEntity.ok(this.estudianteService.modificarEstudiante(codigo, modEstudianteDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<EstudianteDto> eliminarEstudiante(@PathVariable Long codigo) {
        this.estudianteService.obtenerEstudiantePorCodigo(codigo);
        return ResponseEntity.ok().build();
    }
}