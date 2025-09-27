package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.ProductoDto;
import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.dominio.service.ProductoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ViewScoped
public class WebTablaProductosController implements Serializable {

    @Autowired
    private ProductoService productoService;

    private List<ProductoDto> productos;
    private ProductoDto productoSeleccionado;

    private static final Logger logger = LoggerFactory.getLogger(WebProductosController.class);

    private String editNombre;
    private String editTipo;
    private BigDecimal editPrecio;
    private Boolean editDisponibilidad;

    public WebTablaProductosController(ProductoService productoService) {
        this.productoService = productoService;
    }

    public WebTablaProductosController() {}

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.productos = productoService.obtenerTodoProducto();
            this.productos.forEach(producto -> logger.info(producto.toString()));
        } catch (Exception e) {
            logger.error("Error al cargar productos", e);
            this.productos = new ArrayList<>();
        }
    }

    public void refresh() {
        try {
            this.productos = new ArrayList<>(productoService.obtenerTodoProducto());
        } catch (Exception e) {
            this.productos = new ArrayList<>();
        }
    }

    public void agregarProducto() {
        this.productoSeleccionado = null; // Nuevo
        this.editNombre = "";
        this.editTipo = "";
        this.editPrecio = BigDecimal.ZERO;
        this.editDisponibilidad = true;
        PrimeFaces.current().executeScript("PF('ventanaModalProducto').show()");
    }

    public void prepararEdicion(ProductoDto p) {
        this.productoSeleccionado = p;
        if (p != null) {
            this.editNombre = p.name();
            this.editTipo = p.type();
            this.editPrecio = p.price();
            this.editDisponibilidad = p.availability();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalProducto').show()");
    }

    public void guardarProducto() {
        try {
            ProductoDto dto;
            if (productoSeleccionado == null || productoSeleccionado.id() == null) {
                dto = new ProductoDto(null, editNombre, editTipo, editPrecio, editDisponibilidad);
            } else {
                dto = new ProductoDto(productoSeleccionado.id(), editNombre, editTipo, editPrecio, editDisponibilidad);
            }
            ProductoDto guardado = productoService.guardarProducto(dto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(productoSeleccionado == null ? "Producto Agregado" : "Producto Modificado"));
            refresh();
            this.productoSeleccionado = guardado;
            PrimeFaces.current().ajax().update("formulario-productos:tabla-productos", "mensaje_emergente");
            PrimeFaces.current().executeScript("PF('ventanaModalProducto').hide()");
            this.productoSeleccionado = null; // Limpiar selección tras guardar
        } catch (Exception e) {
            logger.error("Error al guardar producto", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el producto"));
        }
    }

    public void eliminarProducto() {
        if (productoSeleccionado == null || productoSeleccionado.id() == null) return;
        try {
            productoService.eliminarProducto(productoSeleccionado.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("formulario-productos:tabla-productos", "mensaje_emergente");
            this.productoSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar producto", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el producto"));
        }
    }

    public void cancelarProducto() {
        this.productoSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalProducto').hide()");
    }
}