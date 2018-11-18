package com.faust8888.cambridge.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CambridgeConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CambridgeConfigServerApplication.class, args);
    }

}
