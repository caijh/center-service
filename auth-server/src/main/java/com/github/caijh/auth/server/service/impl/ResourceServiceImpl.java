package com.github.caijh.auth.server.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.auth.server.model.RoleSelectedResources;
import com.github.caijh.auth.server.model.SelectedResource;
import com.github.caijh.auth.server.repository.RoleRepository;
import com.github.caijh.auth.server.repository.RoleResourceRepository;
import com.github.caijh.auth.server.service.ResourceService;
import com.github.caijh.auth.server.service.RoleService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "ResourceServiceImpl#300")
public class ResourceServiceImpl implements ResourceService {

    @Inject
    private RoleRepository roleRepository;
    @Inject
    private RoleResourceRepository roleResourceRepository;

    @Inject
    private RoleService roleService;

    @Cacheable
    @Override
    public List<RoleSelectedResources> findRoleSelectedResources(String appId, Long userId) {
        List<Role> roles = this.roleService.findByAppIdAndUserId(appId, userId);
        List<RoleSelectedResources> roleSelectedResourcesList = new ArrayList<>();
        this.roleResourceRepository.findByRoleIn(roles)
                                   .stream()
                                   .collect(Collectors.groupingBy(RoleResource::getRole))
                                   .forEach((role, roleResources) -> {
                                       RoleSelectedResources roleSelectedResources = new RoleSelectedResources();
                                       roleSelectedResources.setRole(role);
                                       roleSelectedResources.setSelectedResources(new HashSet<>());

                                       roleResources.forEach(e -> {
                                           SelectedResource selected = new SelectedResource();
                                           selected.setResource(e.getResource());
                                           selected.setAllowedActions(new HashSet<>());
                                           List<Resource.Action> allowedActions = e.getResource().getActions().stream()
                                                                                   .filter(action -> e.getActionNames().contains(action.getName()))
                                                                                   .collect(Collectors.toList());
                                           selected.getAllowedActions().addAll(allowedActions);
                                           roleSelectedResources.getSelectedResources().add(selected);
                                       });
                                       roleSelectedResourcesList.add(roleSelectedResources);
                                   });

        return roleSelectedResourcesList;
    }

}
