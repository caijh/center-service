package com.github.caijh.authorizationserver.controller;

import java.util.HashSet;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;

import com.github.caijh.authorizationserver.entity.ClientApp;
import com.github.caijh.authorizationserver.entity.ClientAppDetails;
import com.github.caijh.authorizationserver.enums.ClientTypeEnum;
import com.github.caijh.authorizationserver.request.ClientAppListReqBody;
import com.github.caijh.authorizationserver.request.ClientRegistryReqBody;
import com.github.caijh.authorizationserver.service.ClientAppService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientAppController {

    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 2592000; // 30 days

    @Inject
    private ClientRegistrationService clientRegistrationService;
    @Inject
    private ClientAppService clientAppService;

    private ClientApp constructClientApp(@Valid ClientRegistryReqBody reqBody) {
        ClientApp clientApp = new ClientApp();
        clientApp.setClientName(reqBody.getClientName());
        clientApp.setClientType(ClientTypeEnum.valueOf(reqBody.getClientType()).getIndex());
        clientApp.setRedirectUris(new HashSet<>());
        clientApp.addRedirectUri(reqBody.getRedirectUri());
        clientApp.setClientId(reqBody.getClientId());
        if (clientApp.getClientId() == null) {
            clientApp.setClientId(UUID.randomUUID().toString());
        }
        clientApp.setClientSecret(UUID.randomUUID().toString());
        clientApp.setAccessTokenValiditySeconds(reqBody.getAccessTokenValiditySeconds());
        clientApp.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
        clientApp.setAuthorizedGrantTypes(new HashSet<>());
        clientApp.addAuthorizedGrantType("authorization_code")
                 .addAuthorizedGrantType("implicit")
                 .addAuthorizedGrantType("password")
                 .addAuthorizedGrantType("client_credentials")
                 .addAuthorizedGrantType("refresh_token");
        clientApp.setScope(new HashSet<>());
        clientApp.addScope(reqBody.getClientName());
        clientApp.setResourceIds(new HashSet<>());
        return clientApp;
    }

    @GetMapping(value = "/client/{id}")
    public ClientApp detail(@PathVariable String id) {
        return this.clientAppService.get(id);
    }

    @PostMapping(value = "/client")
    public void add(@RequestBody ClientRegistryReqBody reqBody) {
        ClientApp clientApp = constructClientApp(reqBody);
        clientRegistrationService.addClientDetails(new ClientAppDetails(clientApp));
    }

    @PostMapping(value = "/client/{id}")
    public void update(@PathVariable String id, @RequestBody ClientRegistryReqBody reqBody) {
        ClientApp clientApp = constructClientApp(reqBody);
        clientRegistrationService.updateClientDetails(new ClientAppDetails(clientApp));
    }

    @DeleteMapping(value = "/client/{id}")
    public void delete(@PathVariable(value = "id") String clientId) {
        this.clientRegistrationService.removeClientDetails(clientId);
    }


    @PostMapping(value = "/clients")
    public Page<ClientApp> clients(@RequestBody ClientAppListReqBody reqBody) {
        ClientApp clientApp = new ClientApp();
        clientApp.setClientName(reqBody.getClientName());
        PageRequest pageRequest = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        return clientAppService.list(clientApp, pageRequest);
    }

}
