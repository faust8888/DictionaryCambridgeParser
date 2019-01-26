package com.faust8888.cambridge.authentication.config.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class JWTTokenEnhancer implements TokenEnhancer {

    private static final String ORGANIZATION_ID = "cambridge";

    @Override
    public OAuth2AccessToken enhance(final OAuth2AccessToken accessToken, final OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("organizationId", ORGANIZATION_ID);
        ((DefaultOAuth2AccessToken) accessToken)
                .setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
