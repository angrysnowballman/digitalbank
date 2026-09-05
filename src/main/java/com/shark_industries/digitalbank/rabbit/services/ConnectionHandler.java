package com.shark_industries.digitalbank.rabbit.services;

import com.rabbitmq.client.ShutdownSignalException;
import org.jspecify.annotations.Nullable;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

public class ConnectionHandler implements ConnectionListener {
    private Runnable success = () -> {};
    private Runnable failure = () -> {};

    public void addHandlers(Runnable success, Runnable failure) {
        this.success = success;
        this.failure = failure;
    }

    @Override
    public void onCreate(@Nullable Connection connection) {
        success.run();
    }

    @Override
    public void onClose(Connection connection) {
        failure.run();
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
       failure.run();
    }

    @Override
    public void onFailed(Exception exception) {
        failure.run();
    }
}
