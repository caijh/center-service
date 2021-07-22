package com.github.caijh.auth.server.controller;

import java.util.List;
import java.util.Set;
import javax.inject.Inject;

import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.auth.server.model.SelectedResource;
import com.github.caijh.auth.server.request.UrlCheckReqBody;
import com.github.caijh.framework.core.model.R;
import com.github.caijh.framework.data.redis.support.Redis;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.data.util.CastUtils;
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
public class ResourceController extends BaseController {

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
    public ResponseEntity<R<Void>> isAllowed(@RequestBody UrlCheckReqBody reqBody) {
        String key = "APP:" + reqBody.getClientId() + ":USER:" + reqBody.getUserId() + ":AUTH";
        List<Object> objects = this.redis.getRedisTemplate().opsForHash().multiGet(key, reqBody.getRoleCodes());
        for (Object obj : objects) {
            if (obj == null || !Set.class.isAssignableFrom(obj.getClass())) {
                continue;
            }
            Set<SelectedResource> selectedResources = CastUtils.cast(obj);
            for (SelectedResource selectedResource : selectedResources) {
                for (Resource.Action allowedAction : selectedResource.getAllowedActions()) {
                    if (this.isAllowed(allowedAction, reqBody.getUrl(), reqBody.getMethod())) {
                        return ResponseEntity.ok(R.of(OK.getReasonPhrase()));
                    }
                }
            }
        }
        return ResponseEntity.ok(R.of(FORBIDDEN.getReasonPhrase()));
    }

    private boolean isAllowed(Resource.Action action, String url, String method) {
        if (action.getMethods() != null && !action.getMethods().contains(method)) {
            return false;
        }
        return this.pathMatcher.match(action.getUrl(), url);
    }

}
