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
public class DictionaryAppConfig {


    @Value("${cambridge.message: Hello default!}")
    private String helloMessage;


    public String getHelloMessage() {
        return helloMessage;
    }

}
