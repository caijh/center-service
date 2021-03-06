package com.github.caijh.auth.server.admin.controller;

import javax.inject.Inject;

import com.github.caijh.auth.server.admin.request.ResourceAddReqBody;
import com.github.caijh.auth.server.admin.request.ResourceUpdateReqBody;
import com.github.caijh.auth.server.admin.service.ResourceService;
import com.github.caijh.auth.server.admin.utils.ResourceConvertMapper;
import com.github.caijh.auth.server.entity.Resource;
import com.github.caijh.framework.core.model.PageReqBody;
import com.github.caijh.framework.data.utils.PageRequestUtils;
import com.github.caijh.framework.web.controller.BaseController;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = "/{id}")
    public void update(@PathVariable Long id, @RequestBody @Validated ResourceUpdateReqBody reqBody) {
        reqBody.setId(id);
        Resource resource = ResourceConvertMapper.INSTANCE.fromResourceUpdateReqBody(reqBody);
        this.resourceService.update(resource);
    }

    @PostMapping(value = "/list")
    public Page<Resource> list(@RequestBody @Validated PageReqBody pageReqBody) {
        return this.resourceService.findAll(PageRequestUtils.newPageRequest(pageReqBody));
    }

}
