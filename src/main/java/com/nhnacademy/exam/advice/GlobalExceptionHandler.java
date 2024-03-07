package com.nhnacademy.exam.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.exam.domain.ErrorDTO;
import com.nhnacademy.exam.exception.UserAuthenticationFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler(UserAuthenticationFailedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorDTO> studentNotFoundExHandler() {
        ErrorDTO error = new ErrorDTO();

        error.setTitle("auth failed");
        error.setStatus(HttpStatus.FORBIDDEN);
        error.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(error);
    }
}
