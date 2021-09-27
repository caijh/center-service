package com.github.caijh.auth.server.admin.controller;

import java.util.List;
import javax.inject.Inject;

import com.github.caijh.auth.server.admin.request.MenuReqBody;
import com.github.caijh.auth.server.admin.service.MenuService;
import com.github.caijh.auth.server.entity.MenuItem;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppMenuController extends BaseController {

    @Inject
    private MenuService menuService;

    /**
     * 保存应用的菜单树.
     *
     * @param appId   应用id
     * @param reqBody MenuReqBody
     */
    @PostMapping(value = "/app/{appId}/menu/")
    public void save(@PathVariable String appId, @RequestBody MenuReqBody reqBody) {
        this.menuService.saveAppMenus(appId, reqBody.getItems());
    }

    /**
     * 获取用户在应用的菜单.
     *
     * @param appId  应用id
     * @param userId 用户id
     * @return 用户菜单树
     */
    @GetMapping(value = "/app/{appId}/user/{userId}/menu")
    public List<MenuItem> userMenus(@PathVariable String appId, @PathVariable Long userId) {
        return this.menuService.getUserMenus(appId, userId);
    }

}
