package com.github.caijh.auth.server.aop;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.caijh.auth.server.service.ResourceService;
import com.github.caijh.auth.server.vo.ResourceSelected;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessTokenAspect {

    @Inject
    private ResourceService resourceService;
    @Inject
    private RedisTemplate<String, Object> redisTemplate;

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
        prepareAuth(appId, userId, body.getLong("expiration"));
    }

    private void prepareAuth(String appId, Long userId, Long expiration) {
        List<ResourceSelected> selectedResources = resourceService.findResourceSelected(appId, userId);
        Set<String> codes = new HashSet<>();
        selectedResources.forEach(e -> {
            String name = e.getResource().getName();
            e.getActionNames().forEach(action -> codes.add(name + ":" + action));
        });

        String key = "AUTH:" + appId + ":" + userId;
        redisTemplate.delete(key);
        redisTemplate.opsForSet().add(key, codes);
        redisTemplate.expireAt(key, new Date(expiration));
    }

}
