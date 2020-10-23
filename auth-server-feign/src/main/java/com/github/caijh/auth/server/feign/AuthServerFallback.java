package com.github.caijh.auth.server.feign;

import com.github.caijh.auth.server.entity.ClientApp;

public class AuthServerFallback implements AuthServer {

    @Override
    public String checkUrl(UrlCheckReqBody reqBody) {
        return "Forbidden";
    }

    @Override
    public ClientApp getClientApp(String clientId) {
        return new ClientApp();
    }

}