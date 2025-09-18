package org.kaven.Cafeteria.dominio.exception;

public class PedidosYaExisteException extends RuntimeException {
    public PedidosYaExisteException(String pedidosexistente) {
        super("El pedido" + pedidosexistente + "ya existe en el sistema");
    }
}
