package org.kaven.Cafeteria.dominio.exception;

public class FacturaNoExisteException extends RuntimeException {
    public FacturaNoExisteException(String codigo) {
        super("La factura" +  codigo + " no existe en el sistema.");
    }
}
