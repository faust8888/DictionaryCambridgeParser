package com.faust8888.cambridge.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CambridgeEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CambridgeEurekaServerApplication.class, args);
    }

}
