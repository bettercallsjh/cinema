package com.pmsj.cinema.business.util;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JSRabbitMqConfiguration {

    private static final String DELAY_QUEUE="js_cinema_delay_queue";
    private static final String DELAY_EXCHANGE="js_cinema_delay_exchange";
    public static final String DELAY_ROUTING_KEY="js_cinema_delay_routing_key";
    private static final String DEAD_LETTER_QUEUE="js_cinema_dead_letter_queue";
    private static final String DEAD_LETTER_EXCHANGE="js_cinema_dead_letter_exchange";
    public static final String DEAD_LETTER_ROUTING_KEY="js_cinema_dead_letter_routing_key";


    //定义一个dead letter Queue的对象，并给队列一个名字：springboot_dead_letter_queue
    @Bean("js_deadLetterQueue")
    public Queue deadLetterQueue(){
        return new Queue(DEAD_LETTER_QUEUE);
    }

    //定义一个dead letter 交换机，并给交换机一个名字：springboot_dead_letter_exchange
    @Bean("js_deadLetterExchange")
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    //定义一个delay 交换机，并给交换机一个名字：springboot_delay_exchange
    @Bean("js_delayExchange")
    public DirectExchange delayExchange(){
        return new DirectExchange(DELAY_EXCHANGE);
    }

    //定义延迟队列
    @Bean("js_delayQueue")
    public Queue js_delayQueue(){
        Map<String,Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl",6000);
        arguments.put("x-dead-letter-exchange",DEAD_LETTER_EXCHANGE);
        arguments.put("x-dead-letter-routing-key",DEAD_LETTER_ROUTING_KEY);
        Queue queue = QueueBuilder.durable(DELAY_QUEUE).withArguments(arguments).build();
        return queue;
    }

    //绑定延迟队列和延迟交换机
    @Bean
    public Binding js_delayQueueBinding(@Qualifier("js_delayQueue") Queue queue,
                                     @Qualifier("js_delayExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_ROUTING_KEY);
    }

    //绑定死信队列和死信交换机
    @Bean
    public Binding js_deadLetterQueueBinding(@Qualifier("js_deadLetterQueue") Queue queue,
                                          @Qualifier("js_deadLetterExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_ROUTING_KEY);
    }
}
