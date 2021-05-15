package com.github.caijh.auth.server.repository;

import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.framework.data.jpa.BaseRepository;

public interface ClientAppRepository extends BaseRepository<ClientApp, String> {

    ClientApp findByClientId(String clientId);

}
