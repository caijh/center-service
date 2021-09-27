package com.github.caijh.auth.server.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.github.caijh.auth.server.model.SelectedResourceAction;
import com.github.caijh.framework.core.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

/**
 * 应用菜单项
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuItem extends BaseEntity<Long> {

    private static final long serialVersionUID = -7589424738091846724L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单名称.
     */
    private String name;

    /**
     * 图标.
     */
    private String icon;

    /**
     * 上一级菜单.
     */
    private Long parentId;

    /**
     * 应用id.
     */
    private String appId;

    /**
     * 对应资源的操作.
     */
    @Type(type = "json")
    private SelectedResourceAction resourceAction;

    /**
     * 子菜单项.
     */
    @Transient
    private Set<MenuItem> children;

}
