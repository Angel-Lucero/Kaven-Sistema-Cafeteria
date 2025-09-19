package org.kaven.Cafeteria.persistence;

import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.dominio.dto.ProductoDto;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.exception.ProductoNoExisteException;
import org.kaven.Cafeteria.dominio.exception.ProductoYaExisteException;
import org.kaven.Cafeteria.persistence.crud.CrudProductoEntity;
import org.kaven.Cafeteria.persistence.entity.ProductoEntity;
import org.kaven.Cafeteria.persistence.entity.UsuarioEntity;
import org.kaven.Cafeteria.web.mapper.ProductoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductoEntityRepository {
    private final CrudProductoEntity crudProducto;
    private final ProductoMapper produtoMapper;

    public ProductoEntityRepository(CrudProductoEntity crudProducto, ProductoMapper produtoMapper) {
        this.crudProducto = crudProducto;
        this.produtoMapper = produtoMapper;
    }

    @Override
    public List<ProductoDto> obtenerTodo() {
        return this.produtoMapper.toDto(this.crudProducto.findAll());
    }

    @Override
    public ProductoDto obtenerProductoPorCodigo(Long codigo) {
        ProductoEntity productoEntity = this.crudProducto.findById(codigo).orElse(null);
        if (productoEntity == null) throw new ProductoNoExisteException (codigo);
        else {
            return this.produtoMapper.toDto(this.crudProducto.findById(codigo).orElse(null));
        }
    }

    @Override
    public ProductoDto guardarProducto(ProductoDto productoDto) {

        if (this.crudProducto.findFirstByNombre(productoDto.name()) != null){
            throw new ProductoYaExisteException(productoDto.name());
        }
        UsuarioEntity usuario = this.produtoMapper.toEntity(productoDto);
        this.crudProducto.save(producto);
        return this.produtoMapper.toDto(producto);
    }

    @Override
    public UsuarioDto modificarProductos(Long codigo, ModProductoDto productoDto) {
        ProductoEntity productoEntity  = this.crudProducto.findById(codigo).orElse(null);
        //los sets de productos
        this.crudProducto.save(productoEntity);
        return this.produtoMapper.toDto(productoEntity);

    }

    @Override
    public void eliminarProducto(Long codigo) {
        UsuarioEntity usuarioEntity = this.crudProducto.findById(codigo).orElse(null);
        this.crudProducto.deleteById(codigo);
        //la excepcion
    }




















}
