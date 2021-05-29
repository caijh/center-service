package com.github.caijh.auth.server.admin.controller;

import javax.inject.Inject;

import com.github.caijh.auth.server.admin.request.ResourceAddReqBody;
import com.github.caijh.auth.server.admin.service.ResourceService;
import com.github.caijh.auth.server.admin.utils.ResourceConvertMapper;
import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController extends BaseController {

    @Inject
    private ResourceService resourceService;

    @PutMapping(value = "")
    public void add(@RequestBody @Validated ResourceAddReqBody reqBody) {
        Resource resource = ResourceConvertMapper.INSTANCE.fromResourceAddReqBody(reqBody);
        this.resourceService.add(resource);
    }

}
