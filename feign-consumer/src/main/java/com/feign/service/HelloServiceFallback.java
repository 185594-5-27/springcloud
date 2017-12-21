package com.feign.service;

import com.feign.service.HelloService;
import org.springframework.stereotype.Component;

/*
* 类描述：
* @auther linzf
* @create 2017/12/20 0020 
*/
@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }
}
