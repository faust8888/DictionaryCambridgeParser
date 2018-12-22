package com.faust8888.cambridge.authentication.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private AuthenticationManager authenticationManagerBean;
    private UserDetailsService userDetailsServiceBean;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public OAuth2Config(AuthenticationManager authenticationManagerBean, UserDetailsService userDetailsServiceBean,
                        PasswordEncoder passwordEncoder) {
        this.authenticationManagerBean = authenticationManagerBean;
        this.userDetailsServiceBean = userDetailsServiceBean;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory()
                .withClient("cambridge-internal-client")
                .secret(passwordEncoder.encode("cambridge-internal-client-test-password"))
                .authorizedGrantTypes("password", "client_credentials")
                .scopes("webclient")
                .accessTokenValiditySeconds(500);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManagerBean)
                .userDetailsService(userDetailsServiceBean);
    }
}
