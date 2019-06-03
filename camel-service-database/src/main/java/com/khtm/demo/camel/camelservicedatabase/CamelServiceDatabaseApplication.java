package com.khtm.demo.camel.camelservicedatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class CamelServiceDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CamelServiceDatabaseApplication.class, args);
    }

}
