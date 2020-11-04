package com.github.caijh.auth.server.feign;

import com.github.caijh.framework.core.model.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-server", fallback = AuthServerFeignFallback.class)
public interface AuthServerFeign {

    @GetMapping(value = "/oauth/check_token")
    ResponseEntity<AuthInfo> checkToken(@RequestParam String token);

    @PostMapping(value = "/url/check")
    ResponseEntity<R<Void>> checkUrl(@RequestBody UrlCheckReqBody reqBody);


}
