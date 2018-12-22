package com.faust8888.cambridge.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(value = {"com.faust8888.cambridge.config"})
@EnableConfigServer
@EnableResourceServer
@SpringBootApplication
public class CambridgeConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CambridgeConfigServerApplication.class, args);
    }

}
