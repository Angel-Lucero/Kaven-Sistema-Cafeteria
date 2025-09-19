package org.kaven.Cafeteria.dominio.service;

import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.dominio.dto.ProductoDto;
import org.kaven.Cafeteria.dominio.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDto> obtenerTodoProducto() {
        return this.productoRepository.obtenerTodoProducto();
    }

    public ProductoDto obtenerProductoPorCodigo(Long codigo) {
        return this.productoRepository.obtenerProductoPorCodigo(codigo);
    }

    public ProductoDto guardarProducto(ProductoDto productoDto) {
        return this.productoRepository.guardarProducto(productoDto);
    }

    public ProductoDto modificarProducto(Long codigo, ModProductoDto modProductoDto) {
        return this.productoRepository.modificarProducto(codigo, modProductoDto);
    }

    public void eliminarProducto(Long codigo) {
        this.productoRepository.eliminarProducto(codigo);
    }
}