package com.logistics.routing.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ROUTING_QUEUE = "routing.queue";

    @Bean
    public Queue routingQueue() {
        return new Queue(ROUTING_QUEUE, false);
    }
}
