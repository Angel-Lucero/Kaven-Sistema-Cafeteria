package org.kaven.Cafeteria.dominio.exception;

public class EntregaYaExisteException extends RuntimeException {
    public EntregaYaExisteException(String entrega) {
        super("La entrega" + entrega + " ya existe en el sistema.");
    }
}
