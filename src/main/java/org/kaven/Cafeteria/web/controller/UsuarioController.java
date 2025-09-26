package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kaven.Cafeteria.dominio.dto.ModUsuarioDto;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones(CRUD) para todos los usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Retorna una lista con todos los usuarios del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<List<UsuarioDto>> obtenerTodo(){
        return ResponseEntity.ok(this.usuarioService.obtenerTodoUsuario());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener usuario a partir del código",
            description = "Retorna el usuario que coincide con el código enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El usuario fue encontrado con éxito"),
                    @ApiResponse(responseCode = "404", description = "El usuario no fue encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<UsuarioDto> obtenerUsuarioPorCodigo(
            @Parameter(description = "Identificador del usuario a recuperar", example = "5")
            @PathVariable Long codigo){
        return ResponseEntity.ok(this.usuarioService.obtenerUsuarioPorCodigo(String.valueOf(codigo)));
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo usuario",
            description = "Crea un nuevo usuario en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "El usuario ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<UsuarioDto> guardarUsuario(
            @Parameter(description = "Datos del usuario a crear")
            @RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService
                .guardarUsuario(usuarioDto));
    }

    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar un usuario existente",
            description = "Actualiza los datos de un usuario existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario modificado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<UsuarioDto> modificarUsuario(
            @Parameter(description = "Identificador del usuario a modificar", example = "5")
            @PathVariable Long codigo,
            @Parameter(description = "Datos actualizados del usuario")
            @RequestBody @Valid ModUsuarioDto modUsuarioDto){
        return ResponseEntity.ok(this.usuarioService.modificarUsuario(String.valueOf(codigo), modUsuarioDto));
    }

    @DeleteMapping("{codigo}")
    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario del sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<Void> eliminarUsuario(
            @Parameter(description = "Identificador del usuario a eliminar", example = "5")
            @PathVariable Long codigo) {
        this.usuarioService.eliminarUsuario(String.valueOf(codigo));
        return ResponseEntity.noContent().build();
    }
}
