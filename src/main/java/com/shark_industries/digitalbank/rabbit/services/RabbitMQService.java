package com.shark_industries.digitalbank.rabbit.services;

import com.shark_industries.digitalbank.rabbit.exception.QueueNotExistException;
import com.shark_industries.digitalbank.rabbit.exception.RabbitMqException;
import com.shark_industries.digitalbank.rabbit.interfaces.BrockerHandler;
import com.shark_industries.digitalbank.rabbit.model.RabbitCredentional;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

@Service
public class RabbitMQService implements BrockerHandler {


    private final ObjectMapper mapper = new ObjectMapper();
    private Long timeout;

    private final CachingConnectionFactory connectionFactory;
    private final RabbitAdmin rabbitAdmin;
    private final RabbitTemplate rabbitTemplate;
    private final ConnectionHandler connectionHandler;

    private Runnable onSuccessCallback;
    private Runnable onFailureCallback;
    private final AmqpAdmin amqpAdmin;

    private boolean isConnected = false;

    private ScheduledFuture<?> recconectTask;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final List<SimpleMessageListenerContainer> containers = new ArrayList<>();


    public RabbitMQService(ConnectionHandler connectionHandler, CachingConnectionFactory connectionFactory, long timeout, AmqpAdmin amqpAdmin) {
        this.timeout = timeout;
        this.connectionFactory = connectionFactory;
        this.rabbitAdmin = new RabbitAdmin(connectionFactory);
        this.rabbitTemplate = new RabbitTemplate(connectionFactory);
        this.connectionHandler =  connectionHandler;
        this.amqpAdmin = amqpAdmin;

        this.connectionFactory.addConnectionListener(connectionHandler);
    }

    @Override
    public void connect(RabbitCredentional rabbitCredentional, Runnable onSuccess, Runnable onFailure) {
        if(this.isConnected) return;
        if(this.connectionFactory == null) throw new IllegalArgumentException();

        this.connectionFactory.setHost(rabbitCredentional.getHost());
        this.connectionFactory.setPort(rabbitCredentional.getPort());
        this.connectionFactory.setUsername(rabbitCredentional.getUsername());
        this.connectionFactory.setPassword(rabbitCredentional.getPassword());

//        this.connectionFactory.addConnectionListener(new ConnectionHandler() {
//            @Override
//            public void onCreate(Connection connection) {
//                isConnected = true;
//                if (onSuccess != null) onSuccess.run();
//            }
//        };


        this.onSuccessCallback = () ->{
            isConnected = true;
            if(onSuccess != null) onSuccess.run();
        };
        this.onFailureCallback = () ->{
          isConnected = false;
          if(onFailure != null) onFailure.run();
          throw new RabbitMqException("Failed to connect to RabbitMQ");
        };

    }

    @Override
    public <T> void sendMessage(String queueName, T payLoad) throws QueueNotExistException {

    }

    @Override
    public void createQueue(String queueName, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) {
        Queue queue = new Queue(queueName);
        rabbitAdmin.declareQueue(queue);
    }

    @Override
    public void createListener(String queueName, MessageListener messageListener, int consumerCount) throws QueueNotExistException {
        Queue queue = new Queue(queueName);

        // ?? Boolean.parseBoolean
        if (!Boolean.parseBoolean(rabbitAdmin.declareQueue(queue))) {
            throw new QueueNotExistException("Queue " + queueName + " does not exist");
        }
        AbstractMessageListenerContainer container = new SimpleMessageListenerContainer();

        container.setQueueNames(queueName);
        container.setMessageListener(messageListener);

        if (consumerCount > 0) {
            container.setConcurrentConsumers(consumerCount);
        }

        container.afterPropertiesSet();
        containers.add(container);
        container.start();
    }

    @Override
    public void stopListener(String queueName) {

    }

    public void stopAllListeners() {
        containers.forEach(SimpleMessageListenerContainer::stop);
        containers.clear();
    }

}
