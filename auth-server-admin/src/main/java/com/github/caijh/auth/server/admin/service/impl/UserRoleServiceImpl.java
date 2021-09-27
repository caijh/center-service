package com.github.caijh.auth.server.admin.service.impl;

import java.util.List;
import javax.annotation.Nonnull;

import com.github.caijh.auth.server.admin.repository.UserRoleRepository;
import com.github.caijh.auth.server.admin.service.UserRoleService;
import com.github.caijh.auth.server.entity.UserRole;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleRepository, UserRole, Long> implements UserRoleService {

    @Override
    public void deleteByRoleIdAndUserIdIn(@Nonnull Long roleId, List<Long> userIds) {
        this.repository.deleteByRoleIdAndUserIdIn(roleId, userIds);
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return this.repository.findByUserId(userId);
    }

}
