package com.github.caijh.auth.server.admin.service.impl;

import com.github.caijh.auth.server.admin.repository.RoleRepository;
import com.github.caijh.auth.server.admin.service.RoleService;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleRepository, Role, Long> implements RoleService {

}
