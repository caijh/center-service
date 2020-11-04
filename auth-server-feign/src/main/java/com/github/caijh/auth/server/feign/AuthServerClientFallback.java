package com.github.caijh.auth.server.feign;

import com.github.caijh.framework.core.model.R;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
public class AuthServerClientFallback implements AuthServerClient {

    @Override
    public ResponseEntity<AuthInfo> checkToken(String token) {
        return ResponseEntity.ok(new AuthInfo());
    }

    @Override
    public ResponseEntity<R<Void>> checkUrl(UrlCheckReqBody reqBody) {
        return ResponseEntity.ok().body(R.of(FORBIDDEN.getReasonPhrase()));
    }

}
