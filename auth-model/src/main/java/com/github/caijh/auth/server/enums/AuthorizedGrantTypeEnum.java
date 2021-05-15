package com.github.caijh.auth.server.enums;

/**
 * OAuth的验证方式.
 */
public enum AuthorizedGrantTypeEnum {
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    PASSWORD("password"),
    CLIENT_CREDENTIALS("client_credentials"),
    REFRESH_TOKEN("refresh_token");
    private final String name;

    AuthorizedGrantTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
