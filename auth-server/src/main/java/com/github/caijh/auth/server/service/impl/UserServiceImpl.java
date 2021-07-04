package com.github.caijh.auth.server.service.impl;

import com.github.caijh.auth.server.entity.User;
import com.github.caijh.auth.server.repository.UserRepository;
import com.github.caijh.auth.server.service.UserService;
import com.github.caijh.framework.data.BaseServiceImpl;

public class UserServiceImpl extends BaseServiceImpl<UserRepository, User, Long> implements UserService {
}
