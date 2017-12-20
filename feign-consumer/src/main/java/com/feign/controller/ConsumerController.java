package com.feign.controller;

import com.feign.entity.User;
import com.feign.service.HelloService;
import com.feign.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
* 类描述：
* @auther linzf
* @create 2017/12/20 0020 
*/
@RestController
public class ConsumerController {

    @Autowired
    HelloService helloService;
    @Autowired
    OtherService otherService;

    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer () {
        return helloService.hello();
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String findAll () {
        return otherService.findAll("123");
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser () {
        User user = new User();
        user.setName("林泽锋");
        user.setAge(12);
        return otherService.addUser(user);
    }


}
