package com.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 类描述：
* @auther linzf
* @create 2018/1/4 0004 
*/
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue(){
            return new Queue("hello");
     }

}
