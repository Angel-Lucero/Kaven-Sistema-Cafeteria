package org.kaven.Cafeteria.dominio.exception;

public class UsuarioNoExisteException extends RuntimeException {
    public UsuarioNoExisteException(String usuarioNocodigo) {
        super("El usuario" + usuarioNocodigo + "no existe en el sistema");
    }
}
