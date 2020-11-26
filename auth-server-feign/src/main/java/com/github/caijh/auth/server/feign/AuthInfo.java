package com.github.caijh.auth.server.feign;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;


@Data
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AuthInfo {

    private String userId;

    private String userName;

    private String clientId;

    private List<String> scope;

}
