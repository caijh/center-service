package com.github.caijh.auth.server.admin.request;

import java.util.List;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RoleUserReqBody {

    @NotEmpty
    private List<Long> userIds;

}
