package com.github.caijh.auth.server.repository;

import com.github.caijh.auth.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
