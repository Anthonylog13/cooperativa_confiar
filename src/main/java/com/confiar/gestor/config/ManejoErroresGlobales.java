package com.confiar.gestor.config;

import com.confiar.gestor.dto.RespuestaDto;
import com.confiar.gestor.exception.ExcepcionPrincipal;
import com.confiar.gestor.exception.RecursoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejoErroresGlobales {

    @ExceptionHandler(ExcepcionPrincipal.class)
    public ResponseEntity<RespuestaDto> handleBusinessException(ExcepcionPrincipal ex) {

        HttpStatus status = ex instanceof RecursoNoEncontradoException ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new RespuestaDto(ex.getMessage(), ex.getCodigo()), status);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}