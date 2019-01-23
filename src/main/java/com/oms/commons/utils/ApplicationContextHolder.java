package com.oms.commons.utils;

import org.springframework.context.ApplicationContext;

public class ApplicationContextHolder{

    private static ApplicationContext context;

    public static synchronized void setContext(ApplicationContext applicationContext){
        synchronized(ApplicationContext.class){
            if(context == null){
                context = applicationContext;
            }
        }
    }

    public static ApplicationContext getContext(){
        return context;
    }
}
