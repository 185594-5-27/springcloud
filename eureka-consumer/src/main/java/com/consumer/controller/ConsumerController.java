package com.consumer.controller;

import com.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
* 类描述：
* @auther linzf
* @create 2017/12/12 0012 
*/
@RestController
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @RequestMapping(value = "/helloConsumer",method = RequestMethod.GET)
    @ResponseBody
    public String helloConsumer (){
        System.out.println("-----------------");
        return consumerService.helloConsumer();
    }


}
