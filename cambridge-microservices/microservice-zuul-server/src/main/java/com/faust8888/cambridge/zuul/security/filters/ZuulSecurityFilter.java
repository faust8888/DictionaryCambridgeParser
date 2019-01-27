package com.faust8888.cambridge.zuul.security.filters;


import com.faust8888.cambridge.zuul.security.ZuulAccessTokenService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

@Component
public class ZuulSecurityFilter extends ZuulFilter {

    private ZuulAccessTokenService accessTokenService;

    @Autowired
    public ZuulSecurityFilter(final ZuulAccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SEND_RESPONSE_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        OAuth2AccessToken token = accessTokenService.getAccessToken();
        addTokenToRequestHeader(token);

        return null;
    }

    private void addTokenToRequestHeader(OAuth2AccessToken token) {
        RequestContext.getCurrentContext()
                .addZuulRequestHeader("Authorization", token.getTokenType() + " " + token.getValue());
    }
}
