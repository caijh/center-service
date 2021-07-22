package com.github.caijh.auth.server.model;

import com.github.caijh.auth.server.entity.Resource;
import lombok.Data;

@Data
public class SelectedResourceAction {

    private Long resourceId;

    private Resource.Action action;

}
