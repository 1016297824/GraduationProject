package com.graduationproject.graduationproject.config;

import com.graduationproject.graduationproject.interceptor.CustomerInterceptor;
import com.graduationproject.graduationproject.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {            //配置拦截器

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private CustomerInterceptor customerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/register");

        registry.addInterceptor(customerInterceptor)
                .addPathPatterns("/api/customer/**");
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
