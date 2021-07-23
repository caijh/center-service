package com.github.caijh.auth.server.admin.service;

import java.util.List;

import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.framework.data.BaseService;

public interface RoleResourceService extends BaseService<RoleResource, Long> {

    void deleteByIdNotIn(List<Long> ids);

    List<RoleResource> findByRoleIn(List<Role> roles);

}
