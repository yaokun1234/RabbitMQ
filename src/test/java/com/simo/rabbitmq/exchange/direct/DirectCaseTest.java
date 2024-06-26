package com.simo.rabbitmq.exchange.direct;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DirectCaseTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = DirectCase.fanoutExchangeName;
        // 消息
        String message = "红色警报！日本乱排核废水，导致海洋生物变异，惊现哥斯拉！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            countDownLatch.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}