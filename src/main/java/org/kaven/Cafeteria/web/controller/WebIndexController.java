package org.kaven.Cafeteria.web.controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ViewScoped
@Data
public class WebIndexController implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(WebIndexController.class);

    @Autowired
    private transient UsuarioService usuarioService;

    private String usuario;
    private String password;

    public String iniciarSesion() {
        try {
            if (usuario == null || usuario.trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El correo es requerido"));
                return null;
            }

            if (password == null || password.trim().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña es requerida"));
                return null;
            }

            var usuarioDto = usuarioService.obtenerUsuarioPorCodigo(usuario);

            if (usuarioDto != null && usuarioDto.password().equals(password)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Inicio de sesión exitoso"));
                logger.info("Usuario {} inició sesión correctamente", usuario);

                if ("ADMINISTRACION".equalsIgnoreCase(usuarioDto.usertype())) {
                    logger.info("Usuario {} identificado como administrador", usuario);
                    return "Administracion?faces-redirect=true";
                } else {
                    logger.info("Usuario {} identificado como usuario normal", usuario);
                    return "Inicio?faces-redirect=true";
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales inválidas"));
                return null;
            }

        } catch (Exception e) {
            logger.error("Error al iniciar sesión", e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrectos"));
            return null;
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/Index.xhtml?faces-redirect=true";
    }
}