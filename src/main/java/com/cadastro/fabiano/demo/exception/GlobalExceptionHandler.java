package com.cadastro.fabiano.demo.exception;

import com.cadastro.fabiano.demo.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Usuário já cadastrado — exibe mensagem amigável sem stack trace */
    @ExceptionHandler(DuplicateBookingException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateBookingException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)          // 409
                .body(new ErrorResponse(ex.getMessage()));
    }

    /** Horário lotado */
    @ExceptionHandler(SlotFullException.class)
    public ResponseEntity<ErrorResponse> handleSlotFull(SlotFullException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)          // 409
                .body(new ErrorResponse(ex.getMessage()));
    }

    /** Erros de negócio genéricos (validação, not found, etc.) */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)       // 400
                .body(new ErrorResponse(ex.getMessage()));
    }
}
