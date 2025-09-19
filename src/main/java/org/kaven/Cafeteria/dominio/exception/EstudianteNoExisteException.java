package org.kaven.Cafeteria.dominio.exception;

public class EstudianteNoExisteException extends RuntimeException {
    public EstudianteNoExisteException(Long codigo) {
        super("El estudiante con código " + codigo + " no existe en el sistema");
    }
}