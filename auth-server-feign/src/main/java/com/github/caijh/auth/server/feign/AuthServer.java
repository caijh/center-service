package com.github.caijh.auth.server.feign;

import com.github.caijh.auth.server.entity.ClientApp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "authServer", fallback = AuthServerFallback.class)
public interface AuthServer {

    @PostMapping(value = "/url/check")
    String checkUrl(@RequestBody UrlCheckReqBody reqBody);

    @GetMapping(value = "/client/{clientId}")
    ClientApp getClientApp(@PathVariable("clientId") String clientId);

}
