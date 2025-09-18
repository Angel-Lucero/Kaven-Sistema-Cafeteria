package org.kaven.Cafeteria.dominio.exception;

public class EstudianteNoExisteException extends RuntimeException {
    public EstudianteNoExisteException(String estudianteNocodigo) {
        super("El Estudiante" + estudianteNocodigo + "No existe en el sistema");
    }
}
