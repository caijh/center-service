package com.github.caijh.auth.server.config.jwt;

public class JwtRuntimeException extends RuntimeException {

    public JwtRuntimeException(Exception e) {
        super(e);
    }

}
