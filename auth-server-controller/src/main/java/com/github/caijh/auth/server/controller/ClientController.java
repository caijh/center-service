package com.github.caijh.auth.server.controller;

import javax.inject.Inject;

import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.service.ClientAppService;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController extends BaseController {

    @Inject
    private ClientAppService clientAppService;

    /**
     * 查看client app 详情.
     *
     * @param id client id
     * @return object of ClientApp
     */
    @GetMapping(value = "/client/{id}")
    public ClientApp detail(@PathVariable String id) {
        return this.clientAppService.get(id);
    }

}
