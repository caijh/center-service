package com.github.caijh.authorizationserver.request;

import javax.validation.constraints.NotNull;

import com.github.caijh.commons.base.ReqBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRegistryReqBody implements ReqBody {

    private String clientId;

    @NotNull
    private String clientType;

    @NotNull
    private String clientName;

    @NotNull
    private String redirectUri;

    @NotNull
    private Integer accessTokenValiditySeconds;

}
