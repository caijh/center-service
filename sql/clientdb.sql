SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `client_app`;
CREATE TABLE `client_app`
(
    `client_id`                      varchar(255) NOT NULL COMMENT '客户端id',
    `access_token_validity_seconds`  int(11)      NULL DEFAULT 0 COMMENT 'access token的有效期，单位秒',
    `additional_information`         json         NULL COMMENT '客户端额外信息',
    `authorities`                    json         NULL COMMENT '客户端的授权',
    `authorized_grant_types`         json         NULL COMMENT '客户端的授权方式',
    `client_name`                    varchar(255) NULL DEFAULT NULL COMMENT '客户端名称',
    `client_secret`                  varchar(255) NULL DEFAULT NULL COMMENT '客户端类型:0-公开，1-私有',
    `client_type`                    int(11)      NULL DEFAULT NULL,
    `redirect_uris`                  json         NULL COMMENT '重定向地址集合',
    `refresh_token_validity_seconds` int(11)      NULL DEFAULT NULL COMMENT 'refresh token的有效期',
    `resource_ids`                   json         NULL COMMENT '可以访问的资源id集合',
    `scope`                          json         NULL COMMENT '作用域',
    PRIMARY KEY (`client_id`) USING BTREE,
    UNIQUE INDEX `uk_client_name` (`client_name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 COMMENT = '客户端表'
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`
(
    `id`      bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `actions` json                NULL COMMENT '操作集合',
    `name`    varchar(255)        NULL DEFAULT NULL COMMENT '资源名称',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_name` (`name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4 COMMENT = '资源表'
  ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`     bigint(20)   NOT NULL AUTO_INCREMENT,
    `app_id` varchar(255) NOT NULL COMMENT '客户端id',
    `code`   varchar(255) NOT NULL COMMENT '角色标识码',
    `name`   varchar(255) NOT NULL COMMENT '角色名称',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_app_id_code` (`app_id`, `code`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT,
    `action_names` json       NULL COMMENT '该资源的操作集合',
    `resource_id`  bigint(20) NOT NULL COMMENT '资源id',
    `role_id`      bigint(20) NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_role_id` (`role_id`) USING BTREE,
    INDEX `idx_resource_id` (`resource_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       bigint(20)   NOT NULL AUTO_INCREMENT,
    `password` varchar(255) NULL DEFAULT NULL,
    `status`   int(11)      NULL DEFAULT NULL COMMENT '0-正常，1-锁定',
    `username` varchar(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_username` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4 COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`      bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20)          NULL DEFAULT NULL COMMENT '角色id',
    `user_id` bigint(20)          NULL DEFAULT NULL COMMENT '用户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_role_id` (`role_id`) USING BTREE,
    INDEX `idx_user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
