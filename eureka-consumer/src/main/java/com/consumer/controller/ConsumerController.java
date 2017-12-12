package com.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
* 类描述：
* @auther linzf
* @create 2017/12/12 0012 
*/
@RestController
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/helloConsumer",method = RequestMethod.GET)
    @ResponseBody
    public String helloConsumer (){
        System.out.println("-----------------");
        return restTemplate.getForEntity("http://EUREKA-PRODUCER/hello",String.class).getBody();
    }


}
