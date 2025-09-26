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
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ViewScoped
public class WebTablaPedidosController implements Serializable {

    @Autowired
    private PedidoService pedidoService;

    private List<PedidoDto> pedidos;
    private PedidoDto pedidoSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(WebTablaPedidosController.class);

    // Variables para el formulario
    private Long editStudentId;
    private String editState;
    private String editTotal;
    private String editOrderDate;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.pedidos = this.pedidoService.obtenerTodoPedido();
        this.pedidos.forEach(pedido -> logger.info(pedido.toString()));
    }

    public void refresh() {
        try {
            this.pedidos = new ArrayList<>(pedidoService.obtenerTodoPedido());
        } catch (Exception e) {
            this.pedidos = new ArrayList<>();
        }
    }

    public void agregarPedido() {
        this.pedidoSeleccionado = null;  // Nuevo pedido
        this.editStudentId = null;
        this.editState = "Pendiente";
        this.editTotal = "0.0";
        this.editOrderDate = "";
        PrimeFaces.current().executeScript("PF('ventanaModalPedido').show()");
    }

    public void prepararEdicion(PedidoDto p) {
        this.pedidoSeleccionado = p;
        if (p != null) {
            this.editStudentId = p.studentid();
            this.editState = p.state();
            this.editTotal = String.valueOf(p.total());
            this.editOrderDate = p.orderdate().toString();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalPedido').show()");
    }

    public void guardarPedido() {
        try {
            ModPedidoDto modPedidoDto = new ModPedidoDto(
                    this.editStudentId,
                    LocalDate.parse(this.editOrderDate),
                    new BigDecimal(this.editTotal),
                    this.editState
            );

            PedidoDto pedidoGuardado;
            if (this.pedidoSeleccionado != null && this.pedidoSeleccionado.id() != null) {
                pedidoGuardado = this.pedidoService.modificarPedido(this.pedidoSeleccionado.id(), modPedidoDto);
            } else {
                pedidoGuardado = this.pedidoService.guardarPedido(new PedidoDto(null, this.editStudentId, LocalDate.parse(this.editOrderDate), new BigDecimal(this.editTotal), this.editState));
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido " + (this.pedidoSeleccionado == null ? "Agregado" : "Modificado")));
            refresh();
            this.pedidoSeleccionado = pedidoGuardado;
            PrimeFaces.current().ajax().update("formPedidos:tablaPedidos", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalPedido').hide()");
            this.pedidoSeleccionado = null; // Limpiar selección
        } catch (Exception e) {
            logger.error("Error al guardar pedido", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarPedido() {
        if (this.pedidoSeleccionado == null || this.pedidoSeleccionado.id() == null) return;
        try {
            this.pedidoService.eliminarPedido(this.pedidoSeleccionado.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("formPedidos:tablaPedidos", "growlMensajes");
            this.pedidoSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar pedido", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarPedido() {
        this.pedidoSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalPedido').hide()");
    }
}
