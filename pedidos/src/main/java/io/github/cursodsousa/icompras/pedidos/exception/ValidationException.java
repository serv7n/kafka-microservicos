package io.github.cursodsousa.icompras.pedidos.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private final String message;
    private final String field;
    public ValidationException(String message, String field) {
        super(message);
        this.message = message;
        this.field = field;

    }
}
