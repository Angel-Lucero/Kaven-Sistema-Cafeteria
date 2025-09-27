package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.CarreraDto;
import org.kaven.Cafeteria.dominio.dto.ModCarreraDto;
import org.kaven.Cafeteria.dominio.service.CarreraService;
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
public class WebTablaCarrerasController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaCarrerasController.class);

    @Autowired
    private CarreraService carreraService;

    private List<CarreraDto> carreras;
    private CarreraDto carreraSeleccionada;

    private String editDegreeName;

    public List<String> getNombresCarreras() {
        return List.of("INFORMATICA", "DIBUJO_TECNICO", "MECANICA", "ELECTRONICA", "ELECTRICIDAD");
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.carreras = this.carreraService.obtenerTodoCarreras();
        } catch (Exception e) {
            logger.error("Error al cargar las carreras", e);
            this.carreras = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar las carreras."));
        }
    }

    public void agregarCarrera() {
        this.carreraSeleccionada = null;
        this.editDegreeName = "INFORMATICA";
        PrimeFaces.current().executeScript("PF('ventanaModalCarrera').show()");
    }

    public void prepararEdicion(CarreraDto carrera) {
        this.carreraSeleccionada = carrera;
        if (carrera != null) {
            this.editDegreeName = carrera.degreeName();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalCarrera').show()");
    }

    public void guardarCarrera() {
        try {
            CarreraDto carreraGuardada;
            String mensajeExito;

            if (this.carreraSeleccionada == null || this.carreraSeleccionada.id() == 0) {
                CarreraDto nuevoDto = new CarreraDto(0, editDegreeName);
                carreraGuardada = this.carreraService.guardarCarreras(nuevoDto);
                mensajeExito = "Carrera Agregada";
            } else {
                ModCarreraDto modDto = new ModCarreraDto(editDegreeName);
                carreraGuardada = this.carreraService.modificarCarreras(this.carreraSeleccionada.id(), modDto);
                mensajeExito = "Carrera Modificada";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeExito));
            cargarDatos();
            PrimeFaces.current().ajax().update("formCarreras:tablaCarreras", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalCarrera').hide()");
            this.carreraSeleccionada = null;

        } catch (Exception e) {
            logger.error("Error al guardar/modificar carrera", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo guardar la carrera."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void eliminarCarrera() {
        if (this.carreraSeleccionada == null || this.carreraSeleccionada.id() == 0) return;
        try {
            this.carreraService.eliminarCarreras(this.carreraSeleccionada.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Carrera Eliminada"));
            cargarDatos();
            PrimeFaces.current().ajax().update("formCarreras:tablaCarreras", "growlMensajes");
            this.carreraSeleccionada = null;
        } catch (Exception e) {
            logger.error("Error al eliminar carrera", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo eliminar la carrera."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void cancelarCarrera() {
        this.carreraSeleccionada = null;
        PrimeFaces.current().executeScript("PF('ventanaModalCarrera').hide()");
    }
}