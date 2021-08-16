package com.monetware.pdexperiment.system.interceptor;

import com.monetware.pdexperiment.system.config.Config;
import com.monetware.pdexperiment.system.interceptor.customInterceptors.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 基础拦截器
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    Config config;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pd-upload/**").addResourceLocations(config.getUploadFile());
    }
}
