package com.github.caijh.auth.server.repository;

import com.github.caijh.auth.server.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
