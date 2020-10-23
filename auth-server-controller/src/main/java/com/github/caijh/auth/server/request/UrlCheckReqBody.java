package com.github.caijh.auth.server.request;

import java.util.List;
import javax.annotation.Nonnull;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class UrlCheckReqBody implements ReqBody {

    @Nonnull
    private String clientId;

    @Nonnull
    private Long userId;

    @Nonnull
    private List<Object> roleCodes;

    @Nonnull
    private String url;

    @Nonnull
    private String method;

}
