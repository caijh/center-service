package com.github.caijh.auth.server.admin.service;

import com.github.caijh.auth.server.entity.ClientApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientAppService {

    ClientApp add(ClientApp clientApp);

    Page<ClientApp> list(ClientApp clientApp, Pageable pageable);

    ClientApp get(String id);

    void update(ClientApp clientApp);

    void delete(String clientId);

}
