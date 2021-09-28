package com.github.caijh.auth.server.admin.service;

import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.framework.data.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientAppService extends BaseService<ClientApp, String> {

    ClientApp add(ClientApp clientApp);

    Page<ClientApp> list(ClientApp clientApp, Pageable pageable);

    ClientApp get(String id);

    void update(ClientApp clientApp);

    void delete(String clientId);

}
