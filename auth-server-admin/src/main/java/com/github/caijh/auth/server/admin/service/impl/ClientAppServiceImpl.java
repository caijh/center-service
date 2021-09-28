package com.github.caijh.auth.server.admin.service.impl;

import java.util.NoSuchElementException;

import com.github.caijh.auth.server.admin.exception.ClientAppNotFoundException;
import com.github.caijh.auth.server.admin.repository.ClientAppRepository;
import com.github.caijh.auth.server.admin.service.ClientAppService;
import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.framework.data.BaseServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientAppServiceImpl extends BaseServiceImpl<ClientApp, String> implements ClientAppService {


    @Override
    public ClientApp add(ClientApp clientApp) {
        ClientApp app = this.<ClientAppRepository>getRepository().findByClientId(clientApp.getClientId());
        if (app != null) {
            throw new ClientAppNotFoundException();
        }
        return this.getRepository().save(clientApp);
    }

    @Override
    public Page<ClientApp> list(ClientApp clientApp, Pageable pageable) {
        return this.getRepository().findAll(Example.of(clientApp), pageable);
    }

    @Override
    public ClientApp get(String id) {
        return this.getRepository().findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void update(ClientApp clientApp) {
        this.getRepository().findById(clientApp.getId())
                                .ifPresent(e -> {
                                    e.setClientSecret(clientApp.getClientSecret());
                                    this.getRepository().save(e);
                                });
    }

    @Override
    public void delete(String clientId) {
        this.getRepository().deleteById(clientId);
    }

}
