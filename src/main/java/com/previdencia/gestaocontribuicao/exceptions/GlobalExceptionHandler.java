package com.previdencia.gestaocontribuicao.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCPFException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCPFException(InvalidCPFException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("ERRO", ex.getMessage());
        response.put("STATUS", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}






