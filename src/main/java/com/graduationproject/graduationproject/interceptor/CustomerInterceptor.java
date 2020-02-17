package com.graduationproject.graduationproject.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomerInterceptor implements HandlerInterceptor {            //权限拦截

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("CustomerInterceptor");

        String authority = (String) request.getAttribute("authority");
        if (!authority.equals("customer")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }

        return true;
    }
}
