package com.feign.service;

import com.feign.config.FullLogConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/12/20 0020.
 * 若存在一个配置类设置了@FeignClient(value="EUREKA-PRODUCER",configuration = DisableHystrixConfiguration.class),那么当前工程中所有的响应EUREKA-PRODUCER
 * 生产者的服务都将禁用断路器功能，即使你配置了像这样的@FeignClient(value="EUREKA-PRODUCER",fallback = HelloServiceFallback.class)的配置最终也无法实现断路器的功能，
 * 因此我们若只是不使用断路器的功能，那么我们就直接@FeignClient(value="EUREKA-PRODUCER")这样配置就不会有断路器的功能。
 */
@FeignClient(value="EUREKA-PRODUCER",fallback = HelloServiceFallback.class,configuration = FullLogConfiguration.class)
public interface HelloService {


    @RequestMapping("/hello")
    String hello();


}
