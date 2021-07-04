package com.github.caijh.auth.server.controller;

import javax.inject.Inject;

import com.github.caijh.auth.server.entity.User;
import com.github.caijh.auth.server.service.UserService;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    @Inject
    private UserService userService;

    @GetMapping(value = "/user/{id}")
    public User detail(@PathVariable(value = "id") Long userId) {
        return this.userService.getOneOrNull(userId);
    }

}
