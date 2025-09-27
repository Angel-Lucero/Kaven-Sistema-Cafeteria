package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.EntregaDto;
import org.kaven.Cafeteria.dominio.dto.ModEntregaDto;
import org.kaven.Cafeteria.dominio.service.EntregaService;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Named
@ViewScoped
public class EntregaBean implements Serializable {

    @Inject
    private EntregaService entregaService;

    private List<EntregaDto> listaEntregas;
    private List<EntregaDto> entregasFiltradas;
    private EntregaDto entregaSeleccionada;
    private boolean modoEdicion;

    @PostConstruct
    public void init() {
        cargarEntregas();
        prepararNuevaEntrega();
    }

    public void cargarEntregas() {
        try {
            listaEntregas = entregaService.obtenerTodoEntregas();
            entregasFiltradas = new ArrayList<>(listaEntregas);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar las entregas: " + e.getMessage()));
        }
    }

    public void prepararNuevaEntrega() {
        entregaSeleccionada = new EntregaDto(null, null, null, "PENDING", LocalDate.now());
        modoEdicion = false;
    }

    public void prepararEdicion(EntregaDto entrega) {
        this.entregaSeleccionada = entrega;
        this.modoEdicion = true;
    }

    public void guardarEntrega() {
        try {
            EntregaDto nuevaEntrega = entregaService.guardarEntrega(entregaSeleccionada);
            cargarEntregas();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Entrega creada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al crear la entrega: " + e.getMessage()));
        }
    }

    public void actualizarEntrega() {
        try {
            ModEntregaDto modEntregaDto = new ModEntregaDto(
                    entregaSeleccionada.orderId(),
                    entregaSeleccionada.employeeId(),
                    entregaSeleccionada.deliveryStatus(),
                    entregaSeleccionada.deliveryDate()
            );

            EntregaDto entregaActualizada = entregaService.modificarEntrega(entregaSeleccionada.id(), modEntregaDto);
            cargarEntregas();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Entrega actualizada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al actualizar la entrega: " + e.getMessage()));
        }
    }

    public void eliminarEntrega(EntregaDto entrega) {
        try {
            entregaService.eliminarEntrega(entrega.id());
            cargarEntregas();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Entrega eliminada correctamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al eliminar la entrega: " + e.getMessage()));
        }
    }
}