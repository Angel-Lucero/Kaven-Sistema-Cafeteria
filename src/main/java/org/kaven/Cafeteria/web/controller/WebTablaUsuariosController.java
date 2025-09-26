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
@ViewScoped
@Data
public class WebTablaUsuariosController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(WebTablaUsuariosController.class);

    @Autowired
    UsuarioService usuarioService;
    private List<UsuarioDto> usuarios;
    private UsuarioDto usuarioSeleccionado;

    private String editMail;
    private String editPassword;
    private String editUsertype;
    private Long editStudentid;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.usuarios = this.usuarioService.obtenerTodoUsuario();
        this.usuarios.forEach(usuario -> logger.info(usuario.toString()));
    }

    public void refresh() {
        try {
            this.usuarios = new ArrayList<>(usuarioService.obtenerTodoUsuario());
        } catch (Exception e) {
            this.usuarios = new ArrayList<>();
        }
    }

    public void agregarUsuario() {
        this.usuarioSeleccionado = null;
        this.editMail = "";
        this.editPassword = "";
        this.editUsertype = "STUDENT"; // Valor por defecto
        this.editStudentid = null;
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').show()");
    }

    public void prepararEdicion(UsuarioDto u) {
        this.usuarioSeleccionado = u;
        if (u != null) {
            this.editMail = u.mail();
            this.editPassword = u.password();
            this.editUsertype = u.usertype();
            this.editStudentid = u.studentid();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').show()");
    }

    public void guardarUsuario() {
        try {
            UsuarioDto dto;
            if (this.usuarioSeleccionado == null || this.usuarioSeleccionado.id() == null) {
                // Nuevo usuario - todos los parámetros incluyendo usertype
                dto = new UsuarioDto(null, editMail, editPassword, editUsertype, editStudentid);
            } else {
                // Usuario existente - todos los parámetros incluyendo usertype
                dto = new UsuarioDto(this.usuarioSeleccionado.id(), editMail, editPassword, editUsertype, editStudentid);
            }
            UsuarioDto guardado = this.usuarioService.guardarUsuario(dto);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(this.usuarioSeleccionado == null || this.usuarioSeleccionado.id() == null ?
                            "Usuario Agregado" : "Usuario Modificado"));
            refresh();
            this.usuarioSeleccionado = guardado;
            PrimeFaces.current().ajax().update("formUsuarios:tablaUsuarios", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
            this.usuarioSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al guardar usuario", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar"));
        }
    }

    public void eliminarUsuario() {
        if (this.usuarioSeleccionado == null || this.usuarioSeleccionado.id() == null) return;
        try {
            this.usuarioService.eliminarUsuario(String.valueOf(this.usuarioSeleccionado.id()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Eliminado"));
            refresh();
            PrimeFaces.current().ajax().update("formUsuarios:tablaUsuarios", "growlMensajes");
            this.usuarioSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar usuario", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar"));
        }
    }

    public void cancelarUsuario() {
        this.usuarioSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
    }
}