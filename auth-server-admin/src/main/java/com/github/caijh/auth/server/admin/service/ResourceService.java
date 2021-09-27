package com.github.caijh.auth.server.admin.service;

import java.util.List;

import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.auth.server.model.SelectedResource;
import com.github.caijh.framework.data.BaseService;

public interface ResourceService extends BaseService<Resource, Long> {

    void add(Resource resource);

    void update(Resource resource);

    /**
     * 获取用户在应用内可操作资源.
     *
     * @param appId  应用id
     * @param userId 用户id
     * @return {@code List<SelectedResource>}
     */
    List<SelectedResource> findSelectedResource(String appId, Long userId);

}
