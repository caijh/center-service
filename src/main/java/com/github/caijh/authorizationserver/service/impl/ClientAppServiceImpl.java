package com.github.caijh.authorizationserver.service.impl;

import java.util.NoSuchElementException;

import com.github.caijh.authorizationserver.entity.ClientApp;
import com.github.caijh.authorizationserver.repository.ClientAppRepository;
import com.github.caijh.authorizationserver.service.ClientAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientAppServiceImpl implements ClientAppService {

    @Autowired
    private ClientAppRepository clientAppRepository;

    @Override
    public Page<ClientApp> list(ClientApp clientApp, Pageable pageable) {
        return clientAppRepository.findAll(Example.of(clientApp), pageable);
    }

    @Override
    public ClientApp get(String id) {
        return clientAppRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

}
