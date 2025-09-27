package org.kaven.Cafeteria.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.kaven.Cafeteria.dominio.dto.UsuarioDto;
import org.kaven.Cafeteria.dominio.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Data
public class WebInicioController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(WebInicioController.class);

    @Autowired
    private UsuarioService usuarioService;

    private List<UsuarioDto> usuarios;
    private int totalUsuarios;
    private int totalAdministradores;
    private int totalEstudiantes;

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        try {
            this.usuarios = usuarioService.obtenerTodoUsuario();
            this.totalUsuarios = usuarios != null ? usuarios.size() : 0;

            // Contar administradores y estudiantes
            this.totalAdministradores = (int) usuarios.stream()
                    .filter(u -> "ADMIN".equalsIgnoreCase(u.usertype()))
                    .count();
            this.totalEstudiantes = totalUsuarios - totalAdministradores;

            logger.info("Cargados {} usuarios ({} administradores, {} estudiantes)",
                    totalUsuarios, totalAdministradores, totalEstudiantes);
        } catch (Exception e) {
            logger.error("Error al cargar datos para la página de inicio", e);
            this.totalUsuarios = 0;
            this.totalAdministradores = 0;
            this.totalEstudiantes = 0;
        }
    }

    public String getMensajeBienvenida() {
        return "Bienvenido al Sistema de Cafetería";
    }

    public String getInformacionSistema() {
        return String.format("Hay %d usuarios registrados en el sistema", totalUsuarios);
    }
}