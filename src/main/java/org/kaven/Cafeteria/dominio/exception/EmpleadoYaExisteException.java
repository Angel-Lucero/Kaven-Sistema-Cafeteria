package org.kaven.Cafeteria.dominio.exception;

public class EmpleadoYaExisteException extends RuntimeException {
    public EmpleadoYaExisteException(String empleadocodigo) {
        super("El empleado" + empleadocodigo + "ya existe en el sistema");
    }
}
