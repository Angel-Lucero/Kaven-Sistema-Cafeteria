package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.ModPedidoDto;
import org.kaven.Cafeteria.dominio.dto.PedidoDto;
import org.kaven.Cafeteria.dominio.service.PedidoService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ViewScoped
public class WebTablaPedidosController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaPedidosController.class);

    @Autowired
    private PedidoService pedidoService;

    private List<PedidoDto> pedidos;
    private PedidoDto pedidoSeleccionado;

    private Long editStudentid;
    private LocalDate editOrderdate;
    private BigDecimal editTotal;
    private String editState;

    public List<String> getEstadosPedido() {
        return List.of("PENDIENTE", "EN_PROCESO", "ENTREGADO", "CANCELADO");
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.pedidos = this.pedidoService.obtenerTodoPedido();
        } catch (Exception e) {
            logger.error("Error al cargar los pedidos", e);
            this.pedidos = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los pedidos."));
        }
    }

    public void agregarPedido() {
        this.pedidoSeleccionado = null;
        this.editStudentid = null;
        this.editOrderdate = LocalDate.now();
        this.editTotal = BigDecimal.ZERO;
        this.editState = "PENDIENTE";
        PrimeFaces.current().executeScript("PF('ventanaModalPedido').show()");
    }

    public void prepararEdicion(PedidoDto pedido) {
        this.pedidoSeleccionado = pedido;
        if (pedido != null) {
            this.editStudentid = pedido.studentid();
            this.editOrderdate = pedido.orderdate();
            this.editTotal = pedido.total();
            this.editState = pedido.state();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalPedido').show()");
    }

    public void guardarPedido() {
        try {
            PedidoDto pedidoGuardado;
            String mensajeExito;

            if (this.pedidoSeleccionado == null || this.pedidoSeleccionado.id() == null) {
                PedidoDto nuevoDto = new PedidoDto(null, editStudentid, editOrderdate, editTotal, editState);
                pedidoGuardado = this.pedidoService.guardarPedido(nuevoDto);
                mensajeExito = "Pedido Agregado";
            } else {
                ModPedidoDto modDto = new ModPedidoDto(editStudentid, editOrderdate, editTotal, editState);
                pedidoGuardado = this.pedidoService.modificarPedido(this.pedidoSeleccionado.id(), modDto);
                mensajeExito = "Pedido Modificado";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeExito));
            cargarDatos();
            PrimeFaces.current().ajax().update("formPedidos:tablaPedidos", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalPedido').hide()");
            this.pedidoSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar/modificar pedido", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo guardar el pedido."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void eliminarPedido() {
        if (this.pedidoSeleccionado == null || this.pedidoSeleccionado.id() == null) return;
        try {
            this.pedidoService.eliminarPedido(this.pedidoSeleccionado.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido Eliminado"));
            cargarDatos();
            PrimeFaces.current().ajax().update("formPedidos:tablaPedidos", "growlMensajes");
            this.pedidoSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar pedido", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo eliminar el pedido."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void cancelarPedido() {
        this.pedidoSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalPedido').hide()");
    }
}