package com.github.caijh.auth.server.admin.service;

import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.framework.data.BaseService;

public interface RoleService extends BaseService<Role, Long> {

    void add(Role role);

    void update(Role role);

}
