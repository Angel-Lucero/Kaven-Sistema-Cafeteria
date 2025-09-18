package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.dominio.dto.ProductoDto;

import java.util.List;

public interface ProductoRepository {
<<<<<<< Updated upstream
    List<ProductoDto> obtenerTodoProducto();
    ProductoDto obtenerProductoPorCodigo(Long codigo);
    ProductoDto guardarProducto(ProductoDto productoDto);
    ProductoDto modificarProducto(Long codigo,ModProductoDto productoDto);
    void eliminarProducto(Long codigo);
=======
    List<ProductoDto> obtenerTodo();
    ProductoDto obtenerUsuarioPorCodigo(Long codigo);
    ProductoDto guardarUsuario(ProductoDto productoDto);
    ProductoDto modificarUsuario(Long codigo, ModProductoDto productoDto);
    void eliminarUsuario(Long codigo);
>>>>>>> Stashed changes
}
