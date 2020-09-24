package com.github.caijh.auth.server.aop;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.caijh.auth.server.entity.Role;
import com.github.caijh.auth.server.service.ResourceService;
import com.github.caijh.auth.server.vo.RoleSelectedResources;
import com.github.caijh.framework.redis.Redis;
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
        String appId = additionalInformation.getString("appId");
        Long userId = additionalInformation.getLong("userId");
        cacheUserRoleSelectedResources(appId, userId, body.getLong("expiration"));
    }

    private void cacheUserRoleSelectedResources(String appId, Long userId, Long expiration) {
        List<RoleSelectedResources> roleSelectedResourcesList = resourceService.findRoleSelectedResources(appId, userId);
        String key = "APP:" + appId + ":USER:" + userId + ":AUTH";
        redis.getRedisTemplate().delete(key);
        roleSelectedResourcesList.forEach(roleSelectedResources -> {
            Role role = roleSelectedResources.getRole();
            redis.getRedisTemplate().opsForHash().put(key, role.getCode(), roleSelectedResources.getSelectedResources());
        });
        redis.getRedisTemplate().expireAt(key, new Date(expiration));
    }

}
