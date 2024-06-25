package com.simo.rabbitmq.exchange.fanout;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FanoutCaseTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testFanoutExchange() {
        // 交换机名称
        String exchangeName = "hmall.fanout";
        // 消息
        String message = "hello, everyone! fanout";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
        try {
            CountDownLatch countDownLatch = new CountDownLatch(2);
            countDownLatch.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}