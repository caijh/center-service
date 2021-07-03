package com.github.caijh.auth.server.model;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.enums.ClientTypeEnum;
import com.github.caijh.framework.core.enums.IndexEnum;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * 客户端app详情.
 */
public class ClientAppDetails implements ClientDetails {

    private static final long serialVersionUID = -3791816944128555367L;
    private final ClientApp clientApp;

    public ClientAppDetails(ClientApp clientApp) {
        this.clientApp = clientApp;
    }

    @Override
    public String getClientId() {
        return this.clientApp.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return this.clientApp.getResourceIds();
    }

    @Override
    public boolean isSecretRequired() {
        return ClientTypeEnum.CONFIDENTIAL == IndexEnum.valueOf(this.clientApp.getClientType(), ClientTypeEnum.class);
    }

    @Override
    public String getClientSecret() {
        return this.clientApp.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return !this.clientApp.getScope().isEmpty();
    }

    @Override
    public Set<String> getScope() {
        return this.clientApp.getScope();
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.clientApp.getAuthorizedGrantTypes();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.clientApp.getRedirectUris();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(this.clientApp.getAuthorities()).orElse(Sets.newHashSet())
                       .parallelStream()
                       .map(authority -> (GrantedAuthority) (() -> authority))
                       .collect(Collectors.toList());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.clientApp.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.clientApp.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return !this.clientApp.getAutoApproveScope().isEmpty() && this.clientApp.getAutoApproveScope().contains(scope);
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.clientApp.getAdditionalInformation();
    }

}
