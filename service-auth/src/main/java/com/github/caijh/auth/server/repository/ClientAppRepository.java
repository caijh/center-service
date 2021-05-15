package com.github.caijh.auth.server.repository;

import com.github.caijh.auth.server.entity.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAppRepository extends JpaRepository<ClientApp, String> {

    ClientApp findByClientId(String clientId);

}
