package com.github.caijh.auth.server.admin.service;

import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.framework.data.BaseService;

public interface ResourceService extends BaseService<Resource, Long> {

    void add(Resource resource);

}
