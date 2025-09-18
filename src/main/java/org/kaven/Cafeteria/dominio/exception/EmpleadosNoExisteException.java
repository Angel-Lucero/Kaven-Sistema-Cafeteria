package org.kaven.Cafeteria.dominio.exception;

public class EmpleadosNoExisteException extends RuntimeException {
    public EmpleadosNoExisteException(String empleadosnocodigo) {
        super("El empleado" + empleadosnocodigo + "no existe en el sistema");
    }
}
