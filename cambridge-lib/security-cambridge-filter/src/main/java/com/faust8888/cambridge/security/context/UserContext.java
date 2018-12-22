package com.faust8888.cambridge.security.context;

import org.springframework.stereotype.Component;

@Component
public class UserContext {

    public static final String AUTHORIZATION_ID = "authorization";

    private String authToken= new String();

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
