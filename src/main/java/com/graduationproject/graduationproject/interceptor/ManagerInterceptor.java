package com.graduationproject.graduationproject.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
// 管理员权限拦截
public class ManagerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("ManagerInterceptor");

        String authority = (String) request.getAttribute("authority");
        System.out.println(authority);
        if (!authority.equals("manager")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
        }

        return true;
    }
}
