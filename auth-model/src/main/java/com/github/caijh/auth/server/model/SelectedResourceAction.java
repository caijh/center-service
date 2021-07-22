package com.github.caijh.auth.server.model;

import java.io.Serializable;

import com.github.caijh.auth.server.entity.Resource;
import lombok.Data;

@Data
public class SelectedResourceAction implements Serializable {

    private static final long serialVersionUID = -4938279289253197518L;
    private Long resourceId;

    private Resource.Action action;

}
