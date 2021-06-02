package com.github.caijh.auth.server.admin.request;

import java.util.List;
import java.util.Set;

import com.github.caijh.framework.core.model.ReqBody;
import lombok.Data;

@Data
public class RoleResourceReqBody implements ReqBody {

    private List<RoleResourceDto> roleResources;

    @Data
    public static class RoleResourceDto {

        private Long resourceId;

        private Set<String> actionNames;

    }

}
