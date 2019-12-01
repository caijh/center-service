package com.github.caijh.authorizationserver.repository;

import com.github.caijh.authorizationserver.entity.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAppRepository extends JpaRepository<ClientApp, String> {

    ClientApp findByClientId(String clientId);

}
