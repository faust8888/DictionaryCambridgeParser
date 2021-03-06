package com.faust8888.cambridge.authentication.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@ComponentScan(value = {"com.faust8888.cambridge.authentication"})
@EnableWebSecurity(debug = true)
@EnableResourceServer
@EnableDiscoveryClient
@EnableAuthorizationServer
@RestController
@SpringBootApplication
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }


    @RequestMapping(value = { "/user" }, produces = "application/json")
    public Map<String, Object> user(final OAuth2Authentication authentication) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user",
                authentication.getUserAuthentication()
                        .getPrincipal());
        userInfo.put("authorities",
                AuthorityUtils.authorityListToSet(
                        authentication.getUserAuthentication()
                                .getAuthorities()));
        return userInfo;
    }
}
