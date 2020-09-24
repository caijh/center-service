package com.github.caijh.auth.server.service;

import java.util.List;

import com.github.caijh.auth.server.vo.RoleSelectedResources;

public interface ResourceService {

    List<RoleSelectedResources> findRoleSelectedResources(String appId, Long userId);

}
