package org.kaven.Cafeteria.dominio.exception;

public class UsuarioYaExisteException extends RuntimeException {
    public UsuarioYaExisteException(String usuarioCodigo) {
        super("El usuario: " + usuarioCodigo + " ya existe");
    }
}
