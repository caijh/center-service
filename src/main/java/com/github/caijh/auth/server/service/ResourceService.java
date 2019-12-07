package com.github.caijh.auth.server.service;

import java.util.List;

import com.github.caijh.auth.server.vo.ResourceSelected;

public interface ResourceService {

    List<ResourceSelected> findResourceSelected(String appId, Long userId);

}
