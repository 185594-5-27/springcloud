package com.zuul.config;

/*
* 类描述：
* @auther linzf
* @create 2017/12/29 0029 
*/

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 类描述：错误捕获异常处理类，当出现报错的时候将错误数据用json的形式返回给页面。
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> handleGlobalException(HttpServletRequest req, Exception e){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("result", false);
        returnMap.put("msg",e.getMessage());
        return returnMap;
    }

}
