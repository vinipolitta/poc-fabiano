package com.cadastro.fabiano.demo.exception;

/**
 * Lançada quando uma pessoa já possui agendamento ativo no evento
 * e o template está configurado com campos de deduplicação.
 */
public class DuplicateBookingException extends RuntimeException {
    public DuplicateBookingException(String message) {
        super(message);
    }
}
