package com.github.caijh.authorizationserver.config.jwt;

public class JwtRuntimeException extends RuntimeException {

    public JwtRuntimeException(Exception e) {
        super(e);
    }

}
