package com.faust8888.cambridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;

@ComponentScan(value = {"com.faust8888.cambridge"})
@Component
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableResourceServer
@SpringBootApplication
public class CambridgeApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CambridgeApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CambridgeApp.class);
    }

}
