package com.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*
* 类描述：
* @auther linzf
* @create 2017/12/13 0013 
*/
@Service
public class ConsumerService {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "helloConsumerFallback")
    public String helloConsumer(){
        return restTemplate.getForEntity("http://EUREKA-PRODUCER/hello",String.class).getBody();
    }

    public String helloConsumerFallback(){
        return "error";
    }

}
