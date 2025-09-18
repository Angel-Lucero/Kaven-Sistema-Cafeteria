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
    public ResponseEntity<List<UsuarioDto>> obtenerTodo(){
        return ResponseEntity.ok(this.usuarioService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener usuario a partir del codigo",
            description = "Retorna el usuario que coincide con el codigo enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El usuario fue encontrado con exito"),
                    @ApiResponse(responseCode = "404", description = "El usuario no fue encontrado", content = @Content)
            }
    )

    public ResponseEntity<UsuarioDto> obtenerUsuarioPorCodigo
            (@Parameter(description = "Identificador del usuario a recuperar", example = "5")
             @PathVariable Long codigo){
        return ResponseEntity.ok(this.usuarioService.obtenerUsuarioPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> guardarUsuario(
            @RequestBody UsuarioDto usuarioDto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService
                .guardarUsuario(usuarioDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<UsuarioDto> modificarUsuario
            (@PathVariable Long codigo, @RequestBody @Valid ModUsuarioDto modUsuarioDto){
        return ResponseEntity.ok(this.usuarioService.modificarUsuario(codigo, modUsuarioDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<UsuarioDto> eliminarUsuario(@PathVariable Long codigo){
        this.usuarioService.obtenerUsuarioPorCodigo(codigo);
        return ResponseEntity.ok().build();
    }


















}
