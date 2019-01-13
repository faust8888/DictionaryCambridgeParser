package com.faust8888.cambridge.cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;

@ComponentScan(value = {"com.faust8888.cambridge.cqrs"})
@Component
@EnableDiscoveryClient
@EnableResourceServer
@SpringBootApplication
public class CambridgeCqrsApp {

    public static void main(String[] args) {
        SpringApplication.run(CambridgeCqrsApp.class, args);
    }

}
