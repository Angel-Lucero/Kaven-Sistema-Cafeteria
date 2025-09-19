package org.kaven.Cafeteria.dominio.repository;

import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.dominio.dto.ProductoDto;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductoRepository {
    List<ProductoDto> obtenerTodoProducto();
    ProductoDto obtenerProductoPorCodigo(Long codigo);
    ProductoDto guardarProducto(ProductoDto productoDto);
    ProductoDto modificarProducto(Long codigo,ModProductoDto productoDto);
    void eliminarProducto(Long codigo);

}
