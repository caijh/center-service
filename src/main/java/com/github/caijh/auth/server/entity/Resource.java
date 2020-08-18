package com.github.caijh.auth.server.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.caijh.framework.orm.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Resource extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Type(type = "json")
    private List<Action> actions;

    @Override
    public Long getId() {
        return this.id;
    }

    @Data
    public static class Action implements Serializable {

        private String name;
        private String description;
        private String url;
        private String method;

    }

}
