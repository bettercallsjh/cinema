package com.pmsj.cinema.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mhd
 * @className RabbitMqConfiguration
 * @description TODO
 * @create 2020/7/20
 * @since 1.0.0
 */
@Configuration
public class RabbitMqConfig {
    private static final String DELAY_QUEUE="cinema_delay_queue";
    private static final String DELAY_EXCHANGE="cinema_delay_exchange";
    private static final String DEAD_LETTER_QUEUE="cinema_dead_letter_queue";
    private static final String DEAD_LETTER_EXCHANGE="cinema_dead_letter_exchange";
    public static final String DELAY_ROUTING_KEY="cinema_delay_routing_key";
    public static final String DEAD_LETTER_ROUTING_KEY="cinema_dead_letter_routing_key";


    //定义一个dead letter Queue的对象，并给队列一个名字：springboot_dead_letter_queue
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue(){
        return new Queue(DEAD_LETTER_QUEUE);
    }

    //定义一个dead letter 交换机，并给交换机一个名字：springboot_dead_letter_exchange
    @Bean("deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }
    //定义一个delay 交换机，并给交换机一个名字：springboot_delay_exchange
    @Bean("delayExchange")
    public DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE);
    }


    //定义延迟队列
    @Bean("delayQueue")
    public Queue delayQueue(){
        Map<String,Object> arguments = new HashMap<>();
        //两分钟
        arguments.put("x-message-ttl",120000);
        arguments.put("x-dead-letter-exchange",DEAD_LETTER_EXCHANGE);
        arguments.put("x-dead-letter-routing-key",DEAD_LETTER_ROUTING_KEY);

        Queue queue = QueueBuilder.durable(DELAY_QUEUE).withArguments(arguments).build();

        return queue;
    }


    //绑定延迟队列和延迟交换机
    @Bean
    public Binding delayQueueBinding(@Qualifier("delayQueue") Queue queue,
                                     @Qualifier("delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_ROUTING_KEY);
    }

    //绑定死信队列和死信交换机
    @Bean
    public Binding deadLetterQueueBinding(@Qualifier("deadLetterQueue") Queue queue,
                                          @Qualifier("deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_ROUTING_KEY);
    }

}
