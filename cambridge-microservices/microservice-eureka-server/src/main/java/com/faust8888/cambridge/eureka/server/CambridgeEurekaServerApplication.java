package com.faust8888.cambridge.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@ComponentScan(value = "com.faust8888.cambridge.eureka")
@EnableEurekaServer
@EnableResourceServer
@SpringBootApplication
public class CambridgeEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CambridgeEurekaServerApplication.class, args);
    }

}
