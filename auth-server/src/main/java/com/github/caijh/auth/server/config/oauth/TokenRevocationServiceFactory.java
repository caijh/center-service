package com.github.caijh.auth.server.config.oauth;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class TokenRevocationServiceFactory {

    @Inject
    private List<TokenRevocationService> revocationServices;

    /**
     * create TokenRevocationService.
     *
     * @param hint access_token or refresh_token
     * @return TokenRevocationService
     */
    public TokenRevocationService create(String hint) {
        return this.revocationServices.stream()
                                      .filter(service -> service.supports(hint))
                                      .findFirst().orElse(this.noopRevocationService());
    }

    private TokenRevocationService noopRevocationService() {
        return new TokenRevocationService() {
            @Override
            public boolean supports(String tokenType) {
                return false;
            }

            @Override
            public void revoke(String token) {
                throw new UnsupportedOperationException();
            }
        };
    }

}
