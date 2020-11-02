package com.github.caijh.auth.server.controller;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;

import com.alibaba.fastjson.JSONObject;
import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.auth.server.model.SelectedResource;
import com.github.caijh.auth.server.request.UrlCheckReqBody;
import com.github.caijh.framework.redis.Redis;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;

/**
 * 资源控制器.
 */
@RestController
public class ResourceController {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Inject
    private Redis redis;

    /**
     * 判断用户是否对应的url访问权限.
     *
     * @param reqBody UrlCheckReqBody
     * @return string "OK" if user has permission to visit url.
     */
    @PostMapping("/url/check")
    public ResponseEntity<JSONObject> isAllowed(@RequestBody UrlCheckReqBody reqBody) {
        String key = "APP:" + reqBody.getClientId() + ":USER:" + reqBody.getUserId() + ":AUTH";
        List<Object> objects = redis.getRedisTemplate().opsForHash().multiGet(key, reqBody.getRoleCodes());
        for (Object obj : objects) {
            if (obj == null || !Set.class.isAssignableFrom(obj.getClass())) {
                continue;
            }
            @SuppressWarnings("unchecked")
            Set<SelectedResource> selectedResources = (Set<SelectedResource>) obj;
            for (SelectedResource selectedResource : selectedResources) {
                for (Resource.Action allowedAction : selectedResource.getAllowedActions()) {
                    if (isAllowed(allowedAction, reqBody.getUrl(), reqBody.getMethod())) {
                        return ResponseEntity.ok(new JSONObject().fluentPut("message", OK.getReasonPhrase()));
                    }
                }
            }
        }
        return ResponseEntity.ok(new JSONObject().fluentPut("message", FORBIDDEN.getReasonPhrase()));
    }

    private boolean isAllowed(Resource.Action action, String url, String method) {
        if (!action.getMethod().equalsIgnoreCase(method)) {
            return false;
        }
        return pathMatcher.match(action.getUrl(), url);
    }

}
