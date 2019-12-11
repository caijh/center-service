package com.github.caijh.auth.server.config.oauth;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.caijh.auth.server.entity.ClientAppDetails;
import com.github.caijh.auth.server.repository.ClientAppRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;

@Named("clientDetailsServiceImpl")
@CacheConfig(cacheNames = "ClientDetailsServiceImpl")
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Inject
    private ClientAppRepository clientAppRepository;

    @Cacheable
    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        return clientAppRepository.findById(clientId).map(ClientAppDetails::new).orElseThrow(() -> new NoSuchClientException("No client with requested id: " + clientId));
    }

}
