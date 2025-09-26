package org.kaven.Cafeteria.dominio.exception;

public class EntregaNoExisteException extends RuntimeException {
    public EntregaNoExisteException(String entreganocodigo) {
        super("La entrega" + entreganocodigo + " no existe en el sistema.");
    }
}
