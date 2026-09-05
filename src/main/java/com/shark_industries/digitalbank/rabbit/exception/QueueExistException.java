package com.shark_industries.digitalbank.rabbit.exception;

public class QueueExistException extends RuntimeException {
    public QueueExistException(String message) {
        super(message);
    }
}
