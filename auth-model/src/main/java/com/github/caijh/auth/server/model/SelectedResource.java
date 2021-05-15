package com.github.caijh.auth.server.model;

import java.io.Serializable;
import java.util.Set;

import com.github.caijh.auth.server.entity.Resource;
import lombok.Data;

/**
 * 选择的可以操作集合.
 */
@Data
public class SelectedResource implements Serializable {

    private Resource resource;

    private Set<Resource.Action> allowedActions;

}
