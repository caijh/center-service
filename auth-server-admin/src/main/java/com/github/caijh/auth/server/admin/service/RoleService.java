package com.github.caijh.auth.server.admin.service;

import java.util.List;

import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.auth.server.entity.UserRole;
import com.github.caijh.framework.data.BaseService;

public interface RoleService extends BaseService<Role, Long> {

    void add(Role role);

    void update(Role role);

    void addUser(List<UserRole> userRoles);

    void deleteUser(List<UserRole> userRoles);

    List<RoleResource> listRoleResource(Long id);

    void saveRoleResources(List<RoleResource> roleResources);

}
