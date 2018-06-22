package com.oms.commons.security.config;

import com.oms.commons.security.converter.WahHttpMessageConverter;
import com.oms.commons.security.interceptor.OptionsRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{

    private List<MediaType> SUPPORTED_MEDIA_TYPES = new ArrayList<MediaType>(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML));
    private Charset DEFAULT_CHARSET       = Charset.forName("UTF-8");

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getOptionsInterceptor())
                .pathMatcher(new AntPathMatcher())
                .addPathPatterns("/**")
                .order(-1);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(wahHttpMessageConverter());
        converters.add(stringHttpMessageConverter());
    }

    public HandlerInterceptorAdapter getOptionsInterceptor(){
        OptionsRequestInterceptor interceptor = new OptionsRequestInterceptor();

        return interceptor;
    }

    public GsonHttpMessageConverter wahHttpMessageConverter(){
        WahHttpMessageConverter converter = new WahHttpMessageConverter();
        //配置
        converter.setSupportedMediaTypes(SUPPORTED_MEDIA_TYPES);

        return converter;
    }

    public HttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter converter = new StringHttpMessageConverter(DEFAULT_CHARSET);

        return converter;
    }
}
