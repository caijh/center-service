package com.github.caijh.auth.server.config.oauth;

public interface TokenRevocationService {

    void revoke(String token);

    boolean supports(String tokenType);

}
