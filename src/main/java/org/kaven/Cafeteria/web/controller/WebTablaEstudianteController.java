package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.service.EstudianteService;
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
public class WebTablaEstudianteController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaEstudianteController.class);

    @Autowired
    private EstudianteService estudianteService;

    private List<EstudianteDto> estudiantes;
    private EstudianteDto estudianteSeleccionado;

    private String editName;
    private String editMail;
    private String editPhone;
    private String editCareer;

    public WebTablaEstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    public WebTablaEstudianteController() {
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.estudiantes = estudianteService.obtenerTodoEstudiante();
        estudiantes.forEach(estudiante -> logger.info(estudiante.toString()));
    }

    public void refresh() {
        try {
            this.estudiantes = new ArrayList<>(estudianteService.obtenerTodoEstudiante());
        } catch (Exception e) {
            this.estudiantes = new ArrayList<>();
        }
    }

    public void agregarEstudiante() {
        this.estudianteSeleccionado = null; // Nuevo
        this.editName = "";
        this.editMail = "";
        this.editPhone = "";
        this.editCareer = "";
        PrimeFaces.current().executeScript("PF('ventanaModalEstudiante').show()");
    }

    public void prepararEdicion(EstudianteDto estudiante) {
        this.estudianteSeleccionado = estudiante;
        if (estudiante != null) {
            this.editName = estudiante.name();
            this.editMail = estudiante.mail();
            this.editPhone = estudiante.phone();
            this.editCareer = estudiante.career();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalEstudiante').show()");
    }

    public void guardarEstudiante() {
        try {
            EstudianteDto dto;
            if (this.estudianteSeleccionado == null || this.estudianteSeleccionado.id() == null) {
                dto = new EstudianteDto(null, editName, editMail, editPhone, editCareer, null);
            } else {
                dto = new EstudianteDto(this.estudianteSeleccionado.id(), editName, editMail, editPhone, editCareer, null);
            }

            EstudianteDto guardado = this.estudianteService.guardarEstudiante(dto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    this.estudianteSeleccionado == null || this.estudianteSeleccionado.id() == null
                            ? "Estudiante Agregado"
                            : "Estudiante Modificado"
            ));

            refresh();
            this.estudianteSeleccionado = guardado;
            PrimeFaces.current().ajax().update("formEstudiantes:tablaEstudiantes", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalEstudiante').hide()");
            this.estudianteSeleccionado = null; // Limpiar la selección tras cerrar
        } catch (Exception e) {
            logger.error("Error al guardar estudiante", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarEstudiante() {
        if (this.estudianteSeleccionado == null || this.estudianteSeleccionado.id() == null) return;
        try {
            this.estudianteService.eliminarEstudiante(this.estudianteSeleccionado.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estudiante Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("formEstudiantes:tablaEstudiantes", "growlMensajes");
            this.estudianteSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar estudiante", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarEstudiante() {
        this.estudianteSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalEstudiante').hide()");
    }

    public void delete(EstudianteDto estudiante) { // Legacy
        if (estudiante == null || estudiante.id() == null) return;
        try {
            estudianteService.eliminarEstudiante(estudiante.id());
            refresh();
        } catch (Exception e) {
            logger.error("Error legacy delete", e);
        }
    }
}
