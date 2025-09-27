package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.FacturaDto;
import org.kaven.Cafeteria.dominio.dto.ModFacturaDto;
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
    private Long editOrdersId;
    private BigDecimal editTotal;
    private String editPaymentType;

    public List<String> getTiposPago() {
        return List.of("EFECTIVO", "TARJETA_CREDITO", "TRANSFERENCIA", "PAGO_MOVIL");
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.facturas = this.facturaService.obtenerTodoFactura();
        } catch (Exception e) {
            logger.error("Error al cargar las facturas", e);
            this.facturas = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar las facturas."));
        }
    }

    public void agregarFactura() {
        this.facturaSeleccionada = null;
        this.editStudentId = null;
        this.editOrdersId = null;
        this.editTotal = BigDecimal.ZERO;
        this.editPaymentType = "EFECTIVO";
        PrimeFaces.current().executeScript("PF('ventanaModalFactura').show()");
    }

    public void prepararEdicion(FacturaDto factura) {
        this.facturaSeleccionada = factura;
        if (factura != null) {
            this.editStudentId = factura.studentId();
            this.editOrdersId = factura.ordersId();
            this.editTotal = factura.total();
            this.editPaymentType = factura.paymentType();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalFactura').show()");
    }

    public void guardarFactura() {
        try {
            FacturaDto facturaGuardada;
            String mensajeExito;

            if (this.facturaSeleccionada == null || this.facturaSeleccionada.id() == null) {
                FacturaDto nuevoDto = new FacturaDto(null, editStudentId, editOrdersId, editTotal, editPaymentType);
                facturaGuardada = this.facturaService.guardarFactura(nuevoDto);
                mensajeExito = "Factura Agregada";
            } else {
                ModFacturaDto modDto = new ModFacturaDto(editStudentId, editOrdersId, editTotal, editPaymentType);
                facturaGuardada = this.facturaService.modificarFactura(this.facturaSeleccionada.id(), modDto);
                mensajeExito = "Factura Modificada";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeExito));
            cargarDatos();
            PrimeFaces.current().ajax().update("formFacturas:tablaFacturas", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalFactura').hide()");
            this.facturaSeleccionada = null;

        } catch (Exception e) {
            logger.error("Error al guardar/modificar factura", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo guardar la factura."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void eliminarFactura() {
        if (this.facturaSeleccionada == null || this.facturaSeleccionada.id() == null) return;
        try {
            this.facturaService.eliminarFactura(this.facturaSeleccionada.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Factura Eliminada"));
            cargarDatos();
            PrimeFaces.current().ajax().update("formFacturas:tablaFacturas", "growlMensajes");
            this.facturaSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al eliminar factura", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo eliminar la factura."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void cancelarFactura() {
        this.facturaSeleccionada = null;
        PrimeFaces.current().executeScript("PF('ventanaModalFactura').hide()");
    }
}