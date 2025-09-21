package org.kaven.Cafeteria.web.exception;

import org.kaven.Cafeteria.dominio.exception.*;
import org.kaven.Cafeteria.dominio.exception.Error;

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

    @ExceptionHandler(PedidoYaExisteException.class)
    public ResponseEntity<Error> handleException(PedidoYaExisteException ex) {
        Error error = new Error("pedido-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(PedidoNoExisteException.class)
    public ResponseEntity<Error> handleException(PedidoNoExisteException ex) {
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

    @ExceptionHandler(EmpleadoYaExisteException.class)
    public ResponseEntity<Error> handleException(EmpleadoYaExisteException ex) {
        Error error = new Error("empleado-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EmpleadoNoExisteException.class)
    public ResponseEntity<Error> handleException(EmpleadoNoExisteException ex) {
        Error error = new Error("empleado-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CarreraNoExisteException.class)
    public ResponseEntity<Error> handleException(CarreraNoExisteException ex) {
        Error error = new Error("carrera-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(CarreraYaExisteException.class)
    public ResponseEntity<Error> handleException(CarreraYaExisteException ex) {
        Error error = new Error("carrera-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EntregaNoExisteException.class)
    public ResponseEntity<Error> handleException(EntregaNoExisteException ex) {
        Error error = new Error("entrega-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(EntregaYaExisteException.class)
    public ResponseEntity<Error> handleException(EntregaYaExisteException ex) {
        Error error = new Error("entrega-ya-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(FacturaNoExisteException.class)
    public ResponseEntity<Error> handleException(FacturaNoExisteException ex) {
        Error error = new Error("factura-no-existe", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(FacturaYaExisteException.class)
    public ResponseEntity<Error> handleException(FacturaYaExisteException ex) {
        Error error = new Error("factura-ya-existe", ex.getMessage());
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