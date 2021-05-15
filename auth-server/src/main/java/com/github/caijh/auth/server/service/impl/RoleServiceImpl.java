package com.github.caijh.auth.server.service.impl;

import java.util.List;
import javax.inject.Inject;

import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.repository.RoleRepository;
import com.github.caijh.auth.server.repository.UserRoleRepository;
import com.github.caijh.auth.server.service.RoleService;
import com.github.caijh.commons.util.Collections;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private UserRoleRepository userRoleRepository;

    public List<Role> findByAppIdAndUserId(String appId, Long userId) {
        List<Long> roleIds = this.userRoleRepository.findRoleIdByUserId(userId);
        if (Collections.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return this.roleRepository.findByAppIdAndIdIn(appId, roleIds);
    }

}
