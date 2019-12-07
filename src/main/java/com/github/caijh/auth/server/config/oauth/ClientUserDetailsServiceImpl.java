package com.github.caijh.auth.server.config.oauth;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.entity.ClientUserDetails;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.entity.User;
import com.github.caijh.auth.server.repository.ClientAppRepository;
import com.github.caijh.auth.server.repository.RoleRepository;
import com.github.caijh.auth.server.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * ClientUserDetailsServiceImpl.
 *
 * @author caijunhui
 **/
@Named(value = "clientUserDetailsService")
public class ClientUserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private ClientAppRepository clientAppRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = ((org.springframework.security.core.userdetails.User) (SecurityContextHolder.getContext()
                                                                                                      .getAuthentication()
                                                                                                      .getPrincipal())).getUsername();
        ClientApp clientApp = clientAppRepository.findByClientId(clientId);
        if (clientApp == null) {
            throw new UsernameNotFoundException("app not found");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        List<Role> roles = roleRepository.findByAppIdAndUserId(clientApp.getId(), user.getId());
        user.setRoles(roles);

        return new ClientUserDetails(clientId, user);
    }

}
