package com.pucpr.biblioteca.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(500).body(":.ERRO DO SISTEMA.:\n\n" +  e.getMessage() + "\n\nLigue para 9393 e fale com o suporte técnico.");
    }
}
