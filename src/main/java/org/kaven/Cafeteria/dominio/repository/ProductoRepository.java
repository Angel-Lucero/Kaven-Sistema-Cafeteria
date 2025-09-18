package org.kaven.Cafeteria.dominio.repository;

public interface ProductoRepository {
    List<ProductoDto> obtenerTodo();
    ProductoDto obtenerUsuarioPorCodigo(Long codigo);
    ProductoDto guardarUsuario(ProductoDto productoDto);
    ProductoDto modificarUsuario(Long codigo,ModProductoDto productoDto);
    void eliminarUsuario(Long codigo);
}
