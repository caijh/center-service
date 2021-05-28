package com.github.caijh.auth.server.admin.service.impl;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.github.caijh.auth.server.admin.constants.MessageConstants;
import com.github.caijh.auth.server.admin.repository.RoleRepository;
import com.github.caijh.auth.server.admin.service.ClientAppService;
import com.github.caijh.auth.server.admin.service.RoleService;
import com.github.caijh.auth.server.admin.utils.RoleConvertMapper;
import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.commons.util.Asserts;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleRepository, Role, Long> implements RoleService {

    @Inject
    private ClientAppService clientAppService;

    @Transactional
    @Override
    public void add(Role role) {
        ClientApp clientApp = this.clientAppService.get(role.getAppId());
        Asserts.notNull(clientApp, () -> BizException.of(MessageConstants.APP_NOT_FOUND));
        this.save(role);
    }

    @Transactional
    @Override
    public void update(Role role) {
        Role oldRole = this.getOne(role.getId());
        Asserts.notNull(oldRole, () -> BizException.of(MessageConstants.ROLE_NOT_FOUND));

        oldRole = RoleConvertMapper.MAPPER.fromRole(role);

        this.save(oldRole);
    }

}
