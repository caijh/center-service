package com.github.caijh.auth.server.admin.repository;

import java.util.List;

import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.framework.data.jpa.BaseRepository;

public interface RoleResourceRepository extends BaseRepository<RoleResource, Long> {

    void deleteByIdNotIn(List<Long> ids);

    List<RoleResource> findByRoleIn(List<Role> roles);

}
