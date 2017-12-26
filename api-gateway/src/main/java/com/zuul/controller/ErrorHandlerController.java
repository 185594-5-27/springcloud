package com.zuul.controller;

import com.netflix.zuul.context.RequestContext;
import net.sf.json.JSONObject;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/*
* 类描述：
* @auther linzf
* @create 2017/12/26 0026 
*/
@RestController
public class ErrorHandlerController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
        JSONObject jobj = new JSONObject();
        jobj.put("errorCode","-403");
        jobj.put("msg","服务器请求超时");
        System.out.println(throwable.getMessage());
        System.out.println(ctx.get("error.exception"));
        return jobj.toString();
    }

}
