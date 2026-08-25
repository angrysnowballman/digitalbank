package com.shark_industries.digitalbank.transferService.model;

public interface MessagePublisher {
    void publish(final String message);
}