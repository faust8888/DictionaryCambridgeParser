package com.faust8888.cambridge.security.filters;

import com.faust8888.cambridge.security.context.UserContext;
import com.faust8888.cambridge.security.context.UserContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CambridgeContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(UserContext.AUTHORIZATION_ID);

        UserContextHolder.getContext().setAuthToken(authToken);

        chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
}
