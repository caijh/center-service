package com.github.caijh.auth.server;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableFeignClients
public class FeignAutoConfiguration {

}
