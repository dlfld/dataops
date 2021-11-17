package com.cuit.user_manage.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/11/11 9:59 上午
 * @Version 1.0
 */
@Configuration
public class HandlerInterceptorConfig implements WebMvcConfigurer {
    @Resource
    OpsInterceptor opsInterceptor;

    /**
     * 设置不拦截的路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(opsInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/v3/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/error/**")
                .excludePathPatterns("/sso/login")
                .excludePathPatterns("/sso/register")
        ;
    }
}
