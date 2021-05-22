package com.github.caijh.auth.server.admin.service.impl;

import java.util.NoSuchElementException;
import javax.inject.Inject;

import com.github.caijh.auth.server.admin.exception.ClientAppNotFoundException;
import com.github.caijh.auth.server.admin.repository.ClientAppRepository;
import com.github.caijh.auth.server.admin.service.ClientAppService;
import com.github.caijh.auth.server.entity.ClientApp;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientAppServiceImpl implements ClientAppService {

    @Inject
    private ClientAppRepository clientAppRepository;

    @Override
    public ClientApp add(ClientApp clientApp) {
        ClientApp app = this.clientAppRepository.findByClientId(clientApp.getClientId());
        if (app != null) {
            throw new ClientAppNotFoundException();
        }
        return this.clientAppRepository.save(clientApp);
    }

    @Override
    public Page<ClientApp> list(ClientApp clientApp, Pageable pageable) {
        return this.clientAppRepository.findAll(Example.of(clientApp), pageable);
    }

    @Override
    public ClientApp get(String id) {
        return this.clientAppRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void update(ClientApp clientApp) {
        this.clientAppRepository.findById(clientApp.getId())
                                .ifPresent(e -> {
                                    e.setClientSecret(clientApp.getClientSecret());
                                    this.clientAppRepository.save(e);
                                });
    }

    @Override
    public void delete(String clientId) {
        this.clientAppRepository.deleteById(clientId);
    }

}
