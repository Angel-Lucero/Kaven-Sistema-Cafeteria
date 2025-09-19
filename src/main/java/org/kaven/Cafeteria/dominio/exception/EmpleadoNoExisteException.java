package org.kaven.Cafeteria.dominio.exception;

public class EmpleadoNoExisteException extends RuntimeException {
    public EmpleadoNoExisteException(String empleadosnocodigo) {
        super("El empleado" + empleadosnocodigo + "no existe en el sistema");
    }
}
