package com.simo.rabbitmq.workqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @description
 * @auther simo
 * @date 2024/6/25 17:50
 */

@Component
public class WorkQueueCase {


    static final String queueName = "work.queue";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @RabbitListener(queues = queueName)
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = queueName)
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println("消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }
}
