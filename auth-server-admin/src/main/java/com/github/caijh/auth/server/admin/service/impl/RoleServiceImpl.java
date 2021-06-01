package com.github.caijh.auth.server.admin.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.github.caijh.auth.server.admin.constants.MessageConstants;
import com.github.caijh.auth.server.admin.repository.RoleRepository;
import com.github.caijh.auth.server.admin.service.ClientAppService;
import com.github.caijh.auth.server.admin.service.RoleResourceService;
import com.github.caijh.auth.server.admin.service.RoleService;
import com.github.caijh.auth.server.admin.service.UserRoleService;
import com.github.caijh.auth.server.admin.utils.RoleConvertMapper;
import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.auth.server.entity.UserRole;
import com.github.caijh.commons.util.Asserts;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.core.utils.PropertyResolver;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleRepository, Role, Long> implements RoleService {

    @Inject
    private ClientAppService clientAppService;

    @Inject
    private UserRoleService userRoleService;

    @Inject
    private RoleResourceService roleResourceService;

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

    @Transactional
    @Override
    public void addUser(List<UserRole> userRoles) {
        this.deleteUser(userRoles);

        this.userRoleService.saveAll(userRoles);
    }

    @Transactional
    @Override
    public void deleteUser(List<UserRole> userRoles) {
        Map<Long, List<UserRole>> group = userRoles.stream().collect(Collectors.groupingBy(UserRole::getRoleId));
        group.forEach((k, v) -> {
            List<Long> userIds = v.stream().map(UserRole::getUserId).collect(Collectors.toList());
            this.userRoleService.deleteByRoleIdAndUserIdIn(k, userIds);
        });
    }

    @Override
    public List<RoleResource> listRoleResource(Long roleId) {
        Role role = this.getOne(roleId);
        Asserts.notNull(role);
        Specification<RoleResource> specification =
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(PropertyResolver.methodToProperty(RoleResource::getRole)), role);
        return this.roleResourceService.findAll(Specification.where(specification));
    }

}
