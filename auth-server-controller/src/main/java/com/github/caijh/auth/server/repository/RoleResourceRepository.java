package com.github.caijh.auth.server.repository;

import java.util.List;

import com.github.caijh.auth.entity.Role;
import com.github.caijh.auth.entity.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleResourceRepository extends JpaRepository<RoleResource, Long> {

    List<RoleResource> findByRoleIn(List<Role> roles);

}
