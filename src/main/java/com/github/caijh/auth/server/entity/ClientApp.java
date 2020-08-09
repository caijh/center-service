package com.github.caijh.auth.server.entity;

import java.util.Map;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.github.caijh.auth.server.enums.AuthorizedGrantTypeEnum;
import com.github.caijh.commons.base.PersistentObject;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Data
public class ClientApp implements PersistentObject<String> {

    @Id
    private String clientId;
    @Column(unique = true)
    private String clientName;
    private Integer clientType;
    private String clientSecret;
    /**
     * 支持的认证类型.
     *
     * @see AuthorizedGrantTypeEnum
     */
    @Type(type = "json")
    private Set<String> authorizedGrantTypes;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    @Type(type = "json")
    private Set<String> redirectUris;
    @Type(type = "json")
    private Set<String> scope;
    @Type(type = "json")
    private Set<String> autoApproveScope;
    @Type(type = "json")
    private Set<String> resourceIds;
    @Type(type = "json")
    private Set<String> authorities;

    @Type(type = "json")
    private Map<String, Object> additionalInformation;

    @Override
    public String getId() {
        return this.clientId;
    }

    public ClientApp addAuthorizedGrantType(String grantType) {
        this.authorizedGrantTypes.add(grantType);
        return this;
    }

    public void addRedirectUri(String redirectUri) {
        this.redirectUris.add(redirectUri);
    }

    public void addScope(String scope) {
        this.scope.add(scope);
    }

    public void addResourceId(String resourceId) {
        this.resourceIds.add(resourceId);
    }

    public void addAuthority(String authority) {
        this.authorities.add(authority);
    }

}
