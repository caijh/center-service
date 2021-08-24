package com.github.caijh.auth.server.admin.service.impl;

import java.util.Optional;
import javax.transaction.Transactional;

import com.github.caijh.auth.server.admin.constants.MessageConstants;
import com.github.caijh.auth.server.admin.service.ResourceService;
import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.commons.util.Asserts;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.data.BaseServiceImpl;

public class ResourceServiceImpl extends BaseServiceImpl<Resource, Long> implements ResourceService {

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

}
