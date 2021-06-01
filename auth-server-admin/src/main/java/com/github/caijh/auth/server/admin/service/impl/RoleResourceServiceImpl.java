package com.github.caijh.auth.server.admin.service.impl;

import com.github.caijh.auth.server.admin.repository.RoleResourceRepository;
import com.github.caijh.auth.server.admin.service.RoleResourceService;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResourceRepository, RoleResource, Long> implements RoleResourceService {
}
