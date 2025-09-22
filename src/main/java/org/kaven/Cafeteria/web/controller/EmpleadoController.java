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
    @Operation(
            summary = "Obtener todos los empleados",
            description = "Retorna una lista con todos los empleados del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<List<EmpleadoDto>> obtenerTodo() {
        return ResponseEntity.ok(this.empleadoService.obtenerTodoEmpleado());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener empleado a partir del código",
            description = "Retorna el empleado que coincida con el código enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El empleado fue encontrado con éxito"),
                    @ApiResponse(responseCode = "404", description = "El empleado no fue encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<EmpleadoDto> obtenerEmpleadoPorCodigo(
            @Parameter(description = "Identificador del empleado a recuperar", example = "5")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.empleadoService.obtenerEmpleadoPorCodigo(codigo));
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo empleado",
            description = "Crea un nuevo empleado en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "El empleado ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<EmpleadoDto> guardarEmpleado(
            @Parameter(description = "Datos del empleado a crear")
            @RequestBody @Valid EmpleadoDto empleadoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.empleadoService
                .guardarEmpleado(empleadoDto));
    }

    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar un empleado existente",
            description = "Actualiza los datos de un empleado existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empleado modificado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<EmpleadoDto> modificarEmpleado(
            @Parameter(description = "Identificador del empleado a modificar", example = "5")
            @PathVariable Long codigo,
            @Parameter(description = "Datos actualizados del empleado")
            @RequestBody @Valid ModEmpleadoDto modEmpleadoDto) {
        return ResponseEntity.ok(this.empleadoService.modificarEmpleado(codigo, modEmpleadoDto));
    }

    @DeleteMapping("{codigo")
    @Operation(
            summary = "Eliminar un empleado",
            description = "Elimina un empleado del sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empleado eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarEmpleado(
            @Parameter(description = "Identificador del empleado a eliminar", example = "5")
            @PathVariable Long codigo) {
        this.empleadoService.eliminarEmpleado(codigo);
        return ResponseEntity.noContent().build();
    }
}