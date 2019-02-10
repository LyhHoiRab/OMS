CREATE DATABASE IF NOT EXISTS `oms` default charset utf8 COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `account` (
  `id`          varchar(32)  NOT NULL,
  `username`    varchar(20)  NOT NULL,
  `password`    varchar(256) NOT NULL,
  `status`      tinyint(1)   NOT NULL,
  `create_time` datetime     NOT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY(`id`),
  INDEX(`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `user` (
  `id`          varchar(32)  NOT NULL,
  `account_id`  varchar(32)  NOT NULL,
  `nickname`    varchar(32)  DEFAULT NULL,
  `head_img`    varchar(256) DEFAULT NULL,
  `sex`         tinyint(1)   NOT NULL,
  `create_time` datetime     NOT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY(`id`),
  INDEX(`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `functions` (
  `id`          varchar(32) NOT NULL,
  `api`         varchar(50) NOT NULL,
  `method`      varchar(8)  NOT NULL,
  `description` text        DEFAULT NULL,
  `allocatable` tinyint(1)  DEFAULT '1',
  `granted`     tinyint(1)  DEFAULT '1',
  `cookie`      tinyint(1)  DEFAULT '1',
  `create_time` datetime    NOT NULL,
  `update_time` datetime    DEFAULT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `menu` (
  `id`          varchar(32) NOT NULL,
  `function_id` varchar(32) DEFAULT NULL,
  `name`        varchar(20) NOT NULL,
  `router_path` text        NOT NULL,
  `icon`        text        DEFAULT NULL,
  `allocatable` tinyint(1)  DEFAULT '1',
  `create_time` datetime    NOT NULL,
  `update_time` datetime    DEFAULT NULL,
  PRIMARY KEY(`id`),
  INDEX(`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `role` (
  `id`          varchar(32)  NOT NULL,
  `name`        varchar(20)  NOT NULL,
  `is_system`   tinyint(1)   DEFAULT '0',
  `status`      tinyint(1)   NOT NULL,
  `create_time` datetime     NOT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `role_account` (
  `role_id`     varchar(32)  NOT NULL,
  `account_id`  varchar(32)  NOT NULL,
  INDEX(`role_id`),
  INDEX(`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `role_function` (
  `role_id`     varchar(32)  NOT NULL,
  `function_id` varchar(32)  NOT NULL,
  INDEX(`role_id`),
  INDEX(`function_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#-------------------------------------------#
CREATE TABLE IF NOT EXISTS `item` (
  `id`          varchar(32)  NOT NULL,
  `name`        varchar(40)  NOT NULL,
  `code`        varchar(20)  NOT NULL,
  `sales`       int(1)       DEFAULT '0',
  `stock`       int(1)       DEFAULT '0',
  `is_check`    tinyint(1)   DEFAULT '0',
  `create_time` datetime     NOT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY(`id`),
  UNIQUE INDEX(`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `product` (
  `id`            varchar(32)  NOT NULL,
  `code`          varchar(20)  NOT NULL,
  `name`          varchar(40)  NOT NULL,
  `number`        varchar(20)  DEFAULT NULL,
  `customized`    tinyint(1)   NOT NULL,
  `type`          tinyint(1)   NOT NULL,
  `texture`       varchar(20)  DEFAULT NULL,
  `specification` varchar(20)  DEFAULT NULL,
  `price`         double(7, 2) DEFAULT '0',
  `description`   text         DEFAULT NULL,
  `effect`        text         DEFAULT NULL,
  `sfda`          text         DEFAULT NULL,
  `certificates`  text         DEFAULT NULL,
  `thumbnail`     text         DEFAULT NULL,
  `create_time`   datetime     NOT NULL,
  `update_time`   datetime     DEFAULT NULL,
  PRIMARY KEY(`id`),
  UNIQUE INDEX(`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `wx_info` (
  `id`          varchar(32)  NOT NULL,
  `account_id`  varchar(32)  DEFAULT NULL,
  `wxno`        varchar(40)  NOT NULL,
  `nickname`    varchar(40)  DEFAULT NULL,
  `phone`       varchar(20)  DEFAULT NULL,
  `remark`      varchar(40)  DEFAULT NULL,
  `image`       text         DEFAULT NULL,
  `qr`          text         DEFAULT NULL,
  `create_time` datetime     NOT NULL,
  `update_time` datetime     DEFAULT NULL,
  PRIMARY KEY(`id`),
  UNIQUE INDEX(`wxno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;