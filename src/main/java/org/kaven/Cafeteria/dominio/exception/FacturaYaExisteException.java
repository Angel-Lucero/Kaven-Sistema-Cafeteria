package org.kaven.Cafeteria.dominio.exception;

public class FacturaYaExisteException extends RuntimeException {
    public FacturaYaExisteException(String codigoya) {
        super("La factura" + codigoya + " si existe en el sistema.");
    }
}
