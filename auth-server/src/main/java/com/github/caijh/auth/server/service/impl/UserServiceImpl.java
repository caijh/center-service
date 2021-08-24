package com.github.caijh.auth.server.service.impl;

import com.github.caijh.auth.server.entity.User;
import com.github.caijh.auth.server.service.UserService;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
}
