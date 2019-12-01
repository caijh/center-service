package com.github.caijh.authorizationserver.controller;

import javax.inject.Inject;

import com.github.caijh.authorizationserver.config.oauth.TokenRevocationService;
import com.github.caijh.authorizationserver.config.oauth.TokenRevocationServiceFactory;
import com.github.caijh.authorizationserver.request.TokenRevokeReqBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenRevocationController {

    @Inject
    private TokenRevocationServiceFactory tokenRevocationServiceFactory;

    /**
     * revoke token.
     *
     * @param reqBody token value
     * @return ResponseEntity
     */
    @PostMapping("/oauth/revoke")
    public ResponseEntity<String> revokeToken(@RequestBody TokenRevokeReqBody reqBody) {
        TokenRevocationService tokenRevocationService = tokenRevocationServiceFactory.create(reqBody.getTokenType());
        tokenRevocationService.revoke(reqBody.getToken());
        return ResponseEntity.ok("logout success");
    }

}
