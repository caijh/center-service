package com.github.caijh.auth.server.admin.service.impl;

import com.github.caijh.auth.server.admin.repository.UserRepository;
import com.github.caijh.auth.server.admin.service.UserService;
import com.github.caijh.auth.server.entity.User;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserRepository, User, Long> implements UserService {
}
