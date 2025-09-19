package org.kaven.Cafeteria.dominio.exception;

public class EmpleadoYaExisteException extends RuntimeException {
    public EmpleadoYaExisteException(String nombre) {
        super("El empleado con nombre " + nombre + " ya existe en el sistema");
    }
}
