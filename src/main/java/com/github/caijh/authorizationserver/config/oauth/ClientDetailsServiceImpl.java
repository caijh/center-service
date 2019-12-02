package com.github.caijh.authorizationserver.config.oauth;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.caijh.authorizationserver.entity.ClientAppDetails;
import com.github.caijh.authorizationserver.repository.ClientAppRepository;
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
