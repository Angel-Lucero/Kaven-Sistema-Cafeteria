package org.kaven.Cafeteria.dominio.exception;

public class UsuarioNoExisteException extends RuntimeException {
    public UsuarioNoExisteException(String identificador) {
        super("El usuario con identificador " + identificador + " no existe en el sistema");
    }
}