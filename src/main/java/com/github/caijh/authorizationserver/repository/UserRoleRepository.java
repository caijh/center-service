package com.github.caijh.authorizationserver.repository;

import com.github.caijh.authorizationserver.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
