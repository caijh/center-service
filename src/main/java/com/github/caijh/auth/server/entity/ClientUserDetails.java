package com.github.caijh.auth.server.entity;

import java.util.Collection;

import com.github.caijh.auth.server.enums.UserStatusEnum;
import com.github.caijh.commons.base.IndexEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

public class ClientUserDetails implements UserDetails {

    private User user;

    public ClientUserDetails(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (CollectionUtils.isEmpty(this.user.getRoles())) {
            return emptyList();
        }
        return this.user.getRoles().parallelStream().map(role -> (GrantedAuthority) role::getCode).collect(toList());
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
