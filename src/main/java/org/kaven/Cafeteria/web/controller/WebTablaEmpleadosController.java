package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.EmpleadoDto;
import org.kaven.Cafeteria.dominio.dto.ModEmpleadoDto;
import org.kaven.Cafeteria.dominio.service.EmpleadoService;
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
public class WebTablaEmpleadosController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaEmpleadosController.class);

    @Autowired
    private EmpleadoService empleadoService;

    private List<EmpleadoDto> empleados;
    private EmpleadoDto empleadoSeleccionado;

    private String editName;
    private String editRole;
    private String editShift;

    public List<String> getRoles() {
        return List.of("CAJERO", "COCINERO", "ADMINISTRADOR", "REPARTIDOR", "BARISTA");
    }

    public List<String> getTurnos() {
        return List.of("MAÑANA", "TARDE", "NOCHE");
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.empleados = this.empleadoService.obtenerTodoEmpleado();
        } catch (Exception e) {
            logger.error("Error al cargar los empleados", e);
            this.empleados = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los empleados."));
        }
    }

    public void agregarEmpleado() {
        this.empleadoSeleccionado = null;
        this.editName = "";
        this.editRole = "CAJERO";
        this.editShift = "MAÑANA";
        PrimeFaces.current().executeScript("PF('ventanaModalEmpleado').show()");
    }

    public void prepararEdicion(EmpleadoDto empleado) {
        this.empleadoSeleccionado = empleado;
        if (empleado != null) {
            this.editName = empleado.name();
            this.editRole = empleado.role();
            this.editShift = empleado.shift();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalEmpleado').show()");
    }

    public void guardarEmpleado() {
        try {
            EmpleadoDto empleadoGuardado;
            String mensajeExito;

            if (this.empleadoSeleccionado == null || this.empleadoSeleccionado.id() == null) {
                EmpleadoDto nuevoDto = new EmpleadoDto(null, editName, editRole, editShift);
                empleadoGuardado = this.empleadoService.guardarEmpleado(nuevoDto);
                mensajeExito = "Empleado Agregado";
            } else {
                ModEmpleadoDto modDto = new ModEmpleadoDto(editName, editRole, editShift);
                empleadoGuardado = this.empleadoService.modificarEmpleado(this.empleadoSeleccionado.id(), modDto);
                mensajeExito = "Empleado Modificado";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeExito));
            cargarDatos();
            PrimeFaces.current().ajax().update("formEmpleados:tablaEmpleados", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalEmpleado').hide()");
            this.empleadoSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar/modificar empleado", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo guardar el empleado."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void eliminarEmpleado() {
        if (this.empleadoSeleccionado == null || this.empleadoSeleccionado.id() == null) return;
        try {
            this.empleadoService.eliminarEmpleado(this.empleadoSeleccionado.id());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado Eliminado"));
            cargarDatos();
            PrimeFaces.current().ajax().update("formEmpleados:tablaEmpleados", "growlMensajes");
            this.empleadoSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar empleado", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo eliminar el empleado."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void cancelarEmpleado() {
        this.empleadoSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalEmpleado').hide()");
    }
}