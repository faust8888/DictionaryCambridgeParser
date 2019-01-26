package com.faust8888.cambridge.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@RefreshScope
public class CambridgeConfig {

    @Value("${cambridge.message: Hello default!}")
    private String helloMessage;

    @Value("${cambridge.signing.key: cambridgeKey}")
    private String signingKey;

    public String getSigningKey() {
        return signingKey;
    }


    public String getHelloMessage() {
        return helloMessage;
    }

}
