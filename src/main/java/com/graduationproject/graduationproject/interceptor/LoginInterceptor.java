package com.graduationproject.graduationproject.interceptor;

import com.graduationproject.graduationproject.component.EncryptorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {           // 登录拦截

    @Autowired
    private EncryptorComponent encryptorComponent;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("LoginInterceptor");

        Optional.ofNullable(request.getHeader("token"))
                .ifPresentOrElse(token -> {
                    var map = encryptorComponent.decrypt(token);
                    request.setAttribute("authority", map.get("authority"));
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "未登录！");
                });

        return true;
    }
}
