package com.github.caijh.auth.server.admin.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;

import com.github.caijh.auth.server.admin.repository.MenuItemRepository;
import com.github.caijh.auth.server.admin.service.ClientAppService;
import com.github.caijh.auth.server.admin.service.MenuService;
import com.github.caijh.auth.server.admin.service.ResourceService;
import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.entity.MenuItem;
import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.auth.server.model.RootMenuItem;
import com.github.caijh.commons.util.Asserts;
import com.github.caijh.commons.util.Collections;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuItemRepository, MenuItem, Long> implements MenuService {

    @Inject
    private ClientAppService clientAppService;

    @Inject
    private ResourceService resourceService;

    @Transactional
    @Override
    public void saveAppMenus(String appId, Set<MenuItem> items) {
        ClientApp clientApp = this.clientAppService.get(appId);
        Asserts.notNull(clientApp);

        RootMenuItem rootMenuItem = new RootMenuItem();
        rootMenuItem.setAppId(appId);
        rootMenuItem.setParentId(0L);
        rootMenuItem.setChildren(items);

        List<MenuItem> menuItems = new LinkedList<>();
        this.collectSubMenus(rootMenuItem, menuItems);

        if (Collections.isNotEmpty(menuItems)) {
            this.saveAll(menuItems);
        }
    }

    private void collectSubMenus(MenuItem menuItem, List<MenuItem> allMenus) {
        if (menuItem == null || Collections.isEmpty(menuItem.getChildren())) {
            return;
        }

        Set<Long> resourceIds = menuItem.getChildren().stream().map(e -> e.getAction().getResourceId()).collect(Collectors.toSet());
        Map<Long, Resource> resourceMap = this.resourceService.findAllById(resourceIds).stream().collect(Collectors.toMap(Resource::getId, e -> e));
        menuItem.getChildren().forEach(e -> {
            e.setAppId(menuItem.getAppId());
            e.setParentId(menuItem.getParentId());
            allMenus.add(e);
            Asserts.notNull(resourceMap.get(e.getAction().getResourceId()), () -> new BizException("资源id异常"));
            this.collectSubMenus(e, allMenus);
        });
    }

}