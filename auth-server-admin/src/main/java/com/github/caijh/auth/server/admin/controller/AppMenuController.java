package com.github.caijh.auth.server.admin.controller;

import javax.inject.Inject;

import com.github.caijh.auth.server.admin.request.MenuReqBody;
import com.github.caijh.auth.server.admin.service.MenuService;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppMenuController extends BaseController {

    @Inject
    private MenuService menuService;

    @PostMapping(value = "/app/{appId}/menu/")
    public void save(@PathVariable String appId, @RequestBody MenuReqBody reqBody) {
        this.menuService.saveAppMenus(appId, reqBody.getItems());
    }

}
