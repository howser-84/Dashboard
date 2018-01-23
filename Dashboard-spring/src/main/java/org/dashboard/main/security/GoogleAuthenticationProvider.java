package org.dashboard.main.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class GoogleAuthenticationProvider implements AuthenticationProvider{
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        GoogleAuthenticationToken token = (GoogleAuthenticationToken)authentication;
        // TODO add token validation here
        token.setAuthenticated(true);
        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        if (aClass.isAssignableFrom(GoogleAuthenticationToken.class))
            return true;
        return false;
    }
}
