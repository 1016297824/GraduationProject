package com.graduationproject.graduationproject.config;

import com.graduationproject.graduationproject.interceptor.*;
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
    private FarmManagerInterceptor farmManagerInterceptor;

    @Autowired
    private RestaurantManagerInterceptor restaurantManagerInterceptor;

    @Autowired
    private RestaurantStaffInterceptor restaurantStaffInterceptor;

    @Autowired
    private FarmStaffInterceptor farmStaffInterceptor;

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

        registry.addInterceptor(farmManagerInterceptor)
                .addPathPatterns("/api/farmManager/**");

        registry.addInterceptor(restaurantManagerInterceptor)
                .addPathPatterns("/api/restaurantManager/**");

        registry.addInterceptor(restaurantStaffInterceptor)
                .addPathPatterns("/api/restaurantStaff/**");

        registry.addInterceptor(farmStaffInterceptor)
                .addPathPatterns("/api/farmStaff/**");
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer2() {
        return new WebMvcConfigurer() {
            /**
             * 设置头 使可以跨域访问
             * @param registry
             * @since 4.2
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // 对所有路径应用CORS配置
                        .allowedOrigins("*")    // 允许的源列表
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")  // 允许的方法列表
                        .maxAge(3600)   // 预检请求的缓存时间（秒）
                        .allowCredentials(true);    // 是否允许发送Cookie等凭证信息
            }
        };
    }
}
