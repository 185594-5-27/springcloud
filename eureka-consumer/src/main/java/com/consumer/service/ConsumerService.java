package com.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL;
import static com.netflix.hystrix.HystrixCollapser.Scope.REQUEST;

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

    /**
     * 功能描述：实现在一定时间内的相同的响应通过批量来获取数据,scope=GLOBAL那么就响应正常，若为REQUEST则会报空指针异常
     * REQUEST范围只对一个request请求内的多次服务请求进行合并，GLOBAL是多单个应用中的所有线程的请求中的多次服务请求进行合并。
     * @param id
     * @return
     */
    @HystrixCollapser(batchMethod = "findAll" ,scope = GLOBAL, collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds",value = "2000")
    })
    public String find(String id){
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand()
    public List<String> findAll(List<String> ids){
       return restTemplate.getForObject("http://EUREKA-PRODUCER/findAll?ids={1}",List.class, StringUtils.join(ids,","));
    }

}
