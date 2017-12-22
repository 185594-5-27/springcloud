package com.feign.service;

import com.feign.config.FullLogConfiguration;
import com.feign.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * Created by Administrator on 2017/12/20 0020.
 * 通过增加configuration来实现关闭断路器的功能
 */
@FeignClient(value="EUREKA-PRODUCER",configuration = FullLogConfiguration.class)
public interface OtherService {

    @RequestMapping("/findAll")
    String findAll(@RequestParam("ids") String ids);

    /**
     * 若使用对象传输数据必须要使用POST方法且要在两边都加上@RequestBody的注解
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    String addUser(@RequestBody User user);

}
