package com.github.caijh.auth.server.admin.repository;

import java.util.List;

import com.github.caijh.auth.server.entity.UserRole;
import com.github.caijh.framework.data.jpa.BaseRepository;

public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

    void deleteByRoleIdAndUserIdIn(Long roleId, List<Long> userIds);

    List<UserRole> findByUserId(Long userId);

}
