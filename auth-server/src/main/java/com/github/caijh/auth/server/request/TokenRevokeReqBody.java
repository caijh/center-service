package com.github.caijh.auth.server.request;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class TokenRevokeReqBody implements ReqBody {

    private String tokenType;
    private String token;

}
