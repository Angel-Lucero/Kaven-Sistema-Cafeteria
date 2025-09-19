package org.kaven.Cafeteria.dominio.exception;

public class ProductoNoExisteException extends RuntimeException {
    public ProductoNoExisteException(Long codigo) {
        super("El producto con código " + codigo + " no existe en el sistema");
    }
}