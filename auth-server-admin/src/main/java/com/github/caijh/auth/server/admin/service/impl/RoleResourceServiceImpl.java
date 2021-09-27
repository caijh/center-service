package com.github.caijh.auth.server.admin.service.impl;

import java.util.List;

import com.github.caijh.auth.server.admin.repository.RoleResourceRepository;
import com.github.caijh.auth.server.admin.service.RoleResourceService;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.commons.util.Collections;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource, Long> implements RoleResourceService {

    @Override
    public void deleteByIdNotIn(List<Long> ids) {
        if (Collections.isEmpty(ids)) {
            return;
        }
        this.<RoleResourceRepository>getRepository().deleteByIdNotIn(ids);
    }

    @Override
    public List<RoleResource> findByRoleIn(List<Role> roles) {
        return this.repository.findByRoleIn(roles);
    }

}
