package org.kaven.Cafeteria.dominio.exception;

public class EstudianteYaExisteException extends RuntimeException {
    public EstudianteYaExisteException(String estudianteNombre) {
        super("El estudiante: " + estudianteNombre + " ya existe");
    }
}
