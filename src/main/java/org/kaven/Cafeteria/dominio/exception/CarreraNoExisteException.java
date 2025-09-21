package org.kaven.Cafeteria.dominio.exception;

public class CarreraNoExisteException extends RuntimeException {
    public CarreraNoExisteException(String carreracodigo) {
        super("La carrera" + carreracodigo + " no existe en el sistema.");
    }
}
