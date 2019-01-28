package com.oms;

import com.oms.commons.consts.CacheName;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableRedisHttpSession(redisNamespace = CacheName.ACCOUNT_SESSION, maxInactiveIntervalInSeconds = CacheName.COOKIE_TIME_OUT)
@MapperScan(basePackages = "com.oms.core.**.dao.mapper")
public class OMSApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(OMSApplication.class);
    }

    public static void main(String[] args){
        SpringApplication.run(OMSApplication.class, args);
    }
}
