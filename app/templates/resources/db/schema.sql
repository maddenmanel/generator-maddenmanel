/*
  Mysql 规范
*/

-- ----------------------------
-- 库的字符集必须使用utf8
-- ----------------------------
CREATE DATABASE test CHARACTER SET utf8;
USE test;

-- ----------------------------
-- 有无符号:unsigned必须填；
-- 主键自增:AUTO_INCREMENT必填;
-- 表的引擎:ENGINE=Innodb必填;
-- 自增起值:AUTO_INCREMENT=xx必填;
-- 表字符集:CHARSET=utf8必填;
-- 表的注释:COMMENT="用户表"必填;
-- 字段注释:COMMENT="自增id"必填;
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`       bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `name`     varchar(100)        NOT NULL COMMENT '姓名',
    `age`      int(11)             NOT NULL COMMENT '年龄',
    `addr`     varchar(200) DEFAULT NULL COMMENT '地址',
    `created`  datetime            NOT NULL COMMENT '创建时间',
    `modified` datetime            NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT = '用户表';

INSERT INTO t_user (name, age, addr, created, modified)
VALUES ('Jack', 20, 'BJ', now(), now());
INSERT INTO t_user (name, age, addr, created, modified)
VALUES ('Jones', 18, 'SH', now(), now());

-- ----------------------------
-- 增加字段时COMMENT必须填写
-- ----------------------------
ALTER TABLE t_user
    ADD COLUMN code VARCHAR(20) NULL COMMENT '用户编码';

-- ----------------------------
-- 唯一索引用uq_开头;
-- 索引名称必须以idx开头;
-- ----------------------------
ALTER TABLE t_user
    ADD INDEX uq_name (code) USING BTREE COMMENT '用户编码唯一索引';
ALTER TABLE t_user
    ADD INDEX idx_name (name) USING BTREE COMMENT '用户名称索引';
