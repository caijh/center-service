package com.github.caijh.auth.server.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.github.caijh.framework.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private Integer status;

    @Transient
    private transient List<Role> roles;

}

