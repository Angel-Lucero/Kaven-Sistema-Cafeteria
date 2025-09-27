package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.EstudianteDto;
import org.kaven.Cafeteria.dominio.dto.ModEstudianteDto;
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
public class WebTablaEstudiantesController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaEstudiantesController.class);

    @Autowired
    private EstudianteService estudianteService;

    private List<EstudianteDto> estudiantes;
    private EstudianteDto estudianteSeleccionado;

    private String editName;
    private String editMail;
    private String editPhone;
    private String editCareer;

    public List<String> getCarreras() {
        return List.of("INFORMATICA", "DIBUJO_TECNICO", "MECANICA", "ELECTRONICA", "ELECTRICIDAD");
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.estudiantes = this.estudianteService.obtenerTodoEstudiante();
        } catch (Exception e) {
            logger.error("Error al cargar los estudiantes", e);
            this.estudiantes = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los estudiantes."));
        }
    }

    public void agregarEstudiante() {
        this.estudianteSeleccionado = null;
        this.editName = "";
        this.editMail = "";
        this.editPhone = "";
        this.editCareer = "INFORMATICA";
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
            EstudianteDto estudianteGuardado;
            String mensajeExito;

            if (this.estudianteSeleccionado == null || this.estudianteSeleccionado.id() == null) {
                EstudianteDto nuevoDto = new EstudianteDto(null, editName, editMail, editPhone, editCareer, null);
                estudianteGuardado = this.estudianteService.guardarEstudiante(nuevoDto);
                mensajeExito = "Estudiante Agregado";
            } else {
                ModEstudianteDto modDto = new ModEstudianteDto(editName, editMail, editPhone, editCareer);
                estudianteGuardado = this.estudianteService.modificarEstudiante(this.estudianteSeleccionado.id(), modDto);
                mensajeExito = "Estudiante Modificado";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeExito));
            cargarDatos();
            PrimeFaces.current().ajax().update("formEstudiantes:tablaEstudiantes", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalEstudiante').hide()");
            this.estudianteSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar/modificar estudiante", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo guardar el estudiante."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void eliminarEstudiante() {
        if (this.estudianteSeleccionado == null || this.estudianteSeleccionado.id() == null) return;
        try {
            this.estudianteService.eliminarEstudiante(this.estudianteSeleccionado.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estudiante Eliminado"));
            cargarDatos();
            PrimeFaces.current().ajax().update("formEstudiantes:tablaEstudiantes", "growlMensajes");
            this.estudianteSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar estudiante", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo eliminar el estudiante."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void cancelarEstudiante() {
        this.estudianteSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalEstudiante').hide()");
    }
}