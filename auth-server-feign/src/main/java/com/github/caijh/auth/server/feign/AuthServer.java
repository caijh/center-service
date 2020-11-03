package com.github.caijh.auth.server.feign;

import com.github.caijh.framework.core.model.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-server", fallback = AuthServerFallback.class)
public interface AuthServer {

    @PostMapping(value = "/url/check")
    ResponseEntity<R<Void>> checkUrl(@RequestBody UrlCheckReqBody reqBody);

}
