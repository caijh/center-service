package com.github.caijh.authorizationserver.service;

import com.github.caijh.authorizationserver.entity.ClientApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientAppService {

    Page<ClientApp> list(ClientApp clientApp, Pageable pageable);

    ClientApp get(String id);

}
