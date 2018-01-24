package org.dashboard.main.security;

import org.dashboard.main.data.User;
import org.dashboard.main.data.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class GoogleAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    private UserDAO userDAO;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        GoogleAuthenticationToken token = (GoogleAuthenticationToken)authentication;

        String userId = GoogleValidator.getUserID((String) token.getCredentials());
        if (userId == null){
            token.setAuthenticated(false);
            return token;
        }
        User user = userDAO.findByUsername(userId);
        if (user == null){
            token.setAuthenticated(false);
            return token;
        }

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
