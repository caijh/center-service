package com.github.caijh.authorizationserver.config;

import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.caijh.authorizationserver.config.jwt.AdditionalClaimsTokenEnhancer;
import com.github.caijh.authorizationserver.config.oauth.TokenKeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    @Named("clientDetailsServiceImpl")
    private ClientDetailsService clientDetailsService;

    @Inject
    private AdditionalClaimsTokenEnhancer tokenEnhancer;

    @Inject
    private TokenStore tokenStore;

    @Inject
    private JwtAccessTokenConverter accessTokenConverter;

    /**
     * JwtAccessTokenConverter.
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(TokenKeyProperties tokenKeyProperties) {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(tokenKeyProperties.getPrivateKey());
        converter.setVerifierKey(tokenKeyProperties.getPublicKey());
        return converter;
    }

    @Bean
    public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
        security.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        TokenEnhancerChain chain = new TokenEnhancerChain();
        chain.setTokenEnhancers(Arrays.asList(tokenEnhancer, accessTokenConverter));
        endpoints.authenticationManager(authenticationManager);
        endpoints.accessTokenConverter(accessTokenConverter);
        endpoints.tokenEnhancer(chain);
        endpoints.tokenStore(tokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

}
