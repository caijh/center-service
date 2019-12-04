package com.github.caijh.auth.server.repository;

import java.util.List;

import com.github.caijh.auth.server.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select * from (select * from role where role.app_id = :appId) as role inner join (select * from user_role where user_id = :userId) as user_role on role.id = user_role.role_id", nativeQuery = true)
    List<Role> findByAppIdAndUserId(@Param("appId") String appId, @Param("userId") Long userId);

}
