package com.github.caijh.auth.server.admin.service;

import java.util.List;
import java.util.Set;

import com.github.caijh.auth.server.entity.MenuItem;
import com.github.caijh.framework.data.BaseService;

public interface MenuService extends BaseService<MenuItem, Long> {

    void saveAppMenus(String appId, Set<MenuItem> items);

    List<MenuItem> getUserMenus(String appId, Long userId);

}
