package com.github.caijh.auth.server.service.impl;

import javax.inject.Inject;

import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.repository.ClientAppRepository;
import com.github.caijh.auth.server.service.ClientAppService;
import org.springframework.stereotype.Service;

@Service
public class ClientAppServiceImpl implements ClientAppService {

    @Inject
    private ClientAppRepository clientAppRepository;


    @Override
    public ClientApp get(String id) {
        return clientAppRepository.findByClientId(id);
    }

}
