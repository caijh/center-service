package com.github.caijh.auth.server.admin.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;

import com.github.caijh.auth.server.admin.request.RoleAddReqBody;
import com.github.caijh.auth.server.admin.request.RoleUpdateReqBody;
import com.github.caijh.auth.server.admin.request.RoleUserReqBody;
import com.github.caijh.auth.server.admin.service.RoleService;
import com.github.caijh.auth.server.admin.utils.RoleConvertMapper;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.UserRole;
import com.github.caijh.framework.core.model.PageReqBody;
import com.github.caijh.framework.data.utils.PageRequestUtils;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        this.roleService.add(role);
    }

    @GetMapping(value = "/{id}")
    public Role get(@PathVariable(name = "id") Long id) {
        return this.roleService.getOneOrNull(id);
    }

    @PostMapping(value = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated RoleUpdateReqBody reqBody) {
        Role role = RoleConvertMapper.MAPPER.fromRoleUpdateReqBody(reqBody);
        role.setId(id);
        this.roleService.update(role);
    }

    @PostMapping(value = "/list")
    public Page<Role> page(@RequestBody @Validated PageReqBody pageReqBody) {
        return this.roleService.findAll(PageRequestUtils.newPageRequest(pageReqBody));
    }

    @PutMapping(value = "/{id}/user")
    public void addUser(@PathVariable Long id, @RequestBody @Validated RoleUserReqBody reqBody) {
        List<UserRole> userRoles = reqBody.getUserIds().stream().map(e -> new UserRole().setRoleId(id).setUserId(e)).collect(Collectors.toList());
        this.roleService.addUser(userRoles);
    }

    @DeleteMapping(value = "/{id}/user")
    public void deleteUser(@PathVariable Long id, @RequestBody @Validated RoleUserReqBody reqBody) {
        List<UserRole> userRoles = reqBody.getUserIds().stream().map(e -> new UserRole().setRoleId(id).setUserId(e)).collect(Collectors.toList());
        this.roleService.deleteUser(userRoles);
    }

}
