package com.github.caijh.auth.server.admin.utils;

import com.github.caijh.auth.server.admin.request.RoleAddReqBody;
import com.github.caijh.auth.server.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvertMapper {

    RoleConvertMapper MAPPER = Mappers.getMapper(RoleConvertMapper.class);

    @Mapping(target = "id", ignore = true)
    Role fromRoleAddReqBody(RoleAddReqBody reqBody);

}
