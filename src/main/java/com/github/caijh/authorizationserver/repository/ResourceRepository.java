package com.github.caijh.authorizationserver.repository;

import com.github.caijh.authorizationserver.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
