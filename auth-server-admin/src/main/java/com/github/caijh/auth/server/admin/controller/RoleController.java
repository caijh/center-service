package com.github.caijh.auth.server.admin.controller;

import javax.inject.Inject;

import com.github.caijh.auth.server.admin.request.RoleAddReqBody;
import com.github.caijh.auth.server.admin.service.RoleService;
import com.github.caijh.auth.server.admin.utils.RoleConvertMapper;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Inject
    private RoleService roleService;

    @PutMapping(value = "")
    public void add(@RequestBody @Validated RoleAddReqBody reqBody) {
        Role role = RoleConvertMapper.MAPPER.fromRoleAddReqBody(reqBody);
        this.roleService.save(role);
    }

}
