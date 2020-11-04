package com.github.caijh.auth.server.feign;

import java.util.List;

import lombok.Data;

@Data
public class AuthInfo {

    private String userId;
    private String userName;
    private String clientId;
    private List<String> scope;

}
