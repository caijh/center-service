package com.github.caijh.auth.server.config.oauth;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.caijh.auth.server.entity.ClientAppDetails;
import com.github.caijh.auth.server.repository.ClientAppRepository;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Named("clientDetailsServiceImpl")
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Inject
    private ClientAppRepository clientAppRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        return clientAppRepository.findById(clientId).map(ClientAppDetails::new).orElse(null);
    }

}
