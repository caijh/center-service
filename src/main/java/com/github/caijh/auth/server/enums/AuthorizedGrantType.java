package com.github.caijh.auth.server.enums;

public enum AuthorizedGrantType {
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");
    private String name;

    AuthorizedGrantType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
