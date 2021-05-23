package com.github.caijh.auth.server.admin.repository;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class UserAddReqBody implements ReqBody {

    private String username;

    private String password;

}
