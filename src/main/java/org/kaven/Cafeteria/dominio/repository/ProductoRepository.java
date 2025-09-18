package org.kaven.Cafeteria.dominio.repository;

public interface ProductoRepository {
    List<ProductoDto> obtenerTodoProducto();
    ProductoDto obtenerProductoPorCodigo(Long codigo);
    ProductoDto guardarProducto(ProductoDto productoDto);
    ProductoDto modificarProducto(Long codigo,ModProductoDto productoDto);
    void eliminarProducto(Long codigo);
}
