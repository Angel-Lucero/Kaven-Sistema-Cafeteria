package org.kaven.Cafeteria.web.exception;

import org.kaven.Cafeteria.dominio.exception.*;
import org.kaven.kinal_play.dominio.exception.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UsuarioYaExisteException.class)
    public ResponseEntity<Error> handleException(UsuarioYaExisteException ex) {
        Error error = new Error("usuario-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(UsuarioNoExisteException.class)
    public ResponseEntity<Error> handleException(UsuarioNoExisteException ex) {
        Error error = new Error("usuario-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ProductoNoExisteException.class)
    public ResponseEntity<Error> handleException(ProductoNoExisteException ex) {
        Error error = new Error("producto-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(PedidosYaExisteException.class)
    public ResponseEntity<Error> handleException(PedidosYaExisteException ex) {
        Error error = new Error("pedido-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(PedidosNoExisteException.class)
    public ResponseEntity<Error> handleException(PedidosNoExisteException ex) {
        Error error = new Error("pedido-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EstudianteYaExisteException.class)
    public ResponseEntity<Error> handleException(EstudianteYaExisteException ex) {
        Error error = new Error("estudiante-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EstudianteNoExisteException.class)
    public ResponseEntity<Error> handleException(EstudianteNoExisteException ex) {
        Error error = new Error("estudiante-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EmpleadosYaExisteException.class)
    public ResponseEntity<Error> handleException(EmpleadosYaExisteException ex) {
        Error error = new Error("empleado-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EmpleadosNoExisteException.class)
    public ResponseEntity<Error> handleException(EmpleadosNoExisteException ex) {
        Error error = new Error("empleado-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errores.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        Error error = new Error("error-desconocido", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}