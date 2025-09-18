package org.kaven.Cafeteria.dominio.exception;

public class ProductoNoExisteException extends RuntimeException {
    public ProductoNoExisteException(String productonoexiste) {
        super("El producto" + productonoexiste + "no existe en el sistema");
    }
}
