package com.github.caijh.auth.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.caijh.framework.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appId;

    private String code;

    private String name;

    @Override
    public Long getId() {
        return id;
    }

}
