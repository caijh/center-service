-- clientdb.client_app definition

CREATE TABLE `client_app` (
  `client_id` varchar(255) NOT NULL COMMENT '客户端id',
  `access_token_validity_seconds` int DEFAULT '0' COMMENT 'access token的有效期，单位秒',
  `additional_information` json DEFAULT NULL COMMENT '客户端额外信息',
  `authorities` json DEFAULT NULL COMMENT '客户端的授权',
  `authorized_grant_types` json DEFAULT NULL COMMENT '客户端的授权方式',
  `client_name` varchar(255) DEFAULT NULL COMMENT '客户端名称',
  `client_secret` varchar(255) DEFAULT NULL COMMENT '客户端类型:0-公开，1-私有',
  `client_type` int DEFAULT NULL,
  `redirect_uris` json DEFAULT NULL COMMENT '重定向地址集合',
  `refresh_token_validity_seconds` int DEFAULT NULL COMMENT 'refresh token的有效期',
  `resource_ids` json DEFAULT NULL COMMENT '可以访问的资源id集合',
  `scope` json DEFAULT NULL COMMENT '作用域',
  `auto_approve_scope` json DEFAULT NULL COMMENT '自动作用域',
  PRIMARY KEY (`client_id`) USING BTREE,
  UNIQUE KEY `uk_client_name` (`client_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='客户端表';


-- clientdb.resource definition

CREATE TABLE `resource` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `actions` json DEFAULT NULL COMMENT '操作集合',
  `name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='资源表';


-- clientdb.`user` definition

CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '0-正常，1-锁定',
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';


-- clientdb.`role` definition

CREATE TABLE `role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(255) NOT NULL COMMENT '客户端id',
  `code` varchar(255) NOT NULL COMMENT '角色标识码',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_app_id_code` (`app_id`,`code`) USING BTREE,
  CONSTRAINT `role_FK_app_id` FOREIGN KEY (`app_id`) REFERENCES `client_app` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';


-- clientdb.role_resource definition

CREATE TABLE `role_resource` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `action_names` json DEFAULT NULL COMMENT '该资源的操作集合',
  `resource_id` bigint unsigned NOT NULL COMMENT '资源id',
  `role_id` bigint unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_resource_id` (`resource_id`) USING BTREE,
  CONSTRAINT `role_resource_FK_resource_id` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`),
  CONSTRAINT `role_resource_FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色与资源关联表';


-- clientdb.user_role definition

CREATE TABLE `user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint unsigned DEFAULT NULL COMMENT '角色id',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_id` (`role_id`) USING BTREE,
  KEY `idx_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `user_role_FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户与角色关联表';


-- clientdb.men_item definition

CREATE TABLE `men_item` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) NOT NULL COMMENT '菜单名称、标题',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `action` json DEFAULT NULL COMMENT '菜单的action',
  `parent_id` bigint unsigned DEFAULT '0' COMMENT '上一级id',
  `app_id` varchar(255) NOT NULL COMMENT '所属应用',
  PRIMARY KEY (`id`),
  KEY `men_item_FK_client_app_id` (`app_id`),
  CONSTRAINT `men_item_FK_client_app_id` FOREIGN KEY (`app_id`) REFERENCES `client_app` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='应用菜单';
