package com.github.caijh.auth.server.admin.service.impl;

import com.github.caijh.auth.server.admin.repository.ResourceRepository;
import com.github.caijh.auth.server.admin.service.ResourceService;
import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.framework.data.BaseServiceImpl;

public class ResourceServiceImpl extends BaseServiceImpl<ResourceRepository, Resource, Long> implements ResourceService {

    @Override
    public void add(Resource resource) {
        this.save(resource);
    }

}
