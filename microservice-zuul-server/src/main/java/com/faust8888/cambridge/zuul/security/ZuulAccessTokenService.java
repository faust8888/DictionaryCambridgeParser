package com.faust8888.cambridge.zuul.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZuulAccessTokenService {

    private DiscoveryClient discoveryClient;
    private OAuth2RestTemplate customRestTemplate;

    @Autowired
    public ZuulAccessTokenService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.customRestTemplate = oauth2RestTemplate();
    }

    public OAuth2AccessToken getAccessToken() {
        customRestTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        return customRestTemplate.getAccessToken();
    }

    private String getAuthenticationServerUrl() {
        List<ServiceInstance> instances =
                discoveryClient.getInstances("cambridgeauthentication");

        if(instances.isEmpty()) {
            throw new RuntimeException("Service cambridgeauthentication was not registred at Eureka!");
        }

        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost();
        Integer port = serviceInstance.getPort();

        return String.format("http://%s:%s/oauth/token", host, port);
    }

    private OAuth2RestTemplate oauth2RestTemplate() {
        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(new DefaultAccessTokenRequest()));
    }

    private OAuth2ProtectedResourceDetails resource() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List scopes = new ArrayList<String>(1);
        scopes.add("webclient");

        resource.setGrantType("client_credentials");
        resource.setScope(scopes);
        resource.setAccessTokenUri(getAuthenticationServerUrl());
        resource.setClientId("cambridge-internal-client");
        resource.setClientSecret("cambridge-internal-client-test-password");
        resource.setUsername("cambridge.internal.user");
        resource.setPassword("cambridge.internal.user.test.password");

        return resource;
    }

}
