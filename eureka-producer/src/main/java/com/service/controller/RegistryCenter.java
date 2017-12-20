package com.service.controller;

import com.service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    /**
     * 若使用对象传输数据必须要使用POST方法且要在两边都加上@RequestBody的注解
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser", method= RequestMethod.POST)
    public User addUser(@RequestBody User user){
        System.out.println("----接收到的数据-----"+user.getName());
        user.setName(user.getName()+"测试数据");
        return user;
    }

    @RequestMapping(value = "/findAll", method= RequestMethod.GET)
    public List<String> findAll(String ids){
        System.out.println("----接收到的数据-----"+ids);
        List<String> stringList = new ArrayList<String>();
        String [] id_array = ids.split(",");
        for(int i=0;i<id_array.length;i++){
            stringList.add(id_array[i]+"测试数据");
        }
        return stringList;
    }

    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public String index() {
        List<String> instance = client.getServices();
        instance.forEach(t->{
            System.out.println(t+"---"+client.getInstances(t));
        });
        int sleepTime = new Random().nextInt(5000);
        System.out.println("sleepTime:" + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World";
    }

    }
