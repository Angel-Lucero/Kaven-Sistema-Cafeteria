package org.kaven.Cafeteria.dominio.exception;

public class ProductoYaExisteException extends RuntimeException {
    public ProductoYaExisteException(String nombreProducto) {
        super("El producto con nombre '" + nombreProducto + "' ya existe en el sistema");
    }
}