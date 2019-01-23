package com.oms.commons.config.datasource;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;

@Configuration
@ConfigurationProperties(prefix = "jdbc")
@Profile(value = "test")
@Setter
public class SimpleDriverDataSourceConfig{

    private Class<? extends Driver> driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource datasource = new SimpleDriverDataSource();

        //配置
        datasource.setDriverClass(driverClassName);
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);

        return datasource;
    }
}
