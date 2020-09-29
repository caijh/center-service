package com.github.caijh.auth.server.request;

import com.github.caijh.commons.base.ReqBody;
import lombok.Data;

@Data
public class TokenRevokeReqBody implements ReqBody {

    private String tokenType;
    private String token;

}
