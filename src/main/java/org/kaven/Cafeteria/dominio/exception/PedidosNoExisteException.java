package org.kaven.Cafeteria.dominio.exception;

public class PedidosNoExisteException extends RuntimeException {
    public PedidosNoExisteException(String pedidosinexistente) {
        super("El pedido" + pedidosinexistente + "no se encuentra su pedido");
    }
}
