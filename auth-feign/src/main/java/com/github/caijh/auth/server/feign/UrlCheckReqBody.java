package com.github.caijh.auth.server.feign;

import java.util.List;
import javax.annotation.Nonnull;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
