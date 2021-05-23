package com.github.caijh.auth.server.admin.utils;

import com.github.caijh.auth.server.admin.repository.UserAddReqBody;
import com.github.caijh.auth.server.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvertMapper {

    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    User fromUserAddReqBody(UserAddReqBody reqBody);

}
