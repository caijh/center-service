package com.github.caijh.auth.server.admin.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.github.caijh.auth.server.admin.constants.MessageConstants;
import com.github.caijh.auth.server.admin.repository.ResourceRepository;
import com.github.caijh.auth.server.admin.service.ResourceService;
import com.github.caijh.auth.server.admin.service.RoleResourceService;
import com.github.caijh.auth.server.admin.service.RoleService;
import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.auth.server.model.SelectedResource;
import com.github.caijh.commons.util.Asserts;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.data.BaseServiceImpl;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceRepository, Resource, Long> implements ResourceService {

    @Inject
    private RoleService roleService;

    @Inject
    private RoleResourceService roleResourceService;

    @Override
    public void add(Resource resource) {
        this.save(resource);
    }

    @Transactional
    @Override
    public void update(Resource resource) {
        final Optional<Resource> oldResource = this.findById(resource.getId());
        Asserts.notNull(oldResource, () -> BizException.of(MessageConstants.RESOURCE_NOT_FOUND));
        this.save(resource);
    }

    @Override
    public List<SelectedResource> findSelectedResource(String appId, Long userId) {
        List<Role> roles = this.roleService.listByAppIdAndUserId(appId, userId);
        Map<Long, SelectedResource> map = Maps.newHashMap();
        this.roleResourceService.findByRoleIn(roles)
                                .stream()
                                .collect(Collectors.groupingBy(RoleResource::getRole))
                                .forEach((role, roleResources) -> roleResources.forEach(e -> {
                                    SelectedResource selectedResource = map.get(e.getResource().getId());
                                    if (selectedResource == null) {
                                        selectedResource = new SelectedResource();
                                        selectedResource.setResource(e.getResource());
                                        selectedResource.setAllowedActions(new HashSet<>());
                                        map.put(e.getResource().getId(), selectedResource);
                                    }
                                    List<Resource.Action> allowedActions = e.getResource().getActions().stream()
                                                                            .filter(action -> e.getActionNames().contains(action.getName()))
                                                                            .collect(Collectors.toList());
                                    selectedResource.getAllowedActions().addAll(allowedActions);
                                }));
        return new ArrayList<>(map.values());
    }

}
