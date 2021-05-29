package com.github.caijh.auth.server.admin.request;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class UserAddReqBody implements ReqBody {

    private String username;

    private String password;

}
