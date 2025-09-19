package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.dominio.dto.ProductoDto;
import org.kaven.Cafeteria.dominio.exception.ProductoNoExisteException;
import org.kaven.Cafeteria.dominio.exception.ProductoYaExisteException;
import org.kaven.Cafeteria.dominio.repository.ProductoRepository;
import org.kaven.Cafeteria.persistence.crud.CrudProductoEntity;
import org.kaven.Cafeteria.persistence.entity.ProductoEntity;
import org.kaven.Cafeteria.web.mapper.ProductoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoEntityRepository implements ProductoRepository {
    private final CrudProductoEntity crudProducto;
    private final ProductoMapper productoMapper;

    public ProductoEntityRepository(CrudProductoEntity crudProducto, ProductoMapper productoMapper) {
        this.crudProducto = crudProducto;
        this.productoMapper = productoMapper;
    }

    @Override
    public List<ProductoDto> obtenerTodoProducto() {
        return this.productoMapper.toDto(this.crudProducto.findAll());
    }

    @Override
    public ProductoDto obtenerProductoPorCodigo(Long codigo) {
        ProductoEntity productoEntity = this.crudProducto.findById(codigo)
                .orElseThrow(() -> new ProductoNoExisteException(codigo));
        return this.productoMapper.toDto(productoEntity);
    }

    @Override
    public ProductoDto guardarProducto(ProductoDto productoDto) {
        if (this.crudProducto.findFirstByNombre(productoDto.name()) != null) {
            throw new ProductoYaExisteException(productoDto.name());
        }

        ProductoEntity producto = this.productoMapper.toEntity(productoDto);
        ProductoEntity productoGuardado = this.crudProducto.save(producto);
        return this.productoMapper.toDto(productoGuardado);
    }

    @Override
    public ProductoDto modificarProducto(Long codigo, ModProductoDto productoDto) {
        ProductoEntity productoEntity = this.crudProducto.findById(codigo)
                .orElseThrow(() -> new ProductoNoExisteException(codigo));

        this.productoMapper.modificarEntityFromDto(productoDto, productoEntity);
        ProductoEntity productoActualizado = this.crudProducto.save(productoEntity);
        return this.productoMapper.toDto(productoActualizado);
    }

    @Override
    public void eliminarProducto(Long codigo) {
        ProductoEntity productoEntity = this.crudProducto.findById(codigo)
                .orElseThrow(() -> new ProductoNoExisteException(codigo));
        this.crudProducto.deleteById(codigo);
    }
}