package com.github.caijh.auth.server.config.oauth;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config.oauth")
@Data
public class TokenKeyProperties {

    private String publicKey;
    private String privateKey;

}
