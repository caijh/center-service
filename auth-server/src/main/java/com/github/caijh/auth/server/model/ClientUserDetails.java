package com.github.caijh.auth.server.model;

import java.util.Collection;

import com.github.caijh.auth.server.entity.User;
import com.github.caijh.auth.server.enums.UserStatusEnum;
import com.github.caijh.framework.core.enums.IndexEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

/**
 * 客户端user详情.
 */
public class ClientUserDetails implements UserDetails {

    private static final long serialVersionUID = -8729427879056048051L;
    private final String appId;
    private final User user;

    public ClientUserDetails(String appId, User user) {
        this.appId = appId;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public String getAppId() {
        return this.appId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(this.user.getRoles())) {
            return emptyList();
        }
        return this.user.getRoles().parallelStream().map(role -> this.newGrantedAuthority(role.getCode())).collect(toList());
    }

    private GrantedAuthority newGrantedAuthority(String granted) {
        return () -> granted;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserStatusEnum.LOCKED != IndexEnum.valueOf(this.user.getStatus(), UserStatusEnum.class);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return UserStatusEnum.NORMAL == IndexEnum.valueOf(this.user.getStatus(), UserStatusEnum.class);
    }

}
