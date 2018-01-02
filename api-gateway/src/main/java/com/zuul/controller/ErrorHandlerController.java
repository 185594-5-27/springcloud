package com.zuul.controller;

import com.netflix.zuul.context.RequestContext;
import com.zuul.entity.ErrorException;
import com.zuul.util.CombineException;
import net.sf.json.JSONObject;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return CombineException.getErrorException(ctx.getThrowable()).toString();
    }



}
