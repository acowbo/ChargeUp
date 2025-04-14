-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user`
(
    `id`              bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`        varchar(50)  NOT NULL COMMENT '用户名',
    `password`        varchar(255) NOT NULL COMMENT '加密密码',
    `salt`            varchar(50)  NOT NULL COMMENT '密码盐',
    `email`           varchar(100) DEFAULT NULL COMMENT '邮箱',
    `status`          tinyint(4)   DEFAULT '1' COMMENT '用户状态(1-正常,0-禁用)',
    `create_time`     datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_login_time` datetime     DEFAULT NULL COMMENT '最后登录时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';


-- ----------------------------
-- Table structure for tb_bill
-- ----------------------------
CREATE TABLE IF NOT EXISTS `tb_bill`
(
    `id`          bigint(20)     NOT NULL AUTO_INCREMENT COMMENT '账单主键id',
    `user_id`     bigint(20)     NOT NULL DEFAULT '1' COMMENT '用户id',
    `category_id` bigint(20)     NOT NULL COMMENT '账单类别id',
    `amount`      decimal(10, 2) NOT NULL COMMENT '金额',
    `name`        varchar(100)   NOT NULL COMMENT '名称',
    `create_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `bill_time`   datetime       NOT NULL COMMENT '账单时间',
    `is_deleted`  tinyint(1)     NOT NULL DEFAULT '0' COMMENT '是否删除',
    `create_by`   bigint(20)              DEFAULT NULL COMMENT '上传人',
    `update_by`   bigint(20)              DEFAULT NULL COMMENT '更新人',
    `in_bill`     tinyint(1)              DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_bill_category` (`category_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1904485399791689731
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户账单表';


-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
CREATE TABLE IF NOT EXISTS `tb_category`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '分类ID，主键',
    `name`        varchar(50) NOT NULL COMMENT '分类名称',
    `type`        tinyint(1)  NOT NULL DEFAULT '0' COMMENT '分类类型，父级：0-支出，1-收入',
    `user_id`     bigint(1)   NOT NULL DEFAULT '1' COMMENT '默认类型为1，否则为用户Id',
    `parent_id`   bigint(20)  NOT NULL DEFAULT '-1' COMMENT '父级id',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint(1)  NOT NULL DEFAULT '0' COMMENT '逻辑删除标识',
    `create_by`   bigint(20)           DEFAULT NULL COMMENT '上传人',
    `update_by`   bigint(20)           DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1904735797410643971
  DEFAULT CHARSET = utf8mb4 COMMENT ='账单分类表';

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` (`id`, `name`, `type`, `user_id`, `parent_id`, `create_time`, `update_time`, `is_deleted`,
                           `create_by`, `update_by`)
VALUES (1904485336466087937, '日用百货', 0, 1, -1, '2025-03-25 18:47:42', '2025-03-25 18:47:42', 0, NULL, NULL);
INSERT INTO `tb_category` (`id`, `name`, `type`, `user_id`, `parent_id`, `create_time`, `update_time`, `is_deleted`,
                           `create_by`, `update_by`)
VALUES (1904510525331533826, '美容美发', 0, 1, -1, '2025-03-25 20:27:48', '2025-03-25 20:27:48', 0, NULL, NULL);
INSERT INTO `tb_category` (`id`, `name`, `type`, `user_id`, `parent_id`, `create_time`, `update_time`, `is_deleted`,
                           `create_by`, `update_by`)
VALUES (1904520876645175297, '日常工资', 1, 1, -1, '2025-03-25 21:08:56', '2025-03-25 21:08:56', 0, NULL, NULL);
INSERT INTO `tb_category` (`id`, `name`, `type`, `user_id`, `parent_id`, `create_time`, `update_time`, `is_deleted`,
                           `create_by`, `update_by`)
VALUES (1904735797410643970, '日常购物', 0, 1, -1, '2025-03-26 11:22:57', '2025-03-26 11:22:57', 0, NULL, NULL);

