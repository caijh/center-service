package com.github.caijh.auth.server.admin.request;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class RoleUpdateReqBody implements ReqBody {

    private String appId;

    private String code;

    private String name;

}
