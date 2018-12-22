package com.faust8888.cambridge.events;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;

@ComponentScan(value = {"com.faust8888.cambridge.events"})
@Component
@EnableDiscoveryClient
@EnableResourceServer
@SpringBootApplication
public class CambridgeEventsApp {

    public static void main(String[] args) {
        SpringApplication.run(CambridgeEventsApp.class, args);
    }

}
