package com.github.caijh.authorizationserver.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.caijh.commons.base.PersistentObject;
import lombok.Data;
import org.hibernate.annotations.Type;

@Entity
@Data
public class RoleResource implements PersistentObject<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long roleId;
    private Long resourceId;
    /**
     * 该角色对此资源可做操作.
     */
    @Type(type = "json")
    private List<String> actionNames;

}
