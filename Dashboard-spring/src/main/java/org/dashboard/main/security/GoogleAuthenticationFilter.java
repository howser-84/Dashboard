package org.dashboard.main.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoogleAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

    public GoogleAuthenticationFilter(AuthenticationManager manager){
        super("/*");
        setAuthenticationManager(manager);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)req;
        if (httpReq.getHeader("Authorization") != null){
            if (httpReq.getHeader("Authorization").startsWith("Bearer ")){
                String token = httpReq.getHeader("Authorization").replaceFirst("Bearer ", "");
                GoogleAuthenticationToken authRequest = new GoogleAuthenticationToken(token);
                Authentication authResult = getAuthenticationManager().authenticate(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return new GoogleAuthenticationToken("dumymtoken");
    }
}
