package com.github.caijh.auth.server.service;

import java.util.List;

import com.github.caijh.auth.server.entity.Role;

public interface RoleService {

    List<Role> findByAppIdAndUserId(String appId, Long userId);

}
