package com.github.caijh.auth.server.vo;

import java.util.Set;

import com.github.caijh.auth.entity.Role;
import lombok.Data;

@Data
public class RoleSelectedResources {

    private Role role;

    private Set<SelectedResource> selectedResources;

}
