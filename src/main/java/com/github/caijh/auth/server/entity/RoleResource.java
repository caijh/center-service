package com.github.caijh.auth.server.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.github.caijh.commons.base.PersistentObject;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Data
public class RoleResource implements PersistentObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Role role;

    @OneToOne
    private Resource resource;

    /**
     * 该角色对此资源可做操作.
     */
    @Type(type = "json")
    private Set<String> actionNames;

}
