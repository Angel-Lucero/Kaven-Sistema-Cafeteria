package org.kaven.Cafeteria.dominio.exception;

public class PedidoNoExisteException extends RuntimeException {
    public PedidoNoExisteException(Long codigo) {
        super("El pedido con código " + codigo + " no existe en el sistema");
    }
}