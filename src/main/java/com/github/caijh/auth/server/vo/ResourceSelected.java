package com.github.caijh.auth.server.vo;

import java.io.Serializable;
import java.util.Set;

import com.github.caijh.auth.server.entity.Resource;
import lombok.Data;

@Data
public class ResourceSelected implements Serializable {

    private Resource resource;

    private Set<String> actionNames;

}
