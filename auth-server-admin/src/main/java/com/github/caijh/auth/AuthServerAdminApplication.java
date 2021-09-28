package com.github.caijh.auth;

import com.github.caijh.framework.data.BaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@SpringBootApplication
@EnableJpaRepositories(
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BaseService.class)
        })
public class AuthServerAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerAdminApplication.class, args);
    }

}
