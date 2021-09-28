package com.github.caijh.auth.server.admin.service;

import com.github.caijh.auth.server.entity.User;
import com.github.caijh.framework.data.BaseService;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserService extends BaseService<User, Long> {

    void add(User user);

}
