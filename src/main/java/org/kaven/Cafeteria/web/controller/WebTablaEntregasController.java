package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.service.EntregaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ViewScoped
public class WebTablaEntregasController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaEntregasController.class);

    @Autowired
    private EntregaService entregaService;

    private List<EntregaDto> entregas;
    private EntregaDto entregaSeleccionada;

    private Long editOrderId;
    private Long editEmployeeId;
    private String editDeliveryStatus;
    private LocalDate editDeliveryDate;

    public WebTablaEntregasController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    public WebTablaEntregasController() {
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.entregas = entregaService.obtenerTodoEntregas();
        entregas.forEach(entrega -> logger.info(entrega.toString()));
    }

    public void refresh() {
        try {
            this.entregas = new ArrayList<>(entregaService.obtenerTodoEntregas());
        } catch (Exception e) {
            this.entregas = new ArrayList<>();
        }
    }

    public void agregarEntrega() {
        this.entregaSeleccionada = null; // Nueva entrega
        this.editOrderId = null;
        this.editEmployeeId = null;
        this.editDeliveryStatus = "";
        this.editDeliveryDate = null;
        PrimeFaces.current().executeScript("PF('ventanaModalEntrega').show()");
    }

    public void prepararEdicion(EntregaDto entrega) {
        this.entregaSeleccionada = entrega;
        if (entrega != null) {
            this.editOrderId = entrega.OrderId();
            this.editEmployeeId = entrega.EmployeeId();
            this.editDeliveryStatus = entrega.deliveryStatus();
            this.editDeliveryDate = entrega.devliveryDate();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalEntrega').show()");
    }

    public void guardarEntrega() {
        try {
            EntregaDto dto;
            if (this.entregaSeleccionada == null || this.entregaSeleccionada.id() == null) {
                dto = new EntregaDto(null, editOrderId, editEmployeeId, editDeliveryStatus, editDeliveryDate);
            } else {
                dto = new EntregaDto(this.entregaSeleccionada.id(), editOrderId, editEmployeeId, editDeliveryStatus, editDeliveryDate);
            }

            EntregaDto guardada = this.entregaService.guardarEntrega(dto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    this.entregaSeleccionada == null || this.entregaSeleccionada.id() == null
                            ? "Entrega Agregada"
                            : "Entrega Modificada"
            ));

            refresh();
            this.entregaSeleccionada = guardada;
            PrimeFaces.current().ajax().update("formEntregas:tablaEntregas", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalEntrega').hide()");
            this.entregaSeleccionada = null; // Limpiar la selección tras cerrar
        } catch (Exception e) {
            logger.error("Error al guardar entrega", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarEntrega() {
        if (this.entregaSeleccionada == null || this.entregaSeleccionada.id() == null) return;
        try {
            this.entregaService.eliminarEntrega(this.entregaSeleccionada.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Entrega Eliminada"));
            refresh();
            PrimeFaces.current().ajax().update("formEntregas:tablaEntregas", "growlMensajes");
            this.entregaSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al eliminar entrega", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarEntrega() {
        this.entregaSeleccionada = null;
        PrimeFaces.current().executeScript("PF('ventanaModalEntrega').hide()");
    }

    public void delete(EntregaDto entrega) { // Legacy
        if (entrega == null || entrega.id() == null) return;
        try {
            entregaService.eliminarEntrega(entrega.id());
            refresh();
        } catch (Exception e) {
            logger.error("Error legacy delete", e);
        }
    }
}
