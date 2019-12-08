package com.github.caijh.auth.server.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.RoleResource;
import com.github.caijh.auth.server.repository.RoleRepository;
import com.github.caijh.auth.server.repository.RoleResourceRepository;
import com.github.caijh.auth.server.service.ResourceService;
import com.github.caijh.auth.server.vo.ResourceSelected;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "ResourceServiceImpl")
public class ResourceServiceImpl implements ResourceService {

    @Inject
    private RoleRepository roleRepository;
    @Inject
    private RoleResourceRepository roleResourceRepository;

    @Cacheable
    @Override
    public List<ResourceSelected> findResourceSelected(String appId, Long userId) {
        List<Role> roles = roleRepository.findByAppIdAndUserId(appId, userId);
        List<ResourceSelected> selectedResources = new ArrayList<>();
        roleResourceRepository.findByRoleIn(roles)
                              .stream()
                              .collect(Collectors.groupingBy(RoleResource::getResource))
                              .forEach((k, v) -> {
                                  ResourceSelected selected = new ResourceSelected();
                                  selected.setResource(k);
                                  selected.setActionNames(new HashSet<>());
                                  v.forEach(e -> selected.getActionNames().addAll(e.getActionNames()));
                                  selectedResources.add(selected);
                              });

        return selectedResources;
    }

}
