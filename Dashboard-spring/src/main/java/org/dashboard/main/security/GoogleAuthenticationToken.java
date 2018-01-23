package org.dashboard.main.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class GoogleAuthenticationToken extends AbstractAuthenticationToken {

    private String token;

    public GoogleAuthenticationToken(String token){
        super(null);
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
