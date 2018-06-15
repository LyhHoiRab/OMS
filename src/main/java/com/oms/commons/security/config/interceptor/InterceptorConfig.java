package com.oms.commons.security.config.interceptor;

import com.oms.commons.security.interceptor.OptionsRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getOptionsInterceptor())
                .pathMatcher(new AntPathMatcher())
                .addPathPatterns("/**")
                .order(-1);
    }

    public HandlerInterceptorAdapter getOptionsInterceptor(){
        OptionsRequestInterceptor interceptor = new OptionsRequestInterceptor();

        return interceptor;
    }
}
