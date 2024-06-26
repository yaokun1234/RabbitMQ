package com.simo.rabbitmq.exchange.topic;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class TopicCaseTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * topicExchange
     */
    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "hmall.topic";
        // 消息
        String message = "喜报！孙悟空大战哥斯拉，胜!";
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name", "姚坤");
        // 发送消息
//        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
        rabbitTemplate.convertAndSend(exchangeName, "japan.news1", objectObjectHashMap);
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            countDownLatch.await(100000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}