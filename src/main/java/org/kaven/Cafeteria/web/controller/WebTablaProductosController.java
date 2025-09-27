package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.ModProductoDto;
import org.kaven.Cafeteria.dominio.dto.ProductoDto;
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

    private static final Logger logger = LoggerFactory.getLogger(WebTablaProductosController.class);

    @Autowired
    private ProductoService productoService;

    private List<ProductoDto> productos;
    private ProductoDto productoSeleccionado;

    private String editName;
    private String editType;
    private BigDecimal editPrice;
    private Boolean editAvailability;

    public List<String> getTiposProducto() {
        return List.of("SALCHIPAPA", "CAKE", "PIZZA_COMBO", "AGUAPURA_BOTTLE", "PEPSI", "COFFEE", "COOKIE");
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }


    public void cargarDatos() {
        try {
            this.productos = this.productoService.obtenerTodoProducto();
            logger.info("Productos cargados: " + this.productos.size());
        } catch (Exception e) {
            logger.error("Error al cargar los productos", e);
            this.productos = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los productos."));
        }
    }


    public void agregarProducto() {
        this.productoSeleccionado = null;
        this.editName = "";
        this.editType = "SALCHIPAPA";
        this.editPrice = BigDecimal.ZERO;
        this.editAvailability = true;
        PrimeFaces.current().executeScript("PF('ventanaModalProducto').show()");
    }


    public void prepararEdicion(ProductoDto producto) {
        this.productoSeleccionado = producto;
        if (producto != null) {
            this.editName = producto.name();
            this.editType = producto.type();
            this.editPrice = producto.price();
            this.editAvailability = producto.availability();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalProducto').show()");
    }


    public void guardarProducto() {
        try {
            ProductoDto productoGuardado;
            String mensajeExito;

            if (this.productoSeleccionado == null || this.productoSeleccionado.id() == null) {
                // Lógica para CREAR
                ProductoDto nuevoDto = new ProductoDto(null, editName, editType, editPrice, editAvailability);
                productoGuardado = this.productoService.guardarProducto(nuevoDto);
                mensajeExito = "Producto Agregado";
            } else {
                ModProductoDto modDto = new ModProductoDto(editName, editType, editPrice, editAvailability);
                productoGuardado = this.productoService.modificarProducto(this.productoSeleccionado.id(), modDto);
                mensajeExito = "Producto Modificado";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeExito));
            cargarDatos();
            PrimeFaces.current().ajax().update("formProductos:tablaProductos", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalProducto').hide()");
            this.productoSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar/modificar producto", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            e.getMessage() != null ? e.getMessage() : "No se pudo guardar el producto."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }


    public void eliminarProducto() {
        if (this.productoSeleccionado == null || this.productoSeleccionado.id() == null) return;
        try {
            this.productoService.eliminarProducto(this.productoSeleccionado.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado"));
            cargarDatos();
            PrimeFaces.current().ajax().update("formProductos:tablaProductos", "growlMensajes");
            this.productoSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar producto", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                            e.getMessage() != null ? e.getMessage() : "No se pudo eliminar el producto."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void cancelarProducto() {
        this.productoSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalProducto').hide()");
    }
}