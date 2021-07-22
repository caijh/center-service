package com.github.caijh.auth.server.admin.controller;

import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppMenuController extends BaseController {

    @PostMapping(value = "/app/{appId}/menu/")
    public void save(@PathVariable String appId) {

    }

}
