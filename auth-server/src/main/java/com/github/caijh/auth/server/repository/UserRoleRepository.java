package com.github.caijh.auth.server.repository;

import java.util.List;

import com.github.caijh.auth.server.entity.UserRole;
import com.github.caijh.framework.data.jpa.BaseRepository;

public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

    List<Long> findRoleIdByUserId(Long userId);

}
