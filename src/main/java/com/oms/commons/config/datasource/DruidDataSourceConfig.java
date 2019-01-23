package com.oms.commons.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@ConfigurationProperties(prefix = "druid")
@Profile(value = {"development", "production"})
public class DruidDataSourceConfig{

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.defaultAutoCommit}")
    private Boolean defaultAutoCommit;

    @Setter
    private Integer maxActive;
    @Setter
    private Integer initialSize;
    @Setter
    private Integer maxWait;
    @Setter
    private Integer minIdle;
    @Setter
    private Integer timeBetweenEvictionRunsMillis;
    @Setter
    private Boolean testWhileIdle;
    @Setter
    private Boolean poolPreparedStatements;
    @Setter
    private Integer maxOpenPreparedStatements;
    @Setter
    private String  connectionInitSqls;

    @Bean
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();

        //配置
        datasource.setDriverClassName(driverClassName);
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);

        datasource.setDefaultAutoCommit(defaultAutoCommit);
        datasource.setMaxActive(maxActive);
        datasource.setInitialSize(initialSize);
        datasource.setMaxWait(maxWait);
        datasource.setMinIdle(minIdle);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        datasource.setConnectionInitSqls(Arrays.asList(connectionInitSqls));

        return datasource;
    }
}
