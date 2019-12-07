package com.github.caijh.auth.server.vo;

import java.util.Set;

import com.github.caijh.auth.server.entity.Resource;
import lombok.Data;

@Data
public class ResourceSelected {

    private Resource resource;

    private Set<String> actionNames;

}
