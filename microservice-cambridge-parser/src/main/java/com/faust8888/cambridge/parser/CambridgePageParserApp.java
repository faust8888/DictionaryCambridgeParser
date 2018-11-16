package com.faust8888.cambridge.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan
@EnableAutoConfiguration
@Component
@SpringBootApplication
public class CambridgePageParserApp {

    public static void main(String[] args) {
        SpringApplication.run(CambridgePageParserApp.class, args);
    }

}
