package com.github.caijh.auth.server.repository;

import com.github.caijh.auth.server.entity.User;
import com.github.caijh.framework.data.jpa.BaseRepository;

public interface UserRepository extends BaseRepository<User, Long> {

    User findByUsername(String username);

}
