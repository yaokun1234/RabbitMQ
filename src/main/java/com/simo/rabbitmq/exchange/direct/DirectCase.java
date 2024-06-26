package com.simo.rabbitmq.exchange.direct;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @description
 * @auther simo
 * @date 2024/6/26 09:59
 */

@Component
public class DirectCase {

    static final String fanoutExchangeName = "hmall.direct";
    static final String queueName1 = "direct.queue1";
    static final String queueName2 = "direct.queue2";

    @Bean
    Queue directQueue1() {
        return new Queue(queueName1, false);
    }

    @Bean
    Queue directQueue2() {
        return new Queue(queueName2, false);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(fanoutExchangeName);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding directBindingQueue1(Queue directQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("red");
    }

    @Bean
    public Binding directBindingQueue2(Queue directQueue1, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue1).to(directExchange).with("blue");
    }

    @Bean
    public Binding directBindingQueue3(Queue directQueue2, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue2).to(directExchange).with("red");
    }

    @Bean
    public Binding directBindingQueue4(Queue directQueue2, DirectExchange directExchange){
        return BindingBuilder.bind(directQueue2).to(directExchange).with("yellow");
    }


    @RabbitListener(queues = queueName1)
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者1接收到DirectExchange消息：【" + msg + "】");
    }

    @RabbitListener(queues = queueName2)
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者2接收到DirectExchange消息：【" + msg + "】");
    }
}
