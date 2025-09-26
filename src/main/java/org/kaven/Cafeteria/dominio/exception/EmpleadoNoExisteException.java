package org.kaven.Cafeteria.dominio.exception;

public class EmpleadoNoExisteException extends RuntimeException {
    public EmpleadoNoExisteException(String codigo) {
        super("El empleado con código " + codigo + " no existe en el sistema");
    }
}
