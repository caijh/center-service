package com.github.caijh.auth.server.admin.request;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.github.caijh.auth.server.entity.Resource;
import lombok.Data;

@Data
public class ResourceUpdateReqBody {

    private Long id;

    @NotNull
    private String name;

    /**
     * 资源的操作集
     */
    @NotEmpty
    private List<Resource.Action> actions;

}
