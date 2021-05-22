package com.github.caijh.auth.server.admin.request;

import javax.validation.constraints.NotNull;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
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
