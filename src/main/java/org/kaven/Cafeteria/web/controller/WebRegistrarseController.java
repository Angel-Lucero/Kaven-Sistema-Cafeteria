package org.kaven.Cafeteria.web.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@ViewScoped
@Data
public class WebRegistrarseController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(WebRegistrarseController.class);

    @Autowired
    private UsuarioService usuarioService;

    // Campos del formulario
    private String nombre;
    private String apellido;
    private String telefono;
    private String genero;
    private Date fechaNacimiento;
    private String correo;
    private String contrasena;

    public void guardarUsuario() {
        try {
            if (correo == null || correo.trim().isEmpty()) {
                mostrarError("El correo es requerido");
                return;
            }

            if (contrasena == null || contrasena.trim().isEmpty()) {
                mostrarError("La contraseña es requerida");
                return;
            }

            UsuarioDto nuevoUsuario = new UsuarioDto(null, correo, contrasena, "STUDENT", null);
            UsuarioDto usuarioGuardado = usuarioService.guardarUsuario(nuevoUsuario);

            mostrarExito("Usuario registrado correctamente");
            logger.info("Usuario {} registrado con ID: {}", correo, usuarioGuardado.id());

            limpiarCampos();

        } catch (Exception e) {
            logger.error("Error al registrar usuario", e);
            mostrarError("Error al registrar usuario: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }

    private void mostrarExito(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", mensaje));
    }

    private void limpiarCampos() {
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.genero = "";
        this.fechaNacimiento = null;
        this.correo = "";
        this.contrasena = "";
    }
}