package org.kaven.Cafeteria.dominio.exception;

public class PedidoYaExisteException extends RuntimeException {
    public PedidoYaExisteException(String informacion) {
        super("El pedido con información " + informacion + " ya existe en el sistema");
    }
}