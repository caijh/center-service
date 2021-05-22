package com.github.caijh.auth.server.admin.controller;

import java.util.HashSet;
import java.util.UUID;
import javax.inject.Inject;
import javax.validation.Valid;

import com.github.caijh.auth.server.admin.request.ClientAppListReqBody;
import com.github.caijh.auth.server.admin.request.ClientRegistryReqBody;
import com.github.caijh.auth.server.admin.service.ClientAppService;
import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.enums.ClientTypeEnum;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端控制器.
 *
 * @author caijunhui
 */
@RestController
public class ClientAppController extends BaseController {

    private static final int REFRESH_TOKEN_VALIDITY_SECONDS = 2592000; // 30 days

    @Inject
    private ClientAppService clientAppService;

    private ClientApp constructClientApp(ClientRegistryReqBody reqBody) {
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
        clientApp.setRefreshTokenValiditySeconds(ClientAppController.REFRESH_TOKEN_VALIDITY_SECONDS);
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

    /**
     * 查看client app 详情.
     *
     * @param id client id
     * @return object of ClientApp
     */
    @GetMapping(value = "/client/{id}")
    public ClientApp detail(@PathVariable String id) {
        return this.clientAppService.get(id);
    }

    /**
     * 创建新client app.
     *
     * @param reqBody ClientRegistryReqBody
     */
    @PutMapping(value = "/client")
    public void add(@RequestBody @Valid ClientRegistryReqBody reqBody) {
        ClientApp clientApp = this.constructClientApp(reqBody);
        this.clientAppService.add(clientApp);
    }

    /**
     * 更新 client app.
     *
     * @param reqBody ClientRegistryReqBody
     */
    @PostMapping(value = "/client")
    public void update(@RequestBody @Valid ClientRegistryReqBody reqBody) {
        ClientApp clientApp = this.constructClientApp(reqBody);
        this.clientAppService.update(clientApp);
    }

    /**
     * 删除 client app.
     *
     * @param clientId client id
     */
    @DeleteMapping(value = "/client/{id}")
    public void delete(@PathVariable(value = "id") String clientId) {
        this.clientAppService.delete(clientId);
    }

    /**
     * 列表 client apps.
     *
     * @param reqBody ClientAppListReqBody
     * @return one page of client app
     */
    @PostMapping(value = "/clients")
    public Page<ClientApp> clients(@RequestBody ClientAppListReqBody reqBody) {
        ClientApp clientApp = new ClientApp();
        clientApp.setClientName(reqBody.getClientName());
        PageRequest pageRequest = PageRequest.of(reqBody.getPageNo(), reqBody.getPageSize());
        return this.clientAppService.list(clientApp, pageRequest);
    }

}
