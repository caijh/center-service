package com.github.caijh.auth.server.admin.utils;

import com.github.caijh.auth.server.admin.request.ResourceAddReqBody;
import com.github.caijh.auth.server.admin.request.ResourceUpdateReqBody;
import com.github.caijh.auth.server.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResourceConvertMapper {

    ResourceConvertMapper INSTANCE = Mappers.getMapper(ResourceConvertMapper.class);

    @Mapping(target = "id", ignore = true)
    Resource fromResourceAddReqBody(ResourceAddReqBody reqBody);

    Resource fromResourceUpdateReqBody(ResourceUpdateReqBody reqBody);

}
