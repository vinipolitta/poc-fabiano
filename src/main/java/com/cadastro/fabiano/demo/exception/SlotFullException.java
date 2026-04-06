package com.cadastro.fabiano.demo.exception;

/**
 * Lançada quando o slot de horário já atingiu a capacidade máxima.
 */
public class SlotFullException extends RuntimeException {
    public SlotFullException(String message) {
        super(message);
    }
}
