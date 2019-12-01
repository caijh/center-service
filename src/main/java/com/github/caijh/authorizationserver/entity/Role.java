package com.github.caijh.authorizationserver.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.caijh.commons.base.PersistentObject;
import lombok.Data;

@Entity
@Data
public class Role implements PersistentObject<Long> {

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
