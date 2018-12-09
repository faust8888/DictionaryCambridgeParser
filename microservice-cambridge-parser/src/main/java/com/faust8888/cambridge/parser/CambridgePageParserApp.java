package com.faust8888.cambridge.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;

@ComponentScan(value = {"com.faust8888.cambridge.parser"})
@EnableAutoConfiguration
@Component
@EnableResourceServer
@SpringBootApplication
public class CambridgePageParserApp {

    public static void main(String[] args) {
        SpringApplication.run(CambridgePageParserApp.class, args);
    }

}
