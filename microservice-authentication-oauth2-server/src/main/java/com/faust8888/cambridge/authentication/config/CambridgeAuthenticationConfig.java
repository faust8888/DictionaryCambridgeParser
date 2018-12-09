package com.faust8888.cambridge.authentication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class CambridgeAuthenticationConfig {

    @Value("${cambridge.signing.key: cambridgeKey}")
    private String signingKey;

    public String getSigningKey() {
        return signingKey;
    }
}
