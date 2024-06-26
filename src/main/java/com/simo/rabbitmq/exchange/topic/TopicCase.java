package com.simo.rabbitmq.exchange.topic;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @description
 * @auther simo
 * @date 2024/6/26 15:10
 */
@Component
public class TopicCase {

    static final String fanoutExchangeName = "hmall.topic";
    static final String queueName1 = "topic.queue1";
    static final String queueName2 = "topic.queue2";

    @Bean
    Queue topicQueue1() {
        return new Queue(queueName1, false);
    }

    @Bean
    Queue topicQueue2() {
        return new Queue(queueName2, false);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(fanoutExchangeName);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding topicBindingQueue1(Queue topicQueue1, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("china.#");
    }

    @Bean
    public Binding topicBindingQueue2(Queue topicQueue2, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("#.news");
    }



    @RabbitListener(queues = queueName1)
    public void listenFanoutQueue1(HashMap msg) {
        System.out.println("消费者1接收到TopicExchange消息：【" + msg + "】");
    }

    @RabbitListener(queues = queueName2)
    public void listenFanoutQueue2(HashMap msg) {
        System.out.println("消费者2接收到TopicExchange消息：【" + msg + "】");
    }


}
