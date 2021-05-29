package com.github.caijh.auth.server.admin.service;

import java.util.List;

import com.github.caijh.auth.server.entity.UserRole;
import com.github.caijh.framework.data.BaseService;

public interface UserRoleService extends BaseService<UserRole, Long> {

    void deleteByRoleIdAndUserIdIn(Long roleId, List<Long> userIds);

}
