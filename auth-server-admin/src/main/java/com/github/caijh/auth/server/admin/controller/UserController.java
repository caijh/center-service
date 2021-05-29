package com.github.caijh.auth.server.admin.controller;

import javax.inject.Inject;

import com.github.caijh.auth.server.admin.request.UserAddReqBody;
import com.github.caijh.auth.server.admin.service.UserService;
import com.github.caijh.auth.server.admin.utils.UserConvertMapper;
import com.github.caijh.auth.server.entity.User;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Inject
    private UserService userService;

    @PutMapping(value = "")
    public void add(@RequestBody @Validated UserAddReqBody reqBody) {
        User user = UserConvertMapper.INSTANCE.fromUserAddReqBody(reqBody);
        this.userService.add(user);
    }

    @GetMapping(value = "/{id}")
    public User getById(@PathVariable Long id) {
        return this.userService.getOneOrNull(id);
    }

}
