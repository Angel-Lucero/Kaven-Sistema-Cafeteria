package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.dto.ModUsuarioDto;
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
public class WebTablaUsuariosController implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(WebTablaUsuariosController.class);

    @Autowired
    private UsuarioService usuarioService;

    private List<UsuarioDto> usuarios;
    private UsuarioDto usuarioSeleccionado;

    private String editMail;
    private String editPassword;
    private String editUserType;

    public List<String> getTiposUsuario() {
        return List.of("ADMINISTRACION", "ESTUDIANTE");
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.usuarios = this.usuarioService.obtenerTodoUsuario();
        } catch (Exception e) {
            logger.error("Error al cargar los usuarios", e);
            this.usuarios = new ArrayList<>();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los usuarios."));
        }
    }

    public void agregarUsuario() {
        this.usuarioSeleccionado = null;
        this.editMail = "";
        this.editPassword = "";
        this.editUserType = "ESTUDIANTE";
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').show()");
    }

    public void prepararEdicion(UsuarioDto usuario) {
        this.usuarioSeleccionado = usuario;
        if (usuario != null) {
            this.editMail = usuario.mail();
            this.editPassword = usuario.password();
            this.editUserType = usuario.usertype();
        }
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').show()");
    }

    public void guardarUsuario() {
        try {
            UsuarioDto usuarioGuardado;
            String mensajeExito;

            if (this.usuarioSeleccionado == null || this.usuarioSeleccionado.id() == null) {
                UsuarioDto nuevoDto = new UsuarioDto(null, editMail, editPassword, editUserType, null);
                usuarioGuardado = this.usuarioService.guardarUsuario(nuevoDto);
                mensajeExito = "Usuario Agregado";
            } else {
                ModUsuarioDto modDto = new ModUsuarioDto(editMail, editPassword, editUserType);
                usuarioGuardado = this.usuarioService.modificarUsuario(this.usuarioSeleccionado.id().toString(), modDto);
                mensajeExito = "Usuario Modificado";
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(mensajeExito));
            cargarDatos();
            PrimeFaces.current().ajax().update("formUsuarios:tablaUsuarios", "growlMensajes");
            PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
            this.usuarioSeleccionado = null;

        } catch (Exception e) {
            logger.error("Error al guardar/modificar usuario", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo guardar el usuario."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void eliminarUsuario() {
        if (this.usuarioSeleccionado == null || this.usuarioSeleccionado.id() == null) return;
        try {
            this.usuarioService.eliminarUsuario(this.usuarioSeleccionado.id().toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Eliminado"));
            cargarDatos();
            PrimeFaces.current().ajax().update("formUsuarios:tablaUsuarios", "growlMensajes");
            this.usuarioSeleccionado = null;
        } catch (Exception e) {
            logger.error("Error al eliminar usuario", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage() != null ? e.getMessage() : "No se pudo eliminar el usuario."));
            PrimeFaces.current().ajax().update("growlMensajes");
        }
    }

    public void cancelarUsuario() {
        this.usuarioSeleccionado = null;
        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
    }
}