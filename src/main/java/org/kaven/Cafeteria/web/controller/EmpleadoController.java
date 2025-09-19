package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;
import org.kaven.Cafeteria.dominio.service.EmpleadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/empleados")
@Tag(name = "Empleados", description = "Operaciones(CRUD) para todos los empleados")
public class EmpleadoController {
    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> obtenerTodo() {
        return ResponseEntity.ok(this.empleadoService.obtenerTodoEmpleado());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener empleado a partir del codigo",
            description = "Retorna el empleado que coincida con el codigo enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El empleado fue encontrado con exito"),
                    @ApiResponse(responseCode = "404", description = "El empleado no fue encontrado", content = @Content)
            }
    )
    public ResponseEntity<EmpleadoDto> obtenerEmpleadoPorCodigo(
            @Parameter(description = "Identificador del empleado a recuperar", example = "5")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.empleadoService.obtenerEmpleadoPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<EmpleadoDto> guardarEmpleado(
            @RequestBody @Valid EmpleadoDto empleadoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.empleadoService
                .guardarEmpleado(empleadoDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<EmpleadoDto> modificarEmpleado(
            @PathVariable Long codigo, @RequestBody @Valid ModEmpleadoDto modEmpleadoDto) {
        return ResponseEntity.ok(this.empleadoService.modificarEmpleado(codigo, modEmpleadoDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long codigo) {
        this.empleadoService.eliminarEmpleado(codigo);
        return ResponseEntity.noContent().build();
    }
}