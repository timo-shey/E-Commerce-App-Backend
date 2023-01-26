package com.example.ecommerce.service;

import com.example.ecommerce.config.MessageStrings;
import com.example.ecommerce.exceptions.AuthenticationFailException;
import com.example.ecommerce.model.AuthenticationToken;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findTokenByUser(user);
    }

    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepository.findTokenByToken(token);
        if (Objects.nonNull(authenticationToken)) {
            if (Objects.nonNull(authenticationToken.getUser())) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if (!Objects.nonNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_PRESENT);
        }
        if (!Objects.nonNull(getUser(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOKEN_NOT_VALID);
        }
    }
}
