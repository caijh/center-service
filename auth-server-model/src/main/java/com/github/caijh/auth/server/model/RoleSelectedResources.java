package com.github.caijh.auth.server.model;

import java.io.Serializable;
import java.util.Set;

import com.github.caijh.auth.server.entity.Role;
import lombok.Data;

@Data
public class RoleSelectedResources implements Serializable {

    private Role role;

    private Set<SelectedResource> selectedResources;

}
