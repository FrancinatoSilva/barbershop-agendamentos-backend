package br.com.natodev.gerenciadordeagendamentosbarbershop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handRegrasDeNegocio(IllegalArgumentException exception) {
        Map<String, String> resposta = new HashMap<>();
        resposta.put("erro", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
