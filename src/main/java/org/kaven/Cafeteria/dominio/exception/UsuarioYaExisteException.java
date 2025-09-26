package org.kaven.Cafeteria.dominio.exception;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String correo) {
        super("El usuario con correo " + correo + " ya existe en el sistema");
    }
}