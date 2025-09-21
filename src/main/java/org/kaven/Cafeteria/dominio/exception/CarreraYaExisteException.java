package org.kaven.Cafeteria.dominio.exception;

public class CarreraYaExisteException extends RuntimeException {
    public CarreraYaExisteException(String carreraexiste) {
        super("La carrera" + carreraexiste + " existe en el sistema.");
    }
}
