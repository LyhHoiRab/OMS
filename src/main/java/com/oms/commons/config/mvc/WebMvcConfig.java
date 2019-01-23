package com.oms.commons.config.mvc;

import com.google.common.collect.Lists;
import com.oms.commons.config.converter.JsonHttpMessageConverter;
import com.oms.commons.config.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{

//    @Autowired
//    private PermissionInterceptor permissionInterceptor;

    /**
     * 消息转换器设置
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        //自定义converter
        JsonHttpMessageConverter jsonConverter = new JsonHttpMessageConverter();
        jsonConverter.setSupportedMediaTypes(Lists.newArrayList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML));

        //String converter
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

        converters.add(jsonConverter);
        converters.add(stringConverter);
    }

    /**
     * 跨域设置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("POST", "GET", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(-1);
    }

    /**
     * 拦截器设置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //权限验证
//        registry.addInterceptor(permissionInterceptor)
//                .addPathPatterns("/api/**")
//                .order(0);
    }
}
