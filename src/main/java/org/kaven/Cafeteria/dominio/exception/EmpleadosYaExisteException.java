package org.kaven.Cafeteria.dominio.exception;

public class EmpleadosYaExisteException extends RuntimeException {
    public EmpleadosYaExisteException(String empleadocodigo) {
        super("El empleado" + empleadocodigo + "ya existe en el sistema");
    }
}
