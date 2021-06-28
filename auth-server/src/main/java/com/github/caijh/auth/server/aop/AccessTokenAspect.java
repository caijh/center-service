package com.github.caijh.auth.server.aop;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.caijh.auth.server.entity.ClientApp;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.model.RoleSelectedResources;
import com.github.caijh.auth.server.service.ResourceService;
import com.github.caijh.framework.data.redis.support.Redis;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessTokenAspect {

    @Inject
    private ResourceService resourceService;
    @Inject
    private Redis redis;

    @Pointcut("execution(public * org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.postAccessToken(..))")
    public void postAccessToken() {
        // Do nothing.
    }

    @AfterReturning(pointcut = "postAccessToken()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        JSONObject body = JSON.parseObject(JSON.toJSONString(result)).getJSONObject("body");
        JSONObject additionalInformation = body.getJSONObject("additionalInformation");
        String appId = additionalInformation.getString(ClientApp.APP_ID);
        Long userId = additionalInformation.getLong(ClientApp.USER_ID);
        this.cacheUserRoleSelectedResources(appId, userId, body.getLong("expiration"));
    }

    private void cacheUserRoleSelectedResources(String appId, Long userId, Long expiration) {
        List<RoleSelectedResources> roleSelectedResourcesList = this.resourceService.findRoleSelectedResources(appId, userId);
        String key = "USER:AUTH:" + userId;
        this.redis.getRedisTemplate().delete(key);
        roleSelectedResourcesList.forEach(roleSelectedResources -> {
            Role role = roleSelectedResources.getRole();
            this.redis.getRedisTemplate().opsForHash().put(key, role.getCode(), roleSelectedResources.getSelectedResources());
        });
        this.redis.getRedisTemplate().expireAt(key, new Date(expiration));
    }

}
