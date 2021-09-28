package com.github.caijh.auth.server.admin.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
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
import com.github.caijh.auth.server.model.SelectedResource;
import com.github.caijh.commons.util.Asserts;
import com.github.caijh.commons.util.Collections;
import com.github.caijh.framework.core.exception.BizException;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuItem, Long> implements MenuService {

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

    @Override
    public List<MenuItem> getUserMenus(String appId, Long userId) {
        ClientApp clientApp = this.clientAppService.get(appId);
        Asserts.notNull(clientApp);

        Map<Long, MenuItem> menuMap = this.<MenuItemRepository>getRepository().findByAppId(appId).stream()
                                                     .collect(Collectors.toMap(MenuItem::getId, e -> e));
        Set<MenuItem> rootMenuItems = new HashSet<>();
        this.<MenuItemRepository>getRepository().findByAppId(appId).forEach(e -> {
            if (e.getParentId() == 0L) {
                rootMenuItems.add(e);
            } else {
                MenuItem parentMenuItem = menuMap.get(e.getParentId());
                if (parentMenuItem != null) {
                    Set<MenuItem> children = this.getChildrenMenuItem(parentMenuItem);
                    children.add(e);
                }
            }
        });

        List<SelectedResource> selectedResource = this.resourceService.findSelectedResource(appId, userId);

        Map<Long, SelectedResource> map = selectedResource.stream().collect(Collectors.toMap(e -> e.getResource().getId(), e -> e));

        this.filter(rootMenuItems, map);

        return new ArrayList<>(rootMenuItems);
    }

    /**
     * 根据用户在应用内可操作资源映射过滤菜单.
     *
     * @param menuItems 菜单集合
     * @param map       用户在应用内可操作资源映射
     */
    private void filter(Set<MenuItem> menuItems, Map<Long, SelectedResource> map) {
        if (Collections.isEmpty(menuItems)) {
            return;
        }
        Iterator<MenuItem> iterator = menuItems.iterator();
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();

            this.filter(menuItem.getChildren(), map);

            SelectedResource selectedResource = map.get(menuItem.getResourceAction().getResourceId());
            if (selectedResource == null || !selectedResource.getAllowedActions().contains(menuItem.getResourceAction().getAction())) {
                iterator.remove();
            }
        }
    }

    private Set<MenuItem> getChildrenMenuItem(MenuItem menuItem) {
        Set<MenuItem> children = menuItem.getChildren();
        if (children == null) {
            children = new HashSet<>();
            menuItem.setChildren(children);
        }
        return menuItem.getChildren();
    }

    private void collectSubMenus(MenuItem menuItem, List<MenuItem> allMenus) {
        if (menuItem == null || Collections.isEmpty(menuItem.getChildren())) {
            return;
        }

        Set<Long> resourceIds = menuItem.getChildren().stream().map(e -> e.getResourceAction().getResourceId()).collect(Collectors.toSet());
        Map<Long, Resource> resourceMap = this.resourceService.findAllById(resourceIds).stream().collect(Collectors.toMap(Resource::getId, e -> e));
        menuItem.getChildren().forEach(e -> {
            e.setAppId(menuItem.getAppId());
            e.setParentId(menuItem.getParentId());
            allMenus.add(e);
            Asserts.notNull(resourceMap.get(e.getResourceAction().getResourceId()), () -> new BizException("资源id异常"));
            this.collectSubMenus(e, allMenus);
        });
    }

}
