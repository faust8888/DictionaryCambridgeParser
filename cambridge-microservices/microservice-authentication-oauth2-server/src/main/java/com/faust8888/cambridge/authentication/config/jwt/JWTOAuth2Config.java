package com.faust8888.cambridge.authentication.config.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
public class JWTOAuth2Config extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsServiceBean;
    private TokenStore tokenStore;
    private DefaultTokenServices tokenServices;
    private JwtAccessTokenConverter tokenConverter;
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    public JWTOAuth2Config(final AuthenticationManager authenticationManager,
                           final UserDetailsService userDetailsServiceBean,
                           final TokenStore tokenStore,
                           final DefaultTokenServices tokenService,
                           final JwtAccessTokenConverter tokenConverter,
                           final TokenEnhancer jwtTokenEnhancer) {
        this.authenticationManager = authenticationManager;
        this.userDetailsServiceBean = userDetailsServiceBean;
        this.tokenStore = tokenStore;
        this.tokenServices = tokenService;
        this.tokenConverter = tokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain
                .setTokenEnhancers(
                        Arrays.asList(jwtTokenEnhancer, tokenConverter));
        endpoints
                .tokenStore(tokenStore)
                .accessTokenConverter(tokenConverter)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceBean);
    }
}
