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

/*
* 类描述：
* @auther linzf
* @create 2017/12/13 0013 
*/
@Service
public class ConsumerService {

    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "helloConsumerFallback",
            commandProperties = {
               // 设置响应的超时时间，假若我们的producer端的执行时间超过2000毫秒那么就自动服务降级，进入helloConsumerFallback方法
               @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),
               // 设置我们上面的超时时间是否有效，默认是true超时时间是有效的，若设置为false则设置的execution.isolation.thread.timeoutInMilliseconds值无效
               @HystrixProperty(name="execution.timeout.enabled", value="true"),
               // 该属性用来配置当 HystrixCommand.run()执行超时的时候是否要将它中断（测试无效）
               @HystrixProperty(name="execution.isolation.thread.interruptOnTimeout",value="false"),
               // 当HystrixCommand 的隔离策略使用信号量的时候， 该属性用来配置信号量的大小（并发请求数）。 当最大并发请求数达到该设置值时， 后续的请求将会被拒绝。
               // @HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests", value="2")
               // 该属性用来配置当 HystrixCommand.run()执行被取消的时候是否要将它中断（报错【java.lang.IllegalArgumentException: unknown command property: execution.isolation.thread.interruptOnCancel】）
               //@HystrixProperty(name="execution.isolation.thread.interruptOnCancel", value= "false")
               // 该属性用来 设置从调用线程中允许HystrixComrnand.get Fallback()方法执行的最大并发 请求数。 当达到最大并发请求数时， 后续的请求将会被拒绝并抛出异常（因为它已经没有后续的fallback可以被调用了）。
               @HystrixProperty(name="fallback.isolation.semaphore.maxConcurrentRequests",value="20"),
               // 该属性用来设置在滚动时间窗中，断路器熔断的最小请求数。 例如，默认该值为 20 的时候，如果滚动时间窗（默认10秒）内仅收到了19个请求， 即使这19个请求都失败了，断路器也不会打开
               @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="30"),
               // 该属性用来设置当断路器打开之后的休眠时间窗。 休眠时间窗结束之后，会将断路器置为 “ 半开” 状态， 尝试熔断的请求命令，如果依然失败就将断路器继续设置为 “打开” 状态，如果成功就设置为 “ 关闭 ” 状态。
               @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="3000")
           }
           ,threadPoolProperties = {
               // 该参数用来设置执行命令线程池的核心线程数，该值也就是命令执行的最大并发量。
               @HystrixProperty(name="coreSize", value = "20"),
               // 该参数用来设置线程池的最大队列大小。当设置为-1时，线程池将使用SynchronousQueue实现的队列，否则将使用LinkedBlockingQueue实现的队列。
               @HystrixProperty(name="maxQueueSize",value="10"),
               // 该参数用来为队列设置拒绝闾值。 通过该参数， 即使队列没有达到最大值也能拒绝请求。 该参数主要是对归nkedBlockingQueue队列的补充， 因为 LinkedBlockingQueue 队列不能动态修改它的对象大小， 而通过该属性就可以调整拒绝请求的队列大小了。
               @HystrixProperty(name="queueSizeRejectionThreshold", value="10"),
               // 该参数用来设置滚动时间窗的长度， 单位为毫秒。 该滚动时间窗的长度用于线程池的指标度量， 它会被分成多个“ 桶 ” 来统计指标。
               @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="10"),
               // 该参数用来设置滚动时间窗被划分成“桶” 的数量。
               @HystrixProperty(name="metrics.rollingStats.numBuckets", value="10")
           })
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
    @HystrixCollapser(batchMethod = "findAll" ,scope = GLOBAL,
            collapserProperties = {
               @HystrixProperty(name = "timerDelayInMilliseconds",value = "2000")
            }
    )
    public String find(String id){
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand()
    public List<String> findAll(List<String> ids){
       return restTemplate.getForObject("http://EUREKA-PRODUCER/findAll?ids={1}",List.class, StringUtils.join(ids,","));
    }

}
