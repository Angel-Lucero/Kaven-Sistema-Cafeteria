package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.FacturaDto;
import org.kaven.Cafeteria.dominio.service.FacturaService;
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
public class WebTablaFacturasController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaFacturasController.class);

    @Autowired
    private FacturaService facturaService;

    private List<FacturaDto> facturas;
    private FacturaDto facturaSeleccionada;

    private Long editStudentId;
    private Long editOrderId;
    private BigDecimal editTotal;
    private String editPaymentType;

    public WebTablaFacturasController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    public WebTablaFacturasController() {
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.facturas = facturaService.obtenerTodoFactura();
        facturas.forEach(factura -> logger.info(factura.toString()));
    }

    public void refresh() {
        try {
            this.facturas = new ArrayList<>(facturaService.obtenerTodoFactura());
        } catch (Exception e) {
            this.facturas = new ArrayList<>();
        }
    }

    public void agregarFactura() {
        this.facturaSeleccionada = null; // Nuevo
        this.editStudentId = null;
        this.editOrderId = null;
        this.editTotal = BigDecimal.ZERO;
        this.editPaymentType = "";
        PrimeFaces.current().executeScript("PF('ventanaModalFactura').show()");
    }

    public void prepararEdicion(FacturaDto factura) {
        this.facturaSeleccionada = factura;
        if (factura != null) {
            this.editStudentId = factura.studentId();
            this.editOrderId = factura.ordersId();
            this.editTotal = factura.total();
            this.editPaymentType = factura.paymentType();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalFactura').show()");
    }

    public void guardarFactura() {
        try {
            FacturaDto dto;
            if (this.facturaSeleccionada == null || this.facturaSeleccionada.id() == null) {
                dto = new FacturaDto(null, editStudentId, editOrderId, editTotal, editPaymentType);
            } else {
                dto = new FacturaDto(this.facturaSeleccionada.id(), editStudentId, editOrderId, editTotal, editPaymentType);
            }

            FacturaDto guardado = this.facturaService.guardarFactura(dto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    this.facturaSeleccionada == null || this.facturaSeleccionada.id() == null
                            ? "Factura Agregada"
                            : "Factura Modificada"
            ));

            refresh();
            this.facturaSeleccionada = guardado;
            PrimeFaces.current().ajax().update("formFacturas:tablaFacturas", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalFactura').hide()");
            this.facturaSeleccionada = null; // Limpiar la selección tras cerrar
        } catch (Exception e) {
            logger.error("Error al guardar factura", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarFactura() {
        if (this.facturaSeleccionada == null || this.facturaSeleccionada.id() == null) return;
        try {
            this.facturaService.eliminarFactura(this.facturaSeleccionada.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Factura Eliminada"));
            refresh();
            PrimeFaces.current().ajax().update("formFacturas:tablaFacturas", "growlMensajes");
            this.facturaSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al eliminar factura", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarFactura() {
        this.facturaSeleccionada = null;
        PrimeFaces.current().executeScript("PF('ventanaModalFactura').hide()");
    }

    public void delete(FacturaDto factura) { // Legacy
        if (factura == null || factura.id() == null) return;
        try {
            facturaService.eliminarFactura(factura.id());
            refresh();
        } catch (Exception e) {
            logger.error("Error legacy delete", e);
        }
    }
}
