package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.service.UsuarioService;
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
public class WebTablaUsuarioController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaUsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    private List<UsuarioDto> usuarios;
    private UsuarioDto usuarioSeleccionado;

    private String editMail;
    private String editPassword;

    public WebTablaUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public WebTablaUsuarioController() {
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.usuarios = usuarioService.obtenerTodoUsuario();
        usuarios.forEach(usuario -> logger.info(usuario.toString()));
    }

    public void refresh() {
        try {
            this.usuarios = new ArrayList<>(usuarioService.obtenerTodoUsuario());
        } catch (Exception e) {
            this.usuarios = new ArrayList<>();
        }
    }

    public void agregarUsuario() {
        this.usuarioSeleccionado = null; // Nuevo
        this.editMail = "";
        this.editPassword = "";
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').show()");
    }

    public void prepararEdicion(UsuarioDto usuario) {
        this.usuarioSeleccionado = usuario;
        if (usuario != null) {
            this.editMail = usuario.mail();
            this.editPassword = usuario.password();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').show()");
    }

    public void guardarUsuario() {
        try {
            UsuarioDto dto;
            if (this.usuarioSeleccionado == null || this.usuarioSeleccionado.id() == null) {
                dto = new UsuarioDto(null, editMail, editPassword, null); // El campo `studentid` es opcional
            } else {
                dto = new UsuarioDto(this.usuarioSeleccionado.id(), editMail, editPassword, this.usuarioSeleccionado.studentid());
            }

            UsuarioDto guardado = this.usuarioService.guardarUsuario(dto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    this.usuarioSeleccionado == null || this.usuarioSeleccionado.id() == null
                            ? "Usuario Agregado"
                            : "Usuario Modificado"
            ));

            refresh();
            this.usuarioSeleccionado = guardado;
            PrimeFaces.current().ajax().update("formUsuarios:tablaUsuarios", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
            this.usuarioSeleccionado = null; // Limpiar la selección tras cerrar
        } catch (Exception e) {
            logger.error("Error al guardar usuario", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarUsuario() {
        if (this.usuarioSeleccionado == null || this.usuarioSeleccionado.id() == null) return;
        try {
            this.usuarioService.eliminarUsuario(this.usuarioSeleccionado.id().toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("formUsuarios:tablaUsuarios", "growlMensajes");
            this.usuarioSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar usuario", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarUsuario() {
        this.usuarioSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
    }

    public void delete(UsuarioDto usuario) { // Legacy
        if (usuario == null || usuario.id() == null) return;
        try {
            usuarioService.eliminarUsuario(usuario.id().toString());
            refresh();
        } catch (Exception e) {
            logger.error("Error legacy delete", e);
        }
    }
}
