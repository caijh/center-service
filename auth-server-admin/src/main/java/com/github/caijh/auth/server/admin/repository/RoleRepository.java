package com.github.caijh.auth.server.admin.repository;

import java.util.List;

import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.framework.data.jpa.BaseRepository;

public interface RoleRepository extends BaseRepository<Role, Long> {

    List<Role> findByAppIdAndIdIn(String appId, List<Long> roleIds);

}
