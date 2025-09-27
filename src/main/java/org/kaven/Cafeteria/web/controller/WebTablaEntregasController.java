package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
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

    public List<String> getEstadosEntrega() {
        return List.of("PENDING", "DELIVERED", "CANCELLED");
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.entregas = this.entregaService.obtenerTodoEntregas();
        } catch (Exception e) {
            logger.error("Error al cargar las entregas", e);
            this.entregas = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar las entregas."));
        }
    }

    public void agregarEntrega() {
        this.entregaSeleccionada = null;
        this.editOrderId = null;
        this.editEmployeeId = null;
        this.editDeliveryStatus = "PENDING";
        this.editDeliveryDate = LocalDate.now();
        PrimeFaces.current().executeScript("PF('ventanaModalEntrega').show()");
    }

    public void prepararEdicion(EntregaDto entrega) {
        this.entregaSeleccionada = entrega;
        if (entrega != null) {
            this.editOrderId = entrega.orderId();
            this.editEmployeeId = entrega.employeeId();
            this.editDeliveryStatus = entrega.deliveryStatus();
            this.editDeliveryDate = entrega.deliveryDate();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalEntrega').show()");
    }

    public void guardarEntrega() {
        try {
            EntregaDto entregaGuardada;
            String mensajeExito;

            if (this.entregaSeleccionada == null || this.entregaSeleccionada.id() == null) {
                EntregaDto nuevoDto = new EntregaDto(null, editOrderId, editEmployeeId, editDeliveryStatus, editDeliveryDate);
                entregaGuardada = this.entregaService.guardarEntrega(nuevoDto);
                mensajeExito = "Entrega Agregada";
            } else {
                ModEntregaDto modDto = new ModEntregaDto(editOrderId, editEmployeeId, editDeliveryStatus, editDeliveryDate);
                entregaGuardada = this.entregaService.modificarEntrega(this.entregaSeleccionada.id(), modDto);
                mensajeExito = "Entrega Modificada";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeExito));
            cargarDatos();
            PrimeFaces.current().ajax().update("formEntregas:tablaEntregas", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalEntrega').hide()");
            this.entregaSeleccionada = null;

        } catch (Exception e) {
            logger.error("Error al guardar/modificar entrega", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo guardar la entrega."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void eliminarEntrega() {
        if (this.entregaSeleccionada == null || this.entregaSeleccionada.id() == null) return;
        try {
            this.entregaService.eliminarEntrega(this.entregaSeleccionada.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Entrega Eliminada"));
            cargarDatos();
            PrimeFaces.current().ajax().update("formEntregas:tablaEntregas", "growlMensajes");
            this.entregaSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al eliminar entrega", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo eliminar la entrega."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void cancelarEntrega() {
        this.entregaSeleccionada = null;
        PrimeFaces.current().executeScript("PF('ventanaModalEntrega').hide()");
    }
}