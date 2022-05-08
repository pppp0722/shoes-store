package com.pppp0722.shoesstore.controller.advice;

import com.pppp0722.shoesstore.repository.exception.JdbcEmptyResultException;
import com.pppp0722.shoesstore.repository.exception.JdbcUpdateException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.pppp0722.shoesstore.controller.api.v1")
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(
        MethodArgumentNotValidException e) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getAllErrors()
            .forEach(c -> errors.add(c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(JdbcUpdateException.class)
    public ResponseEntity<String> handleJdbcUpdateException(JdbcUpdateException e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(JdbcEmptyResultException.class)
    public ResponseEntity<String> handleJdbcEmptyResultException(JdbcEmptyResultException e) {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        return ResponseEntity.internalServerError().body("DB 오류 발생!");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.internalServerError().body("알 수 없는 오류 발생!");
    }
}
