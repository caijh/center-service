package com.github.caijh.auth.server.admin.service.impl;

import javax.annotation.Nonnull;
import javax.transaction.Transactional;

import com.github.caijh.auth.server.admin.repository.UserRepository;
import com.github.caijh.auth.server.admin.service.UserService;
import com.github.caijh.auth.server.entity.User;
import com.github.caijh.framework.core.enums.CommonStatus;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Transactional
    @Override
    public void add(@Nonnull User user) {
        user.setStatus(CommonStatus.IN_USE.getIndex());
        this.save(user);
    }

}
