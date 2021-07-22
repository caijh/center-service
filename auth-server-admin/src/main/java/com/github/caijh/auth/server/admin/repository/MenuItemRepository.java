package com.github.caijh.auth.server.admin.repository;

import java.util.List;

import com.github.caijh.auth.server.entity.MenuItem;
import com.github.caijh.framework.data.jpa.BaseRepository;

public interface MenuItemRepository extends BaseRepository<MenuItem, Long> {

    List<MenuItem> findByAppId(String appId);

}
