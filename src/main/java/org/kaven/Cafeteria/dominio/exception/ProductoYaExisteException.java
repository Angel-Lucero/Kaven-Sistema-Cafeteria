package org.kaven.Cafeteria.dominio.exception;

public class ProductoYaExisteException extends RuntimeException {
    public ProductoYaExisteException(String productocodigoexiste) {
        super("El producto" + productocodigoexiste + "ya existe en el sistema");
    }
}
