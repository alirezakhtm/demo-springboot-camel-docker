package com.khtm.demo.camel.camelservicedatabase.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSourceMysql(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder
                .username(environment.getProperty("mysql.username"))
                .password(environment.getProperty("mysql.password"))
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url(environment.getProperty("mysql.url"))
                .build();
    }

}
