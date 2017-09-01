package com.lexue.base.invokelink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zhao.weiwei on 2017/6/10.
 */
@Component
public class WebInterceptorConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private LinkHandlerInterceptor linkHandlerInterceptor;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(linkHandlerInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
