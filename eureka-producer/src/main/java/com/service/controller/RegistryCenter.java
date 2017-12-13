package com.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/*
* 类描述：
* @auther linzf
* @create 2017/12/12 0012 
*/
@RestController
public class RegistryCenter {


    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public String index() {
        List<String> instance = client.getServices();
        instance.forEach(t->{
            System.out.println(t+"---"+client.getInstances(t));
        });
        int sleepTime = new Random().nextInt(3000);
        System.out.println("sleepTime:" + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World";
    }

    }
