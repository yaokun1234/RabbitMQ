package com.simo.rabbitmq.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 * @auther simo
 * @date 2024/6/26 15:50
 */

@Configuration
@RequiredArgsConstructor
@Slf4j
public class MQConfig {

    private final RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnsCallback(returned -> {
            log.error("触发return callback,");
            log.info("exchange: {}", returned.getExchange());
            log.info("routingKey: {}", returned.getRoutingKey());
            log.info("message: {}", returned.getMessage());
            log.info("replyCode: {}", returned.getReplyCode());
            log.info("replyText: {}", returned.getReplyText());
        });

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("Message successfully delivered to the broker.");
            } else {
                log.error("Message delivery to broker failed: " + cause);
            }
        });
    }

}
