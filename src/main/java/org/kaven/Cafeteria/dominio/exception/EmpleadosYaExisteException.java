package org.kaven.Cafeteria.dominio.exception;

public class EmpleadosYaExisteException extends RuntimeException {
    public EmpleadosYaExisteException(String message) {
        super(message);
    }
}
