package com.feign.service;

import com.feign.fallback.HelloServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/12/20 0020.
 */
@FeignClient(name="EUREKA-PRODUCER",fallback = HelloServiceFallback.class)
public interface HelloService {


    @RequestMapping("/hello")
    String hello();


}
