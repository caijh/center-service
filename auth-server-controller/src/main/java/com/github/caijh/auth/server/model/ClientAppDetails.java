package com.github.caijh.auth.server.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.caijh.auth.entity.ClientApp;
import com.github.caijh.auth.enums.ClientTypeEnum;
import com.github.caijh.commons.base.enums.IndexEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

public class ClientAppDetails implements ClientDetails {

    private final ClientApp clientApp;

    public ClientAppDetails(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    @Override
    public String getClientId() {
        return clientApp.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return clientApp.getResourceIds();
    }

    @Override
    public boolean isSecretRequired() {
        return ClientTypeEnum.CONFIDENTIAL == IndexEnum.valueOf(clientApp.getClientType(), ClientTypeEnum.class);
    }

    @Override
    public String getClientSecret() {
        return clientApp.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return !clientApp.getScope().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return clientApp.getScope();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return clientApp.getAuthorizedGrantTypes();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return clientApp.getRedirectUris();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return clientApp.getAuthorities()
                        .parallelStream()
                        .map(authority -> (GrantedAuthority) () -> authority)
                        .collect(Collectors.toList());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return clientApp.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return clientApp.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return !clientApp.getAutoApproveScope().isEmpty() && clientApp.getAutoApproveScope().contains(scope);
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return clientApp.getAdditionalInformation();
    }

}
