package com.shark_industries.digitalbank.rabbit.exception;

public class RabbitMqException extends RuntimeException {
    public RabbitMqException(String message) {
        super(message);
    }

    public RabbitMqException(String message, Throwable cause) {
        super(message, cause);
    }
}
