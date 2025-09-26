package org.kaven.Cafeteria.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    @Operation(
            summary = "Obtener todos los productos",
            description = "Retorna una lista con todos los productos del sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<List<ProductoDto>> obtenerTodo() {
        return ResponseEntity.ok(this.productoService.obtenerTodoProducto());
    }

    @GetMapping("{codigo}")
    @Operation(
            summary = "Obtener producto a partir del código",
            description = "Retorna el producto que coincida con el código enviado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "El producto fue encontrado con éxito"),
                    @ApiResponse(responseCode = "404", description = "El producto no fue encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<ProductoDto> obtenerProductoPorCodigo(
            @Parameter(description = "Identificador del producto a recuperar", example = "5")
            @PathVariable Long codigo) {
        return ResponseEntity.ok(this.productoService.obtenerProductoPorCodigo(codigo));
    }

    @PostMapping
    @Operation(
            summary = "Crear un nuevo producto",
            description = "Crea un nuevo producto en el sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "409", description = "El producto ya existe", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<ProductoDto> guardarProducto(
            @Parameter(description = "Datos del producto a crear")
            @RequestBody ProductoDto productoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productoService
                .guardarProducto(productoDto));
    }

    @PutMapping("{codigo}")
    @Operation(
            summary = "Modificar un producto existente",
            description = "Actualiza los datos de un producto existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Producto modificado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )
    public ResponseEntity<ProductoDto> modificarProducto(
            @Parameter(description = "Identificador del producto a modificar", example = "5")
            @PathVariable Long codigo,
            @Parameter(description = "Datos actualizados del producto")
            @RequestBody @Valid ModProductoDto modProductoDto) {
        return ResponseEntity.ok(this.productoService.modificarProducto(codigo, modProductoDto));
    }

    @DeleteMapping("{codigo}")
    @Operation(
            summary = "Eliminar un producto",
            description = "Elimina un producto del sistema",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
            }
    )

    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "Identificador del producto a eliminar", example = "5")
            @PathVariable Long codigo) {
        this.productoService.eliminarProducto(codigo);
        return ResponseEntity.noContent().build();
    }
}