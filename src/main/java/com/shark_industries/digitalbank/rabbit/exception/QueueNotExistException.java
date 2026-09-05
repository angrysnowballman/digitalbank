package com.shark_industries.digitalbank.rabbit.exception;

public class QueueNotExistException extends RuntimeException {
    public QueueNotExistException(String message) {
        super(message);
    }
}
