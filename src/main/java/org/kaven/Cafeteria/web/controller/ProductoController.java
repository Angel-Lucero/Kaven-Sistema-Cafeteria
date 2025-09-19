package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.dominio.dto.ProductoDto;
import org.kaven.Cafeteria.dominio.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/productos")
@Tag(name = "Productos", description = "Operaciones(CRUD) para todos los productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerTodo() {
        return ResponseEntity.ok(this.productoService.obtenerTodo());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener producto a partir del codigo",
            description = "Retorna el producto que coincida con el codigo enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El producto fue encontrado con exito"),
                    @ApiResponse(responseCode = "404", description = "El producto no fue encontrado", content = @Content)
            }
    )

    public ResponseEntity<ProductoDto> obtenerProductoPorCodigo
            (@Parameter(description = "Identificador del producto a recuperar", example = "5")
             @PathVariable Long codigo) {
        return ResponseEntity.ok(this.productoService.obtenerProductoPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<ProductoDto> guardarProducto(
            @RequestBody ProductoDto productoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productoService
                .guardarProducto(productoDto));
    }

    @PutMapping("{codigo}")
    public ResponseEntity<ProductoDto> modificarProducto
            (@PathVariable Long codigo, @RequestBody @Valid ModProductoDto modProductoDto) {
        return ResponseEntity.ok(this.productoService.modificarProducto(codigo, modProductoDto));
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity<EstudianteDto> eliminarProducto(@PathVariable Long codigo) {
        this.productoService.obtenerProductoPorCodigo(codigo);
        return ResponseEntity.ok().build();
    }

}
