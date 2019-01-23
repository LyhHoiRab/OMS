package com.oms.commons.security.listener;

import com.oms.commons.utils.ApplicationContextHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContextListener implements ApplicationContextAware{

    public void setApplicationContext(ApplicationContext context) throws BeansException{
        ApplicationContextHolder.setContext(context);
    }
}
