package com.graduationproject.graduationproject.config;

import com.graduationproject.graduationproject.interceptor.CustomerInterceptor;
import com.graduationproject.graduationproject.interceptor.LoginInterceptor;
import com.graduationproject.graduationproject.interceptor.ManagerInterceptor;
import com.graduationproject.graduationproject.interceptor.SuperManagerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//配置拦截器
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private CustomerInterceptor customerInterceptor;

    @Autowired
    private SuperManagerInterceptor superManagerInterceptor;

    @Autowired
    private ManagerInterceptor managerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/register");

        registry.addInterceptor(customerInterceptor)
                .addPathPatterns("/api/customer/**");

        registry.addInterceptor(superManagerInterceptor)
                .addPathPatterns("/api/superManager/**");

        registry.addInterceptor(managerInterceptor)
                .addPathPatterns("/api/manager/**");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer2(){
        return new WebMvcConfigurer() {
            /**
             * 设置头 使可以跨域访问
             * @param registry
             * @since 4.2
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        .maxAge(3600)
                        .allowCredentials(true);
            }
        };
    }
}
