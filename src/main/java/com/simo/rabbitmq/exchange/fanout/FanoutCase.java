package com.simo.rabbitmq.exchange.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @description
 * @auther simo
 * @date 2024/6/25 18:02
 */

@Component
public class FanoutCase {

    static final String fanoutExchangeName = "hmall.fanout";
    static final String queueName1 = "fanout.queue1";
    static final String queueName2 = "fanout.queue2";

    @Bean
    Queue queue1() {
        return new Queue(queueName1, false);
    }

    @Bean
    Queue queue2() {
        return new Queue(queueName2, false);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchangeName);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue1(Queue queue1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingQueue2(Queue queue2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }

    @RabbitListener(queues = queueName1)
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者1接收到Fanout消息：【" + msg + "】");
    }

    @RabbitListener(queues = queueName2)
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者2接收到Fanout消息：【" + msg + "】");
    }
}
