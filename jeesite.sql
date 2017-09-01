/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : lexue_cms

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-09-01 15:25:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `push_message`
-- ----------------------------
DROP TABLE IF EXISTS `push_message`;
CREATE TABLE `push_message` (
  `id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `rule_id` bigint(255) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `business_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `version_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `target_type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `target_value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `push_time` bigint(255) DEFAULT NULL,
  `expire_time` bigint(255) DEFAULT NULL,
  `store_offline` bit(1) DEFAULT NULL,
  `status` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `create_time` bigint(50) DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `transparent` int(11) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `transmission` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `extraInfo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `client` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `push_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `push_status` varchar(11) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of push_message
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_blob_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_calendars`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_cron_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `CRON_EXPRESSION` varchar(200) COLLATE utf8_bin NOT NULL,
  `TIME_ZONE_ID` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('getSchedulerFactoryBean', 'TASK_dd3300ed0e264c4fbe27642cbc06f5cb', 'DEFAULT', '*/5 * * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('getSchedulerFactoryBean', 'TASK_f333236e261a47c1adbd1212d2f26c04', 'DEFAULT', '*/5 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for `qrtz_fired_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `ENTRY_ID` varchar(95) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_job_details`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) COLLATE utf8_bin NOT NULL,
  `IS_DURABLE` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_NONCONCURRENT` varchar(1) COLLATE utf8_bin NOT NULL,
  `IS_UPDATE_DATA` varchar(1) COLLATE utf8_bin NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) COLLATE utf8_bin NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('getSchedulerFactoryBean', 'TASK_dd3300ed0e264c4fbe27642cbc06f5cb', 'DEFAULT', null, 'com.lexue.base.util.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372001E636F6D2E6C657875652E626173652E646F6D61696E2E5363686564756C65000000000000000102000A4C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000B6465736372697074696F6E71007E00094C0002696471007E00094C00086A6F6247726F757071007E00094C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000673746174757371007E00097870740008746573745461736B7074000D2A2F35202A202A202A202A203F74000373646674002064643333303065643065323634633466626532373634326362633036663563627400046A6F62737400046A6F62737400057465737432740000740001307800);
INSERT INTO `qrtz_job_details` VALUES ('getSchedulerFactoryBean', 'TASK_f333236e261a47c1adbd1212d2f26c04', 'DEFAULT', null, 'com.lexue.base.util.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372001E636F6D2E6C657875652E626173652E646F6D61696E2E5363686564756C65000000000000000102000A4C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000B6465736372697074696F6E71007E00094C0002696471007E00094C00086A6F6247726F757071007E00094C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000673746174757371007E00097870740008746573745461736B7074000D2A2F35202A202A202A202A203F74000331323374002066333333323336653236316134376331616462643132313264326632366330347400036A6F627400036A6F627400047465737474000431323233740001307800);

-- ----------------------------
-- Table structure for `qrtz_locks`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `LOCK_NAME` varchar(40) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('getSchedulerFactoryBean', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for `qrtz_paused_trigger_grps`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_scheduler_state`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `INSTANCE_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simple_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_simprop_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `STR_PROP_1` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_2` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `STR_PROP_3` varchar(512) COLLATE utf8_bin DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for `qrtz_triggers`
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) COLLATE utf8_bin NOT NULL,
  `TRIGGER_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `TRIGGER_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_NAME` varchar(200) COLLATE utf8_bin NOT NULL,
  `JOB_GROUP` varchar(200) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) COLLATE utf8_bin NOT NULL,
  `TRIGGER_TYPE` varchar(8) COLLATE utf8_bin NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('getSchedulerFactoryBean', 'TASK_dd3300ed0e264c4fbe27642cbc06f5cb', 'DEFAULT', 'TASK_dd3300ed0e264c4fbe27642cbc06f5cb', 'DEFAULT', null, '1501655720000', '-1', '5', 'PAUSED', 'CRON', '1501655716000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372001E636F6D2E6C657875652E626173652E646F6D61696E2E5363686564756C65000000000000000102000A4C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000B6465736372697074696F6E71007E00094C0002696471007E00094C00086A6F6247726F757071007E00094C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000673746174757371007E00097870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000015DA1A7D0A07874000D2A2F35202A202A202A202A203F74000373646674002064643333303065643065323634633466626532373634326362633036663563627400046A6F62737400046A6F62737400057465737432740000740001317800);
INSERT INTO `qrtz_triggers` VALUES ('getSchedulerFactoryBean', 'TASK_f333236e261a47c1adbd1212d2f26c04', 'DEFAULT', 'TASK_f333236e261a47c1adbd1212d2f26c04', 'DEFAULT', null, '1501655690000', '-1', '5', 'PAUSED', 'CRON', '1501655687000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372001E636F6D2E6C657875652E626173652E646F6D61696E2E5363686564756C65000000000000000102000A4C0009636C6173734E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000B6465736372697074696F6E71007E00094C0002696471007E00094C00086A6F6247726F757071007E00094C00076A6F624E616D6571007E00094C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000673746174757371007E00097870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000015DA1A75F587874000D2A2F35202A202A202A202A203F74000331323374002066333333323336653236316134376331616462643132313264326632366330347400036A6F627400036A6F627400047465737474000431323233740001317800);

-- ----------------------------
-- Table structure for `schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `job_group` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `job_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `class_name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `method_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `cron_expression` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(200) COLLATE utf8_bin DEFAULT ' ',
  `create_time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `params` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 1` (`job_group`,`job_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('dd3300ed0e264c4fbe27642cbc06f5cb', 'jobs', 'jobs', 'testTask', 'test2', '*/5 * * * * ?', 'sdf', '2017-08-02 14:35:16', '1', '');
INSERT INTO `schedule_job` VALUES ('f333236e261a47c1adbd1212d2f26c04', 'job', 'job', 'testTask', 'test', '*/5 * * * * ?', '123', '2017-08-02 14:34:47', '1', '1223');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `value` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '数据值',
  `label` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '标签名',
  `type` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64) COLLATE utf8_bin DEFAULT '0' COMMENT '父级编号',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('04fe7ebc32b6411bb22d132a99916449', '2', '磅', 'company', '宽高单位', '20', '0', null, '2017-09-01 11:33:12', null, '2017-09-01 11:33:12', '', '0');
INSERT INTO `sys_dict` VALUES ('082106dbac1c4965937ae7c699b8f580', '2', '图片', 'frameType', '帧条目类型', '20', '0', null, '2017-09-01 11:34:37', null, '2017-09-01 11:34:37', '', '0');
INSERT INTO `sys_dict` VALUES ('184d486397a844ccb0d509834f103e80', '2', '启用', 'status', '状态', '10', '0', null, '2017-09-01 11:32:27', null, '2017-09-01 11:32:27', '', '0');
INSERT INTO `sys_dict` VALUES ('297b3c8e6a494ca584a98bf7d45fd580', '2', '视频透传', 'transparent', '透传类型', '20', '0', null, '2017-09-01 11:36:13', null, '2017-09-01 11:36:13', '', '0');
INSERT INTO `sys_dict` VALUES ('2adef99a9c7b459ca1eb10fadbb3907e', '1', '禁用', 'status', '状态', '20', '0', null, '2017-09-01 11:32:37', null, '2017-09-01 11:32:37', '', '0');
INSERT INTO `sys_dict` VALUES ('2f4f463a0abb45258ac69c425f3ef19b', '1', '像素', 'company', '宽高单位', '10', '0', null, '2017-09-01 11:33:05', null, '2017-09-01 11:33:05', '', '0');
INSERT INTO `sys_dict` VALUES ('2f6b0892ac8444f8a77ed2b0a79b82fb', '4098', '中考志愿', 'BusinessType', '推送客户端', '20', '0', null, '2017-09-01 11:37:57', null, '2017-09-01 11:37:57', '', '0');
INSERT INTO `sys_dict` VALUES ('38ee0bb6d05e45c2b2c449c8e9b8176c', '5', '文字横排', 'frameType', '帧条目类型', '50', '0', null, '2017-09-01 11:34:55', null, '2017-09-01 11:34:55', '', '0');
INSERT INTO `sys_dict` VALUES ('3b150ca6adf54ad3abad615198be967d', 'Android', '安卓', 'plantforms', '推送平台', '20', '0', null, '2017-09-01 11:40:16', null, '2017-09-01 11:40:16', '', '0');
INSERT INTO `sys_dict` VALUES ('4a72a40d833c44e9bfef9cad17fd24ca', 'IOS', '苹果', 'plantforms', '推送平台', '10', '0', null, '2017-09-01 11:39:36', null, '2017-09-01 11:39:36', '', '0');
INSERT INTO `sys_dict` VALUES ('5131bb5cb5704a01993fd4d4ab7f14fb', '1048833', '中考VIP', 'BusinessType', '推送客户端', '60', '0', null, '2017-09-01 11:39:01', null, '2017-09-01 11:39:01', '', '0');
INSERT INTO `sys_dict` VALUES ('51c5c9cc4e21419daded88a1e86ebbe1', 'USERID', '用户', 'TargetType', '推送平台', '30', '0', null, '2017-09-01 11:41:34', null, '2017-09-01 11:41:34', '', '0');
INSERT INTO `sys_dict` VALUES ('5b148edc869744ca8ac3f5409c0dbf55', '4097', '中考', 'BusinessType', '推送客户端', '10', '0', null, '2017-09-01 11:37:21', null, '2017-09-01 11:37:21', '', '0');
INSERT INTO `sys_dict` VALUES ('5ed4fe8cc7e34262af408cc407bf9735', '2097409', '高考VIP', 'BusinessType', '推送客户端', '70', '0', null, '2017-09-01 11:39:11', null, '2017-09-01 11:39:11', '', '0');
INSERT INTO `sys_dict` VALUES ('638955f4039242448446233bb4fe6648', '1', '全部', 'itemPickType', '帧选取类型', '10', '0', null, '2017-09-01 11:33:45', null, '2017-09-01 11:33:45', '', '0');
INSERT INTO `sys_dict` VALUES ('69daf658f0b24f0bb7fe25d3926798df', '12289', '少儿英语游戏', 'BusinessType', '推送客户端', '50', '0', null, '2017-09-01 11:38:40', null, '2017-09-01 11:38:40', '', '0');
INSERT INTO `sys_dict` VALUES ('74ea09befba149099b6d151416dd49b2', '4', '特殊筛选', 'itemPickType', '帧选取类型', '40', '0', null, '2017-09-01 11:34:02', null, '2017-09-01 11:34:02', '', '0');
INSERT INTO `sys_dict` VALUES ('89a77c9b9f1845b1a688007ab3efaa8c', '8194', '高考志愿', 'BusinessType', '推送客户端', '40', '0', null, '2017-09-01 11:38:25', null, '2017-09-01 11:38:25', '', '0');
INSERT INTO `sys_dict` VALUES ('93c2824252e94ad9ac46038b7f8bbee5', '1', '文字竖排', 'frameType', '帧条目类型', '10', '0', null, '2017-09-01 11:34:30', null, '2017-09-01 11:34:30', '', '0');
INSERT INTO `sys_dict` VALUES ('ad4743e7237143749ea80f30187a0793', 'PLANTFORM', '平台', 'TargetType', '推送平台', '20', '0', null, '2017-09-01 11:41:26', null, '2017-09-01 11:41:26', '', '0');
INSERT INTO `sys_dict` VALUES ('b6d6a08839f145baaf4676196b9afae6', '3', '百分比', 'company', '宽高单位', '30', '0', null, '2017-09-01 11:33:19', null, '2017-09-01 11:33:19', '', '0');
INSERT INTO `sys_dict` VALUES ('bb545b84ea1c4df2a4b63942ed0527e7', '10', '多图帧', 'frameType', '帧条目类型', '60', '0', null, '2017-09-01 11:35:01', null, '2017-09-01 11:35:01', '', '0');
INSERT INTO `sys_dict` VALUES ('bfefe93c0135490a99671c76fcd7d81f', 'CLIENT', '客户端', 'TargetType', '推送平台', '50', '0', null, '2017-09-01 11:42:01', null, '2017-09-01 11:42:01', '', '0');
INSERT INTO `sys_dict` VALUES ('c1c0e0289d7b4fde9311f847e279d25b', '3', '优先级', 'itemPickType', '帧选取类型', '30', '0', null, '2017-09-01 11:33:56', null, '2017-09-01 11:33:56', '', '0');
INSERT INTO `sys_dict` VALUES ('c49236540ad242478208230e2db30a9b', 'ALL', '全部', 'TargetType', '推送平台', '10', '0', null, '2017-09-01 11:41:14', null, '2017-09-01 11:41:14', '', '0');
INSERT INTO `sys_dict` VALUES ('c902b345c96241639ac2f5528b8c7ae4', '4', '动画', 'frameType', '帧条目类型', '40', '0', null, '2017-09-01 11:34:48', null, '2017-09-01 11:34:48', '', '0');
INSERT INTO `sys_dict` VALUES ('ce38d8b277e94999ba2c0189cf8bfe6f', 'TAG', '标签', 'TargetType', '推送平台', '40', '0', null, '2017-09-01 11:41:43', null, '2017-09-01 11:41:43', '', '0');
INSERT INTO `sys_dict` VALUES ('dd7a5252a76d40b49ef6adca3ef1e4fa', '3', '流媒体', 'frameType', '帧条目类型', '30', '0', null, '2017-09-01 11:34:42', null, '2017-09-01 11:34:42', '', '0');
INSERT INTO `sys_dict` VALUES ('ea0265b08fa643bb8e78f6848828eff6', '8193', '高考', 'BusinessType', '推送客户端', '30', '0', null, '2017-09-01 11:38:11', null, '2017-09-01 11:38:11', '', '0');
INSERT INTO `sys_dict` VALUES ('ef46eda1c8b9470f8b7a9d55750ae7a7', '2', '随机', 'itemPickType', '帧选取类型', '20', '0', null, '2017-09-01 11:33:51', null, '2017-09-01 11:33:51', '', '0');
INSERT INTO `sys_dict` VALUES ('fc1d10bec516482d96782783c2bf947e', '1', '无透传', 'transparent', '透传类型', '10', '0', null, '2017-09-01 11:36:08', null, '2017-09-01 11:36:08', '', '0');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(255) COLLATE utf8_bin NOT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `method` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `operName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `requestIp` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `params` longtext COLLATE utf8_bin,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `del_flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('00465023f26545b3badeae787841cea4', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:37:21', null, null, null);
INSERT INTO `sys_log` VALUES ('0056f3b1240140869ba31ca6a37d5816', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:17:10', null, null, null);
INSERT INTO `sys_log` VALUES ('00e0ed8ed6b14048a46b496b6e514646', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:45:40', null, null, null);
INSERT INTO `sys_log` VALUES ('00e7eb8b4add478b8cf4177b78dd22fd', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:02:44', null, null, null);
INSERT INTO `sys_log` VALUES ('013f66abcb7a4482a7dc20b1313c1f8f', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x226164343737356133393531313465396139323236323238313633616266643036223B7B7D3B, null, null, '2017-09-01 14:22:42', null, null, null);
INSERT INTO `sys_log` VALUES ('01ebce227a2048d5b5d35c0161f0a080', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:33:39', null, null, null);
INSERT INTO `sys_log` VALUES ('01f5f57e1e6c437cbd70b163f1ebc11d', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:30:24', null, null, null);
INSERT INTO `sys_log` VALUES ('027768d1b1d44351bd9c7f93e266d1be', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:17:49', null, null, null);
INSERT INTO `sys_log` VALUES ('02c266329ea446c0b0d7999a13aa35b4', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:33:25', null, null, null);
INSERT INTO `sys_log` VALUES ('058cd69bc3f94c92ae78b0fc0cff4622', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:46:06', null, null, null);
INSERT INTO `sys_log` VALUES ('05a9d262d0d94f5d85bb9966e15b4d6c', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F61645265732F73617665222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E4BFAEE694B9222C22706172656E744964223A226530623666623064356234353431356661393730336463343461383130373038222C227065726D697373696F6E223A227379733A61647265733A73617665222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:24:44', null, null, null);
INSERT INTO `sys_log` VALUES ('05e0c40a4f5e4d8cbe0f05487da9c4be', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 13:56:53', null, null, null);
INSERT INTO `sys_log` VALUES ('06478ea1f0f247088b906f88956f5605', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:45:17', null, null, null);
INSERT INTO `sys_log` VALUES ('071806a43dfd462ab8fca6fff44360ee', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:33', null, null, null);
INSERT INTO `sys_log` VALUES ('07d7983e7cca4485b39fc30292690b80', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E69DA1E79BAEE7B1BBE59E8B222C226964223A22222C226C6162656C223A22E59BBEE78987222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A226672616D6554797065222C2276616C7565223A2232227D3B, null, null, '2017-09-01 11:34:37', null, null, null);
INSERT INTO `sys_log` VALUES ('07e2d5e66fbd409986cea1d6c93fb2f0', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F6164426F782F616464222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E6B7BBE58AA0222C22706172656E744964223A223135222C227065726D697373696F6E223A227379733A6164626F783A616464222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:21:44', null, null, null);
INSERT INTO `sys_log` VALUES ('08442c5d2171432f9f7a19f6a0b7e013', '消息推送管理-消息推送列表', 'com.lexue.sso.web.controller.PushMessageController.index()', null, 'http://localhost:8080/push/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 12:30:23', null, null, null);
INSERT INTO `sys_log` VALUES ('088c3003bb424fd4bafb07fd72214e33', '角色管理-角色列表', 'com.lexue.sso.web.controller.RoleController.index()', null, 'http://localhost:8080/role/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:26:45', null, null, null);
INSERT INTO `sys_log` VALUES ('08a9d79929574d8083e5ee4697d4b085', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:45:04', null, null, null);
INSERT INTO `sys_log` VALUES ('08f3d4a5364a49bca24e38baf0e2bb10', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:10:17', null, null, null);
INSERT INTO `sys_log` VALUES ('0914917399cb42b1818466667cf2e459', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:31:00', null, null, null);
INSERT INTO `sys_log` VALUES ('0919efdf1405456ba44e04915e2c1b6d', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:46:41', null, null, null);
INSERT INTO `sys_log` VALUES ('096abae5aebe404c9d27bd6e7b4ee989', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:29:37', null, null, null);
INSERT INTO `sys_log` VALUES ('098181aa58904defba751c9e862d6d09', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27494F53277D222C2264656661756C7446696C746572223A302C226663617073223A312C226672616D654361706163697479223A302C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A302C226C6F676963223A312C226E6F7465223A2262616E6E6572E9AB98E88083222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083222C2274706C6964223A307D3B, null, null, '2017-09-01 11:46:06', null, null, null);
INSERT INTO `sys_log` VALUES ('0ae70f63c26244659914da703735420d', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:44:43', null, null, null);
INSERT INTO `sys_log` VALUES ('0b3e4fe12e5b477f8d84a04b0c42f8ef', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:42:12', null, null, null);
INSERT INTO `sys_log` VALUES ('0c4a255e4ef442888f7818ffbabfa61b', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F77782F6C697374222C226964223A223131222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E5BEAEE4BFA1E7BEA4E7AEA1E79086222C22706172656E744964223A2232222C227065726D697373696F6E223A227379733A77783A6C697374222C2272656D61726B73223A22222C22736F7274223A307D3B, null, null, '2017-09-01 11:15:43', null, null, null);
INSERT INTO `sys_log` VALUES ('0c67da459d9f47ffaa6eed7cc8df7076', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:24', null, null, null);
INSERT INTO `sys_log` VALUES ('0df359b88fab499fb7e8020c1bd21245', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223235366434303261386461613465333462636261636234653233613966383234223B313B, null, null, '2017-09-01 11:54:56', null, null, null);
INSERT INTO `sys_log` VALUES ('0ed444ce3ba34ed28a7c67d17eaed7d4', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223264366136666538316365633436356638393231313036383663653665356431223B323B, null, null, '2017-09-01 14:21:52', null, null, null);
INSERT INTO `sys_log` VALUES ('0f0e488e6b3b42f7b110c8f8167e47ab', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A322C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031333A35373A3532222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A312C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031333A35373A3531222C22737461747573223A312C227469746C65223A2262616E6E6572E88BB9E69E9C32222C2274706C4964223A312C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 13:57:57', null, null, null);
INSERT INTO `sys_log` VALUES ('0f427d47b5004e5d8ae7ee84e31f130a', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:17:16', null, null, null);
INSERT INTO `sys_log` VALUES ('0f44f2859e7f4901bbc4d07339bbc7cd', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x226164343737356133393531313465396139323236323238313633616266643036223B7B7D3B, null, null, '2017-09-01 14:24:57', null, null, null);
INSERT INTO `sys_log` VALUES ('10516b4f6496466a94792972754c7c90', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:59', null, null, null);
INSERT INTO `sys_log` VALUES ('1136751db0d541bc9e62ff54d7bdeec9', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A22222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A22E9AB98E88083E8A786E9A291E5898DE5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 11:42:27', null, null, null);
INSERT INTO `sys_log` VALUES ('126796eb28654df587238556234019f9', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:55:21', null, null, null);
INSERT INTO `sys_log` VALUES ('126a443ec8214f4781c866523ce4ad4d', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:25:11', null, null, null);
INSERT INTO `sys_log` VALUES ('12ca14a6dabb4842a3da371f4128666c', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:19:32', null, null, null);
INSERT INTO `sys_log` VALUES ('1337a65079834b408281402ecded72c6', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A226333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:05:26', null, null, null);
INSERT INTO `sys_log` VALUES ('134a088f4d50472ab382108bad5d4c96', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:06', null, null, null);
INSERT INTO `sys_log` VALUES ('135ca6e94595466084cd9f75c0530cef', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:20:59', null, null, null);
INSERT INTO `sys_log` VALUES ('13c3fe0b07314e6da2921301d1a4e2ac', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:20:02', null, null, null);
INSERT INTO `sys_log` VALUES ('145bed7f7b414f24a1ce9810a7c4986d', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:30:48', null, null, null);
INSERT INTO `sys_log` VALUES ('15235f326a1e4312a77bd5707ac50aa9', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:35:01', null, null, null);
INSERT INTO `sys_log` VALUES ('1667f0227c114deda2b0c2e8181620ae', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:30:46', null, null, null);
INSERT INTO `sys_log` VALUES ('167e28e0b65d45248776ad13da182d37', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:16:45', null, null, null);
INSERT INTO `sys_log` VALUES ('1690f7840b594f5bac1a663a3fe50b5d', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:27', null, null, null);
INSERT INTO `sys_log` VALUES ('16abfdc6d4bd406585344e489a56e0d7', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27416E64726F6964277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A322C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A22E5AE89E58D93222C227072696F72697479223A302C22737461747573223A312C227469746C65223A2262616E6E6572E9AB98E88083E5AE89E58D93222C2274706C6964223A337D3B, null, null, '2017-09-01 14:22:13', null, null, null);
INSERT INTO `sys_log` VALUES ('1722509b22204d63abd44adacbdd87c4', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:22:37', null, null, null);
INSERT INTO `sys_log` VALUES ('17efac52e5214a0ba282d176eb333643', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:30:25', null, null, null);
INSERT INTO `sys_log` VALUES ('190023a0335a411ebc1081624a7328d0', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:30:41', null, null, null);
INSERT INTO `sys_log` VALUES ('194aa6b95c4b49e0916a18fdce8bca9b', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:34:37', null, null, null);
INSERT INTO `sys_log` VALUES ('1bc16d1daa8e450cb7a984ee2723034e', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:10:12', null, null, null);
INSERT INTO `sys_log` VALUES ('1bf7c6754b104612be928f7f2e6e6a66', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:17:17', null, null, null);
INSERT INTO `sys_log` VALUES ('1c04f500fa7f4366b03c932fed077d37', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:25', null, null, null);
INSERT INTO `sys_log` VALUES ('1c425874316d4ccca81ee15b2fa247cb', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B313B, null, null, '2017-09-01 14:33:43', null, null, null);
INSERT INTO `sys_log` VALUES ('1c76bff746ea41d4b3e615220bb180d6', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27416E64726F6964277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A322C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A22E5AE89E58D93222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083E5AE89E58D93222C2274706C6964223A337D3B, null, null, '2017-09-01 14:24:59', null, null, null);
INSERT INTO `sys_log` VALUES ('1cd54dbb106841be85c0fe519283cae1', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:45:26', null, null, null);
INSERT INTO `sys_log` VALUES ('1d5cb9bd5cc94cc8aae084656819f15f', '角色管理-角色列表', 'com.lexue.sso.web.controller.RoleController.index()', null, 'http://localhost:8080/role/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:26:37', null, null, null);
INSERT INTO `sys_log` VALUES ('1da4dc42050248bbaa663ee6f2357ded', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E69DA1E79BAEE7B1BBE59E8B222C226964223A22222C226C6162656C223A22E58AA8E794BB222C2272656D61726B73223A22222C22736F7274223A34302C2274797065223A226672616D6554797065222C2276616C7565223A2234227D3B, null, null, '2017-09-01 11:34:48', null, null, null);
INSERT INTO `sys_log` VALUES ('1dcc1bd720d44ede91e155fcb35368cc', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:17:22', null, null, null);
INSERT INTO `sys_log` VALUES ('1e804bf1cb124cf480832e77b8a38636', '消息推送管理-消息推送列表', 'com.lexue.sso.web.controller.PushMessageController.index()', null, 'http://localhost:8080/push/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:57:28', null, null, null);
INSERT INTO `sys_log` VALUES ('1e902f2371904e228424728581162206', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A312C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031333A35373A3333222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A312C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031333A35373A3332222C22737461747573223A312C227469746C65223A2262616E6E6572E88BB9E69E9C222C2274706C4964223A312C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 13:57:36', null, null, null);
INSERT INTO `sys_log` VALUES ('1eea1d627f254cec946d8e0a0256f47c', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:41:26', null, null, null);
INSERT INTO `sys_log` VALUES ('21fec938b58049d1b22288e5836256ed', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:30:28', null, null, null);
INSERT INTO `sys_log` VALUES ('220aff9c09bf44ceaf7d19f2207c108c', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27416E64726F6964277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A322C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A22E5AE89E58D93222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083E5AE89E58D93222C2274706C6964223A337D3B, null, null, '2017-09-01 14:22:58', null, null, null);
INSERT INTO `sys_log` VALUES ('23ea6c536973453a82f3e6d4918ec995', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A312C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031313A35323A3332222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A312C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031313A35323A3331222C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E8808331222C2274706C4964223A312C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 11:52:43', null, null, null);
INSERT INTO `sys_log` VALUES ('241c23d8f3a4458c973c5d7f6c162346', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D2C687474703A2F2F7777772E6C657875652E636F6D222C226964223A2263336430353262656538393934353662626235613733646531376434386339622C6333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A70672C687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572312C62616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:17:16', null, null, null);
INSERT INTO `sys_log` VALUES ('24b088ec87094a0284b0f41f57e5c142', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 13:57:57', null, null, null);
INSERT INTO `sys_log` VALUES ('24e01b1e03064a2498ed229f6e1c771b', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:10', null, null, null);
INSERT INTO `sys_log` VALUES ('24ffa5a724b443318b0f1e59fabccfaa', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27416E64726F6964277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A322C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A22E5AE89E58D93222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083E5AE89E58D93222C2274706C6964223A337D3B, null, null, '2017-09-01 14:22:44', null, null, null);
INSERT INTO `sys_log` VALUES ('253212df30b24ee8b7ea942aeb5cf804', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5AEA2E688B7E7ABAF222C226964223A22222C226C6162656C223A22E5B091E584BFE88BB1E8AFADE6B8B8E6888F222C2272656D61726B73223A22222C22736F7274223A35302C2274797065223A22427573696E65737354797065222C2276616C7565223A223132323839227D3B, null, null, '2017-09-01 11:38:40', null, null, null);
INSERT INTO `sys_log` VALUES ('25a2c08456d84a8ba1bd1ca7e418c1cc', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:30:26', null, null, null);
INSERT INTO `sys_log` VALUES ('26fbfb02f480461eb6a28461fe2da7de', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:24:20', null, null, null);
INSERT INTO `sys_log` VALUES ('2782939d05b84a65853e20f7fb022ddd', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:14', null, null, null);
INSERT INTO `sys_log` VALUES ('2803ebf9cf8e4739aec30ba608bc10d1', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:36', null, null, null);
INSERT INTO `sys_log` VALUES ('281b1c056e754ab09cf4d9cd3d5c12c1', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F616454706C2F64656C657465222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E588A0E999A4222C22706172656E744964223A226661333861346166373830393466333362353539373830613632616136383039222C227065726D697373696F6E223A227379733A616474706C3A64656C657465222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:26:17', null, null, null);
INSERT INTO `sys_log` VALUES ('2903831ea5484c28a0eff15851b65cc3', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E69DA1E79BAEE7B1BBE59E8B222C226964223A22222C226C6162656C223A22E69687E5AD97E7AB96E68E92222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A226672616D6554797065222C2276616C7565223A2231227D3B, null, null, '2017-09-01 11:34:30', null, null, null);
INSERT INTO `sys_log` VALUES ('2a1e2b91bd1c4801a32db5a8659f0148', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:15:10', null, null, null);
INSERT INTO `sys_log` VALUES ('2ad9dac1b35f41128f729ebc6dc2f52c', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A22222C226C6162656C223A22E5AE89E58D93222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A2254617267657454797065222C2276616C7565223A22416E64726F6964227D3B, null, null, '2017-09-01 11:40:16', null, null, null);
INSERT INTO `sys_log` VALUES ('2badd1d78f5d46c594b1a438f23aa1fd', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F61644672616D652F6C697374222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E6A8A1E69DBFE5B8A7E7AEA1E79086222C22706172656E744964223A2234222C227065726D697373696F6E223A227379733A61646672616D653A6C697374222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:19:32', null, null, null);
INSERT INTO `sys_log` VALUES ('2bef49eb54614d928b82963383e5ede1', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5AEBDE9AB98E58D95E4BD8D222C226964223A22222C226C6162656C223A22E5838FE7B4A0222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A22636F6D70616E79222C2276616C7565223A2231227D3B, null, null, '2017-09-01 11:33:05', null, null, null);
INSERT INTO `sys_log` VALUES ('2c4c7ab9865844199a870aba59cf4105', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223264366136666538316365633436356638393231313036383663653665356431223B313B, null, null, '2017-09-01 14:16:10', null, null, null);
INSERT INTO `sys_log` VALUES ('2c5056a3708e4f39801419fbba1f3b02', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:25', null, null, null);
INSERT INTO `sys_log` VALUES ('2c96081abdea409eac7bf17ea8f8893c', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:37:31', null, null, null);
INSERT INTO `sys_log` VALUES ('2d28c5d96e6e40d995e4b2af86f2e7f6', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:24:59', null, null, null);
INSERT INTO `sys_log` VALUES ('2d9e30e4637141488eeccd94257e2eae', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B313B, null, null, '2017-09-01 14:10:18', null, null, null);
INSERT INTO `sys_log` VALUES ('2da45999e4c5472a8b1d76acd62bfff7', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x223031663166313765333763353465346561626361373933386361333736393938223B7B7D3B, null, null, '2017-09-01 11:50:11', null, null, null);
INSERT INTO `sys_log` VALUES ('2dd02875994a4094aca0bd4abaefac37', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:36:13', null, null, null);
INSERT INTO `sys_log` VALUES ('2e5051ada8c8457b9eb869a2c531ec1f', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:30:21', null, null, null);
INSERT INTO `sys_log` VALUES ('2ef9fb7c1eb34226946a70837f838e7b', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:02:55', null, null, null);
INSERT INTO `sys_log` VALUES ('2ff4a8b7218e44a48a8162c011a8f326', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:48:53', null, null, null);
INSERT INTO `sys_log` VALUES ('306ea0bf532e4249a52258d50a07b086', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:21:18', null, null, null);
INSERT INTO `sys_log` VALUES ('31067778a5c64355b19f42da774b62ca', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F707573682F6C697374222C226964223A2239222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E6B688E681AFE68EA8E98081222C22706172656E744964223A2232222C227065726D697373696F6E223A227379733A707573683A6C697374222C2272656D61726B73223A22222C22736F7274223A307D3B, null, null, '2017-09-01 11:03:43', null, null, null);
INSERT INTO `sys_log` VALUES ('313b358e6763473f961d162d2da7d568', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5AEA2E688B7E7ABAF222C226964223A22222C226C6162656C223A22E9AB98E88083222C2272656D61726B73223A22222C22736F7274223A33302C2274797065223A22427573696E65737354797065222C2276616C7565223A2238313933227D3B, null, null, '2017-09-01 11:38:11', null, null, null);
INSERT INTO `sys_log` VALUES ('31e90fdb8e1e450282468eb01a4e66ec', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:42', null, null, null);
INSERT INTO `sys_log` VALUES ('32c6722412c04f178f230c34a1284063', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:39:11', null, null, null);
INSERT INTO `sys_log` VALUES ('32e5b49e026e4e138d9fc5bae177ca1f', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:25:06', null, null, null);
INSERT INTO `sys_log` VALUES ('3333d4fff9e7403fbe2856c8611b9d89', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5AEA2E688B7E7ABAF222C226964223A22222C226C6162656C223A22E4B8ADE88083564950222C2272656D61726B73223A22222C22736F7274223A36302C2274797065223A22427573696E65737354797065222C2276616C7565223A2231303438383333227D3B, null, null, '2017-09-01 11:39:01', null, null, null);
INSERT INTO `sys_log` VALUES ('343ef42cf7634688a9d619703acb6f63', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A22222C226C6162656C223A22E794A8E688B7222C2272656D61726B73223A22222C22736F7274223A33302C2274797065223A2254617267657454797065222C2276616C7565223A22555345524944227D3B, null, null, '2017-09-01 11:41:34', null, null, null);
INSERT INTO `sys_log` VALUES ('34bfb0565e4d4da598ddda802dcaec96', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:22:20', null, null, null);
INSERT INTO `sys_log` VALUES ('34f45f80822248ada0ce53806156eed7', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A226333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:18:34', null, null, null);
INSERT INTO `sys_log` VALUES ('3804a6141ab94268aa7c57f6497429ca', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:42:11', null, null, null);
INSERT INTO `sys_log` VALUES ('3806fa1ed2404cd8a2cc97d11901f656', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B313B, null, null, '2017-09-01 14:25:07', null, null, null);
INSERT INTO `sys_log` VALUES ('3847468605fb440889a2cf0f87ec836a', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:37:57', null, null, null);
INSERT INTO `sys_log` VALUES ('3b510cac19c04ce3805f1957f39b30ce', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:25:13', null, null, null);
INSERT INTO `sys_log` VALUES ('3c2e52eb80244e679f7c69adf64c323e', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:32:27', null, null, null);
INSERT INTO `sys_log` VALUES ('3d78c01b5b6c4587ba18913d80bb71e8', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27416E64726F6964277D222C2264656661756C7446696C746572223A302C226663617073223A312C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A2262616E6E6572E4B8ADE88083222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E4B8ADE88083222C2274706C6964223A307D3B, null, null, '2017-09-01 11:51:08', null, null, null);
INSERT INTO `sys_log` VALUES ('3d7c79d58beb4cbd92f8e46c8e633e3b', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:17:56', null, null, null);
INSERT INTO `sys_log` VALUES ('40b604b44fd94300a8dfd292a40320d5', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:08', null, null, null);
INSERT INTO `sys_log` VALUES ('40fd681802a4435eb63eb53b6fc7428f', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F61644672616D652F73617665222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E4BFAEE694B9222C22706172656E744964223A223861643565613233656563303466383662656566333639313466653762376536222C227065726D697373696F6E223A227379733A61646672616D653A73617665222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:23:35', null, null, null);
INSERT INTO `sys_log` VALUES ('4179c2b444774fa981705997421e99fc', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:25:31', null, null, null);
INSERT INTO `sys_log` VALUES ('417fc36fbab4412b988d73d2855699e9', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A226333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:21:34', null, null, null);
INSERT INTO `sys_log` VALUES ('41d894128a664c71b5df2ef2eb13cd05', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:52:04', null, null, null);
INSERT INTO `sys_log` VALUES ('4254a02b990b4c46b7f1f4971ef3eaa1', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A226333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:17:51', null, null, null);
INSERT INTO `sys_log` VALUES ('426d893c65c946a0897208199cc0f31a', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:40:37', null, null, null);
INSERT INTO `sys_log` VALUES ('42926ee5a2f84377909bc474f0878596', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:33:27', null, null, null);
INSERT INTO `sys_log` VALUES ('43640f789a2747ea843fc86766dafd3a', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E78AB6E68081222C226964223A22222C226C6162656C223A22E7A681E794A8222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A22737461747573222C2276616C7565223A2231227D3B, null, null, '2017-09-01 11:32:37', null, null, null);
INSERT INTO `sys_log` VALUES ('43a7a58f35e34ea0a78ae9c0b4ab1e9a', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:50:16', null, null, null);
INSERT INTO `sys_log` VALUES ('443674fc95b045488481163595147606', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:18:49', null, null, null);
INSERT INTO `sys_log` VALUES ('45b7324ded63412eaf8a0eb925d5f11e', '菜单管理-删除菜单', 'com.lexue.sso.web.controller.MenuController.delete()', null, 'http://localhost:8080/menu/delete', 'admin', '0:0:0:0:0:0:0:1', 0x2233223B, null, null, '2017-09-01 11:03:06', null, null, null);
INSERT INTO `sys_log` VALUES ('45babee9b1d1440181a5461be46e672e', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 13:55:53', null, null, null);
INSERT INTO `sys_log` VALUES ('461d1dc8e5ca4f9c840eba894045631a', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:04', null, null, null);
INSERT INTO `sys_log` VALUES ('4679e45d9bda49c1a8343c1d4aa14ed3', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223264366136666538316365633436356638393231313036383663653665356431223B313B, null, null, '2017-09-01 14:15:10', null, null, null);
INSERT INTO `sys_log` VALUES ('468282b9bd864c63aef2b44f065f5c4a', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:40', null, null, null);
INSERT INTO `sys_log` VALUES ('484b3ce623894debaaa71a89b28db0e9', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:13', null, null, null);
INSERT INTO `sys_log` VALUES ('49076724e89a4875ba9f2f4e6cd98d89', '消息推送管理-新增消息推送', 'com.lexue.sso.web.controller.PushMessageController.add()', null, 'http://localhost:8080/push/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 12:32:21', null, null, null);
INSERT INTO `sys_log` VALUES ('49d96f0e5842412bbe29e5f83c91c1fc', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E98089E58F96E7B1BBE59E8B222C226964223A22222C226C6162656C223A22E789B9E6AE8AE7AD9BE98089222C2272656D61726B73223A22222C22736F7274223A34302C2274797065223A226974656D5069636B54797065222C2276616C7565223A2234227D3B, null, null, '2017-09-01 11:34:02', null, null, null);
INSERT INTO `sys_log` VALUES ('4a48d9d1b10d411aa90c51c767bcbe02', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:47', null, null, null);
INSERT INTO `sys_log` VALUES ('4b22e935cd06480a91a7e44654d31fab', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:33:51', null, null, null);
INSERT INTO `sys_log` VALUES ('4b65e77d28214c95ae0d8e51e1cfdd75', '用户管理-用户列表', 'com.lexue.sso.web.controller.UserController.index()', null, 'http://localhost:8080/user/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:26:20', null, null, null);
INSERT INTO `sys_log` VALUES ('4bcaa9351d4643c6adc2ef9998b66528', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A22222C226C6162656C223A22E6A087E7ADBE222C2272656D61726B73223A22222C22736F7274223A34302C2274797065223A2254617267657454797065222C2276616C7565223A22544147227D3B, null, null, '2017-09-01 11:41:43', null, null, null);
INSERT INTO `sys_log` VALUES ('4c0f05fd7818414dba1a8d1dc16dcab2', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:32:37', null, null, null);
INSERT INTO `sys_log` VALUES ('4d8c122947b540759992675caf5c1162', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:34:48', null, null, null);
INSERT INTO `sys_log` VALUES ('4da0b18ffce541a993d1e6fa39d5c27d', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:51:46', null, null, null);
INSERT INTO `sys_log` VALUES ('4df643cb619941eaa06277a5d22b167d', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 13:56:55', null, null, null);
INSERT INTO `sys_log` VALUES ('4dfcf13cbd8742fda664ee78ff79c46f', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:29:31', null, null, null);
INSERT INTO `sys_log` VALUES ('4e784a131a34410481ef04c7fbd8012f', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:39', null, null, null);
INSERT INTO `sys_log` VALUES ('4e823a6756134341adb13c6590c6e944', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:29:34', null, null, null);
INSERT INTO `sys_log` VALUES ('4e958eeb7567493dbce2542983f97039', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:05:24', null, null, null);
INSERT INTO `sys_log` VALUES ('4ef09ee2997d48cba2c523b9f3bf9868', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5AEBDE9AB98E58D95E4BD8D222C226964223A22222C226C6162656C223A22E799BEE58886E6AF94222C2272656D61726B73223A22222C22736F7274223A33302C2274797065223A22636F6D70616E79222C2276616C7565223A2233227D3B, null, null, '2017-09-01 11:33:19', null, null, null);
INSERT INTO `sys_log` VALUES ('4fdc1f62592345d6ae15ddaf2a99588f', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:38:11', null, null, null);
INSERT INTO `sys_log` VALUES ('5024945b22eb43baac7cb47d0eb661c0', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:05:28', null, null, null);
INSERT INTO `sys_log` VALUES ('51bed4f2eb6441fab99342c48bb84fe7', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:47:04', null, null, null);
INSERT INTO `sys_log` VALUES ('52f6feb00a644af0847cfc63f9b0cb9d', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27416E64726F6964277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A322C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A22E5AE89E58D93222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083E5AE89E58D93222C2274706C6964223A337D3B, null, null, '2017-09-01 11:56:08', null, null, null);
INSERT INTO `sys_log` VALUES ('548f21ade3e24f718b62b4aaf5ce46cc', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:30:41', null, null, null);
INSERT INTO `sys_log` VALUES ('54a0243c01214f6c9af5f8fd659fccfa', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:43', null, null, null);
INSERT INTO `sys_log` VALUES ('54ab4c3f24334c4fbb3976be2e03dda4', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:29', null, null, null);
INSERT INTO `sys_log` VALUES ('5502860590214eeca8cad3e119f2deb0', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:02', null, null, null);
INSERT INTO `sys_log` VALUES ('551b6aaf7dcd4433a7639c69aa0bb337', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:28:55', null, null, null);
INSERT INTO `sys_log` VALUES ('5706ea44ea5d4a9a91246ef9bb4c7104', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:56:50', null, null, null);
INSERT INTO `sys_log` VALUES ('58264f5734164859a1ae270e88d2d537', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:54:52', null, null, null);
INSERT INTO `sys_log` VALUES ('58d361db027d4b86bf8c7fdaaf0c28f4', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:10:14', null, null, null);
INSERT INTO `sys_log` VALUES ('5a0bf0184ab841938aeb2f0646fb1c9d', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:05', null, null, null);
INSERT INTO `sys_log` VALUES ('5a97ee7858874439adf9292e43b952fd', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:18:32', null, null, null);
INSERT INTO `sys_log` VALUES ('5b4d0e5868694b1fb8adfd303e0a94ef', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:26:17', null, null, null);
INSERT INTO `sys_log` VALUES ('5b803fe7e4c747cb8fd66d5edb9fa9cd', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:30:55', null, null, null);
INSERT INTO `sys_log` VALUES ('5c9b1614842f45b7b3f8cbf5cb8b5c0f', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223264366136666538316365633436356638393231313036383663653665356431223B313B, null, null, '2017-09-01 14:10:15', null, null, null);
INSERT INTO `sys_log` VALUES ('5dd2e56ee1fd4754bf0378b9c024e8d6', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:07', null, null, null);
INSERT INTO `sys_log` VALUES ('5dd90c403556459a89cffba8c778eb86', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:30:58', null, null, null);
INSERT INTO `sys_log` VALUES ('5e3ce2b749264febab68c5acc859e3a9', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:45:26', null, null, null);
INSERT INTO `sys_log` VALUES ('5f49ce9455f146eb80c8bad91d4a9252', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:30:26', null, null, null);
INSERT INTO `sys_log` VALUES ('6063b80c007b4d478f99f72d455d629f', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:34:55', null, null, null);
INSERT INTO `sys_log` VALUES ('610775b7f9e44aa3b95521c5aa60a311', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:33:56', null, null, null);
INSERT INTO `sys_log` VALUES ('6146059b35f94fea99b1e04be79d3504', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E69DA1E79BAEE7B1BBE59E8B222C226964223A22222C226C6162656C223A22E6B581E5AA92E4BD93222C2272656D61726B73223A22222C22736F7274223A33302C2274797065223A226672616D6554797065222C2276616C7565223A2233227D3B, null, null, '2017-09-01 11:34:42', null, null, null);
INSERT INTO `sys_log` VALUES ('62259254e618412d8c2eb38699f253a4', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x223333616663393466353531343433383462343039623237663831363434633731223B7B7D3B, null, null, '2017-09-01 14:16:19', null, null, null);
INSERT INTO `sys_log` VALUES ('624b840eb99940849d20d33888ada7bc', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:55', null, null, null);
INSERT INTO `sys_log` VALUES ('632efd84373d495780067d9b922508d4', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A22222C226C6162656C223A22E5AEA2E688B7E7ABAF222C2272656D61726B73223A22222C22736F7274223A35302C2274797065223A2254617267657454797065222C2276616C7565223A22434C49454E54227D3B, null, null, '2017-09-01 11:42:01', null, null, null);
INSERT INTO `sys_log` VALUES ('63a0a80885414448ae847a5a7aafa3a5', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:51:14', null, null, null);
INSERT INTO `sys_log` VALUES ('63cbb5671e7e492fafca8336d2ce45d7', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:17:20', null, null, null);
INSERT INTO `sys_log` VALUES ('64846c7e285041ac98c22a42ef8060cd', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:41:43', null, null, null);
INSERT INTO `sys_log` VALUES ('64a2a79bca724571a2acb4a2b31eb0e5', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:34:42', null, null, null);
INSERT INTO `sys_log` VALUES ('6596513ec6b640b099cfb954a1e27dcf', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5AEA2E688B7E7ABAF222C226964223A223562313438656463383639373434636138616333663534303963306462663535222C226C6162656C223A22E4B8ADE88083222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A22427573696E65737354797065222C2276616C7565223A2234303937227D3B, null, null, '2017-09-01 11:37:31', null, null, null);
INSERT INTO `sys_log` VALUES ('669fd77921e343cf928942fe1e604b52', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:41:34', null, null, null);
INSERT INTO `sys_log` VALUES ('677ca1fe30ac4795b77ffd5f913d4e0b', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:21', null, null, null);
INSERT INTO `sys_log` VALUES ('696d73c719f3439db0a056af6e2e213f', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E78AB6E68081222C226964223A22222C226C6162656C223A22E590AFE794A8222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A22737461747573222C2276616C7565223A2232227D3B, null, null, '2017-09-01 11:32:27', null, null, null);
INSERT INTO `sys_log` VALUES ('698b3caa21a242b486935fad207bdbeb', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:39', null, null, null);
INSERT INTO `sys_log` VALUES ('69c8a5e7c4524411bd96161e31d4c8ee', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:52:43', null, null, null);
INSERT INTO `sys_log` VALUES ('6a18637e8bf7471d90d19a68fd271e94', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:29:04', null, null, null);
INSERT INTO `sys_log` VALUES ('6acac965b5d04c54a3531cf632e818c2', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:33:37', null, null, null);
INSERT INTO `sys_log` VALUES ('6e03dac4ba6040d5bd3f8380d5e5c405', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F61645265732F64656C657465222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E588A0E999A4222C22706172656E744964223A226530623666623064356234353431356661393730336463343461383130373038222C227065726D697373696F6E223A227379733A61647265733A64656C657465222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:25:12', null, null, null);
INSERT INTO `sys_log` VALUES ('6f470df06a1a4511a9419cce96b14825', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27416E64726F6964277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A322C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A22E5AE89E58D93222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083E5AE89E58D93222C2274706C6964223A337D3B, null, null, '2017-09-01 14:22:29', null, null, null);
INSERT INTO `sys_log` VALUES ('6f7761a10d924c149139d6d44a2105f2', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F61645265732F616464222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E6B7BBE58AA0222C22706172656E744964223A226530623666623064356234353431356661393730336463343461383130373038222C227065726D697373696F6E223A227379733A61647265733A616464222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:24:20', null, null, null);
INSERT INTO `sys_log` VALUES ('6fe3aa0594024fe8b27b9d8c4e1a425d', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:29:34', null, null, null);
INSERT INTO `sys_log` VALUES ('703bbcbdd529457ca6a25d6767ec85d2', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:33:12', null, null, null);
INSERT INTO `sys_log` VALUES ('70739cbb83b64180b04d69ed10483da6', '菜单管理-删除菜单', 'com.lexue.sso.web.controller.MenuController.delete()', null, 'http://localhost:8080/menu/delete', 'admin', '0:0:0:0:0:0:0:1', 0x223132223B, null, null, '2017-09-01 11:02:51', null, null, null);
INSERT INTO `sys_log` VALUES ('70976d46566d46efa2aa5e6ad19f21fc', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:56:18', null, null, null);
INSERT INTO `sys_log` VALUES ('721683f71fbb4d8da421a8d17f4675b8', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:02:51', null, null, null);
INSERT INTO `sys_log` VALUES ('724dd8550c47449fae561fd359046c02', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:54:58', null, null, null);
INSERT INTO `sys_log` VALUES ('74468abfe6514ad1bfc731fc9da46916', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E98089E58F96E7B1BBE59E8B222C226964223A22222C226C6162656C223A22E99A8FE69CBA222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A226974656D5069636B54797065222C2276616C7565223A2232227D3B, null, null, '2017-09-01 11:33:51', null, null, null);
INSERT INTO `sys_log` VALUES ('756a39a8994d421ebb65ead7f2fd5f06', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F707573682F64656C657465222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E588A0E999A4222C22706172656E744964223A2239222C227065726D697373696F6E223A227379733A707573683A64656C657465222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:21:18', null, null, null);
INSERT INTO `sys_log` VALUES ('75932b72e77e4e3f8bfb59a8cb6340ae', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:56:08', null, null, null);
INSERT INTO `sys_log` VALUES ('75c473be54ce48a8b8bde913af2e60e0', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:14:22', null, null, null);
INSERT INTO `sys_log` VALUES ('768481ce44f2463eb025886b8e1c31bb', '消息推送管理-消息推送列表', 'com.lexue.sso.web.controller.PushMessageController.index()', null, 'http://localhost:8080/push/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 12:32:16', null, null, null);
INSERT INTO `sys_log` VALUES ('76bd6e58ed3b49578f793f769277cee1', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F61644672616D652F64656C657465222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E588A0E999A4222C22706172656E744964223A223861643565613233656563303466383662656566333639313466653762376536222C227065726D697373696F6E223A227379733A61646672616D653A64656C657465222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:24:00', null, null, null);
INSERT INTO `sys_log` VALUES ('796d904224424b2badcc4fdf63653212', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:27', null, null, null);
INSERT INTO `sys_log` VALUES ('79a1c0616625481e8ac199cee8808763', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:40:40', null, null, null);
INSERT INTO `sys_log` VALUES ('7a74b561e22f4df3afd8ba96434722a8', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:39:36', null, null, null);
INSERT INTO `sys_log` VALUES ('7b1fdcb635874db49cf8957d205dd308', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:08', null, null, null);
INSERT INTO `sys_log` VALUES ('7c181842494a43fa9eb64d5dbb553718', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:17:14', null, null, null);
INSERT INTO `sys_log` VALUES ('7c65c9e43745447a859c2da73c18df76', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:29:35', null, null, null);
INSERT INTO `sys_log` VALUES ('7c8668ebb1df4c9e9cbbef551133d08c', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:18:01', null, null, null);
INSERT INTO `sys_log` VALUES ('7d2acc6005ea449cbf38e3f73431f8ad', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A223461373261343064383333633434653962666566396361643137666432346361222C226C6162656C223A22E88BB9E69E9C222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A22706C616E74666F726D73222C2276616C7565223A22494F53227D3B, null, null, '2017-09-01 11:40:40', null, null, null);
INSERT INTO `sys_log` VALUES ('7d2e1e52051c45dea61ec9364ccd7e4b', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:21:49', null, null, null);
INSERT INTO `sys_log` VALUES ('7d9e5e2a53ca4393953406ab37d489e1', '菜单管理-删除菜单', 'com.lexue.sso.web.controller.MenuController.delete()', null, 'http://localhost:8080/menu/delete', 'admin', '0:0:0:0:0:0:0:1', 0x223132223B, null, null, '2017-09-01 11:02:55', null, null, null);
INSERT INTO `sys_log` VALUES ('7e0986b50a6c484babf68b05e0c90ecc', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A322C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031313A35373A3037222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A312C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031313A35373A3036222C22737461747573223A312C227469746C65223A22E5AE89E58D9362616E6E657232222C2274706C4964223A332C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 11:57:12', null, null, null);
INSERT INTO `sys_log` VALUES ('7e0abb840a464930a6d3d1adddde3d0a', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:30:39', null, null, null);
INSERT INTO `sys_log` VALUES ('7f5dbaf91b60463086f367cd01424ec2', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F61644672616D652F616464222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E6B7BBE58AA0222C22706172656E744964223A223861643565613233656563303466383662656566333639313466653762376536222C227065726D697373696F6E223A227379733A61646672616D653A616464222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:23:09', null, null, null);
INSERT INTO `sys_log` VALUES ('80a2460d95f44cbb8b8fb1b38768d87a', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:57:12', null, null, null);
INSERT INTO `sys_log` VALUES ('8138617ef719453697b5485a02519cb6', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:31:45', null, null, null);
INSERT INTO `sys_log` VALUES ('81cfa7fdc59748b793ef9f1d66691f63', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:25:33', null, null, null);
INSERT INTO `sys_log` VALUES ('81d595aa1178406ba33c208403953050', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:17:14', null, null, null);
INSERT INTO `sys_log` VALUES ('8292c9dcdbef4db4b3a3812e698dacf1', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:16', null, null, null);
INSERT INTO `sys_log` VALUES ('84867dc95a074cfc81f6031a478e658e', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:28', null, null, null);
INSERT INTO `sys_log` VALUES ('86805c7a1ce54826af2fbabe12046d80', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:22:02', null, null, null);
INSERT INTO `sys_log` VALUES ('874bc71d286546d6bbe57346d33054ce', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:27:06', null, null, null);
INSERT INTO `sys_log` VALUES ('87d3224dcd374361b12f3a92beb67210', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:15:09', null, null, null);
INSERT INTO `sys_log` VALUES ('891facac4fab4b15abd567c4a3eba2af', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B313B, null, null, '2017-09-01 14:16:12', null, null, null);
INSERT INTO `sys_log` VALUES ('89fc9e0b2d2f437d90916886ea4bdeba', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A322C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031313A35353A3136222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A312C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031313A35353A3135222C22737461747573223A312C227469746C65223A2262616E6E6572E9AB98E8808332222C2274706C4964223A312C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 11:55:21', null, null, null);
INSERT INTO `sys_log` VALUES ('8a59677fe8794abfabe1a171ac7fd645', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:13', null, null, null);
INSERT INTO `sys_log` VALUES ('8a947a5d34154741b0681f85182915ec', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A312C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031313A35363A3435222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A312C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031313A35363A3434222C22737461747573223A312C227469746C65223A22E5AE89E58D9362616E6E657231222C2274706C4964223A332C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 11:56:50', null, null, null);
INSERT INTO `sys_log` VALUES ('8b217a6943074d2082f6d02628306e36', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:03:43', null, null, null);
INSERT INTO `sys_log` VALUES ('8bcabbb5692e475f9bdffe9fd2b393b0', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27494F53277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A312C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A2262616E6E6572E9AB98E88083222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083222C2274706C6964223A317D3B, null, null, '2017-09-01 11:50:16', null, null, null);
INSERT INTO `sys_log` VALUES ('8d18d5bf77c64ded9f9255019fae5c18', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 12:32:52', null, null, null);
INSERT INTO `sys_log` VALUES ('8d3e610c74e84e18bd0e67de4f260104', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A22222C226C6162656C223A22E585A8E983A8222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A2254617267657454797065222C2276616C7565223A22414C4C227D3B, null, null, '2017-09-01 11:41:14', null, null, null);
INSERT INTO `sys_log` VALUES ('8da87c83368e4247aa632a5fbf748b89', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:27:04', null, null, null);
INSERT INTO `sys_log` VALUES ('8db05552923f4aba946cb80ee3a5f262', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:02:58', null, null, null);
INSERT INTO `sys_log` VALUES ('8ea9062ab40c41f98d676a7cc9d5a4b7', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:54', null, null, null);
INSERT INTO `sys_log` VALUES ('8fcaf7c8644f4ccd86942dede49c82aa', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:30:48', null, null, null);
INSERT INTO `sys_log` VALUES ('9052e74f065d4852ba6f2991fb5ef748', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:24:13', null, null, null);
INSERT INTO `sys_log` VALUES ('90796126a52747a28207585bf99cef99', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:30:27', null, null, null);
INSERT INTO `sys_log` VALUES ('90c579760aa7462e8eeb3dbffd074bce', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:18:34', null, null, null);
INSERT INTO `sys_log` VALUES ('9131adb52ee44b23902b2f184ab0512d', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:20:33', null, null, null);
INSERT INTO `sys_log` VALUES ('91a63856280144bcb7ae7302ed3ca0cd', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:28:59', null, null, null);
INSERT INTO `sys_log` VALUES ('9216f79fea884af2a2666f50ebc2cd07', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:23:35', null, null, null);
INSERT INTO `sys_log` VALUES ('9289d6e34d634762b21952f7063c7de7', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:42:01', null, null, null);
INSERT INTO `sys_log` VALUES ('94ca6dc7338e4cfcaa61b448d3b1c58e', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F707573682F616464222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E6B7BBE58AA0222C22706172656E744964223A2239222C227065726D697373696F6E223A227379733A707573683A616464222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:20:33', null, null, null);
INSERT INTO `sys_log` VALUES ('952c7012b38d43cfb651f073a2a3bb8f', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:28:59', null, null, null);
INSERT INTO `sys_log` VALUES ('955369aa2b0147d391901a6eefada305', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x223333616663393466353531343433383462343039623237663831363434633731223B7B7D3B, null, null, '2017-09-01 14:16:43', null, null, null);
INSERT INTO `sys_log` VALUES ('956d7958f4b7448c905ed493d760abe9', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E9808FE4BCA0E7B1BBE59E8B222C226964223A22222C226C6162656C223A22E8A786E9A291E9808FE4BCA0222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A227472616E73706172656E74222C2276616C7565223A2232227D3B, null, null, '2017-09-01 11:36:13', null, null, null);
INSERT INTO `sys_log` VALUES ('95a5637b4df44de49176c1214bd4dc8a', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F616454706C2F73617665222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E4BFAEE694B9222C22706172656E744964223A226661333861346166373830393466333362353539373830613632616136383039222C227065726D697373696F6E223A227379733A616474706C3A73617665222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:25:59', null, null, null);
INSERT INTO `sys_log` VALUES ('967546ff2ccc40e899494338f7639e52', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:33:45', null, null, null);
INSERT INTO `sys_log` VALUES ('967a7c787045497d8b255468f6aecc57', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:17:48', null, null, null);
INSERT INTO `sys_log` VALUES ('96c952ed8cd24e19ab37b759249ccc81', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:21:32', null, null, null);
INSERT INTO `sys_log` VALUES ('977096e6fd5e455a82e032a134edad40', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 13:57:16', null, null, null);
INSERT INTO `sys_log` VALUES ('97847a99862f4b39a890b786f599605b', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:22:33', null, null, null);
INSERT INTO `sys_log` VALUES ('981ef7e2c1c747d8b64d05be9c7a1c11', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:30:58', null, null, null);
INSERT INTO `sys_log` VALUES ('98d26c7b5dd449c794d7c6b74a3d1090', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:07', null, null, null);
INSERT INTO `sys_log` VALUES ('99f1899f55744e0ca947ebf3b6ddb9eb', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:22:23', null, null, null);
INSERT INTO `sys_log` VALUES ('9a8e4088937c42a9ac662ae6a1eec374', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F6164426F782F64656C657465222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E588A0E999A4222C22706172656E744964223A223135222C227065726D697373696F6E223A227379733A6164626F783A64656C657465222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:22:26', null, null, null);
INSERT INTO `sys_log` VALUES ('9b5e74ba57cb4f5b9e70bfb2ba39e27a', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:16:22', null, null, null);
INSERT INTO `sys_log` VALUES ('9bed5f5eb2354342a3fe58e2c0e8ce1b', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:33:42', null, null, null);
INSERT INTO `sys_log` VALUES ('9c1b592f53a5415db6e7c8143d6acbae', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:46', null, null, null);
INSERT INTO `sys_log` VALUES ('9c78e662e6714bc8baa07a773741e627', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:41', null, null, null);
INSERT INTO `sys_log` VALUES ('9c800858cdb4475b861e7fa1c448d183', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:05:13', null, null, null);
INSERT INTO `sys_log` VALUES ('9c88d52d79714791a41b255563315c5c', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:17:21', null, null, null);
INSERT INTO `sys_log` VALUES ('9ce7769b2dc14a7596368bf425d530e9', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:21:44', null, null, null);
INSERT INTO `sys_log` VALUES ('9d265aac80b245978582bcf05c8b2bac', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:42:27', null, null, null);
INSERT INTO `sys_log` VALUES ('9d809ff81c2a4584952924e16f834c42', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E69DA1E79BAEE7B1BBE59E8B222C226964223A22222C226C6162656C223A22E5A49AE59BBEE5B8A7222C2272656D61726B73223A22222C22736F7274223A36302C2274797065223A226672616D6554797065222C2276616C7565223A223130227D3B, null, null, '2017-09-01 11:35:01', null, null, null);
INSERT INTO `sys_log` VALUES ('9dc56d172ed544e785597905320ea84d', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:30:30', null, null, null);
INSERT INTO `sys_log` VALUES ('9deb60e9c2fb4b22a34ec42071bfdefe', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A22222C226C6162656C223A22E5B9B3E58FB0222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A2254617267657454797065222C2276616C7565223A22504C414E54464F524D227D3B, null, null, '2017-09-01 11:41:26', null, null, null);
INSERT INTO `sys_log` VALUES ('9e192a13be794265b6e949bd0a86b40b', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:34:30', null, null, null);
INSERT INTO `sys_log` VALUES ('9e3527de0781472e9cf4a09e7d22c88d', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:10:12', null, null, null);
INSERT INTO `sys_log` VALUES ('9ebb7e0c657d4797a92d7c54ef97002b', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:49:00', null, null, null);
INSERT INTO `sys_log` VALUES ('9f91924d816a45cebc85bcc7cea4e628', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 13:55:51', null, null, null);
INSERT INTO `sys_log` VALUES ('a001f7f85ed34464ab0ed431464115e8', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:33:23', null, null, null);
INSERT INTO `sys_log` VALUES ('a01530bdde204cbcb58185b80839612c', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:39:01', null, null, null);
INSERT INTO `sys_log` VALUES ('a025ea88d7244d41ad76deffdc658ed0', '角色管理-新增修改角色', 'com.lexue.sso.web.controller.RoleController.save()', null, 'http://localhost:8080/role/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226964223A2231222C226D656E75496473223A22312C352C65623230363462353131316134643365393433333035633163303831343238392C362C31656133353537626138326434373237396261376635313765653561316565632C37666538663333333139333034633865386536353437653261396363643530612C64636462303935643638386434613763613964353365376562626438643934622C66396465323634323665356434316266396235373732343863653066613635632C372C31346132666431653435326134323235393938383234376438633636353836332C36383065396635353064323434383639623864623037633061663966363736382C37363065383263306434396134376337613062643363653831383039356536652C61383239303661386138316534643538613335663564616462383437316431642C36646530633666653434316334363566396631306235663063313662376533312C32303530356339613535383634393230616562643762303236663231316563622C33313233333161623365666634386362396466353531333732363632656137372C64626464656530313266343434396263613836306366663033383334326636332C65356138626362653639316134396361613831363362333430333766663533382C61376663313437346638623534396130613433656439376238366430333237312C30336434316263353261326434643962616636646634323032396230663865322C31373065646130363533356234333637396232663835666461323035663065642C34306136633030646338356634386263393561653738393637663636626161312C62363436613930663136326434646135386237653336623936636136343166632C62633363336233383137613834326636613130613964366233386436616536342C63343136376236663933336234376261626462303938326361656333636538652C64313263633030306164376134363932393861306134313539613839393130652C34343939303131623535653034666435613439343437323436393561346566332C39353839313637396639353334386534613964373862323831386262313363312C61313464613465646561323534333066383865626334326634623036653139302C61326235623865636431303334366637623763306565653138316566633161662C322C392C32643630646531653633356534633831613466363262633535333036313535332C37323331663437383961376534386466386161356336653865393138623463302C66326635633839626161656234353065393062363461346465386431313064342C31302C31312C342C31352C33623735363566316332623534616439623332633736326263346266653363362C61313632623031373866313834376233386130383865353364643134646136312C62333335366463353265663734623236383537383462356531626433626363632C38616435656132336565633034663836626565663336393134666537623765362C34363766326361363932356434386331396532316461313936613532636133372C37613931656433613439386134623739626533343731323263386638303538362C61363061616365626232326134346366613262333365313933666532303934352C65306236666230643562343534313566613937303364633434613831303730382C37613337366432383435616434613338383965633632626431303339336562642C61373930303237306332623634626235393438373336303661616363626365652C62643935336437333962313634396165396663333236613362666638376565642C66613338613461663738303934663333623535393738306136326161363830392C31643335623234393936373334393262383661626630393363376364623437302C34656438656438633763333734313266383765376238393735323232366466392C63636631366339313566376534626138393365306461356539316133653536662C222C226E616D65223A22E7B3BBE7BB9FE7AEA1E79086E59198222C2272656D61726B73223A22222C2275736561626C65223A2231227D3B, null, null, '2017-09-01 11:26:45', null, null, null);
INSERT INTO `sys_log` VALUES ('a08b48f837dd48c1b26d030d31e2cc77', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A322C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031333A35373A3039222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A302C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031333A35373A3038222C22737461747573223A312C227469746C65223A2262616E6E6572E5AE89E58D9332222C2274706C4964223A332C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 13:57:15', null, null, null);
INSERT INTO `sys_log` VALUES ('a138ac84a1df46c4a0662def3842e93a', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F707573682F73617665222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E4BFAEE694B9222C22706172656E744964223A2239222C227065726D697373696F6E223A227379733A707573683A73617665222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:20:59', null, null, null);
INSERT INTO `sys_log` VALUES ('a4237269e75949148ad43cfd149ddf9c', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:31', null, null, null);
INSERT INTO `sys_log` VALUES ('a6036929bdd84833ba945895a9381b3e', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5AEA2E688B7E7ABAF222C226964223A22222C226C6162656C223A22E9AB98E88083564950222C2272656D61726B73223A22222C22736F7274223A37302C2274797065223A22427573696E65737354797065222C2276616C7565223A2232303937343039227D3B, null, null, '2017-09-01 11:39:11', null, null, null);
INSERT INTO `sys_log` VALUES ('a604f461128340789bc76b62fa90d1e6', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:30:28', null, null, null);
INSERT INTO `sys_log` VALUES ('a64605b3f19944af81b880b6547ed0eb', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:33:19', null, null, null);
INSERT INTO `sys_log` VALUES ('a6c20b8915c945a59fcd3ac001e784a6', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:44', null, null, null);
INSERT INTO `sys_log` VALUES ('a72232c2dbe2490b8e8ae35d9af786f1', '菜单管理-删除菜单', 'com.lexue.sso.web.controller.MenuController.delete()', null, 'http://localhost:8080/menu/delete', 'admin', '0:0:0:0:0:0:0:1', 0x223134223B, null, null, '2017-09-01 11:03:02', null, null, null);
INSERT INTO `sys_log` VALUES ('a761ec6c4b16478496778d5d57720052', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:51:49', null, null, null);
INSERT INTO `sys_log` VALUES ('a7d0ffcda7084828825412cacdf64483', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:56:27', null, null, null);
INSERT INTO `sys_log` VALUES ('aa35acef752844678d1e263620552692', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:37', null, null, null);
INSERT INTO `sys_log` VALUES ('aa51f4b88f5d45b18ac22a4cf684d5a6', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:45:03', null, null, null);
INSERT INTO `sys_log` VALUES ('aadf203eb19c4523929f0387b1cb6f12', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:33:25', null, null, null);
INSERT INTO `sys_log` VALUES ('ab3adfb9e82c4a3eab76c4ba38d0499c', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:28:56', null, null, null);
INSERT INTO `sys_log` VALUES ('ab7a9530574347daa9158e14c27eb6f3', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:34:02', null, null, null);
INSERT INTO `sys_log` VALUES ('abc85cee065448bfbf5e59b88a4750be', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:40:16', null, null, null);
INSERT INTO `sys_log` VALUES ('ac3d539fe2ca4098856a7df7e74707fb', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:18', null, null, null);
INSERT INTO `sys_log` VALUES ('acb4f0869a974ccf9a9f9787b7af0092', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:38:25', null, null, null);
INSERT INTO `sys_log` VALUES ('ae55bc9e18d843a8b29fda34c3c79e5f', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x226564653230333763326237633434366638646132393962616431373437653165223B7B7D3B, null, null, '2017-09-01 14:30:56', null, null, null);
INSERT INTO `sys_log` VALUES ('aee2ee83ca2543dba217e7182674d615', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:29:04', null, null, null);
INSERT INTO `sys_log` VALUES ('af6d96ce3afa43a28d86bf5580fbed6f', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:25:08', null, null, null);
INSERT INTO `sys_log` VALUES ('afc8af073d4d41b1a250aaa6883629ec', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:16:14', null, null, null);
INSERT INTO `sys_log` VALUES ('afe81b8df8a04f3cad32caf7efec4602', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:44', null, null, null);
INSERT INTO `sys_log` VALUES ('b006b37d1ffd418d9d32c4824f87aa34', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:25:36', null, null, null);
INSERT INTO `sys_log` VALUES ('b0540bdfa11840c9a33251140b33f26b', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x226164343737356133393531313465396139323236323238313633616266643036223B7B7D3B, null, null, '2017-09-01 14:22:46', null, null, null);
INSERT INTO `sys_log` VALUES ('b071915bfd954f6ea61452a7458b3d56', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:10:15', null, null, null);
INSERT INTO `sys_log` VALUES ('b097bf2f032d4b47923cb929e005bcb5', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A22222C226C6162656C223A22E88BB9E69E9C222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A2254617267657454797065222C2276616C7565223A22494F53227D3B, null, null, '2017-09-01 11:39:36', null, null, null);
INSERT INTO `sys_log` VALUES ('b13416f2bc14433e98ec2626228d3c63', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E98089E58F96E7B1BBE59E8B222C226964223A22222C226C6162656C223A22E4BC98E58588E7BAA7222C2272656D61726B73223A22222C22736F7274223A33302C2274797065223A226974656D5069636B54797065222C2276616C7565223A2233227D3B, null, null, '2017-09-01 11:33:56', null, null, null);
INSERT INTO `sys_log` VALUES ('b150370ebe7b4f209814f5b265661a82', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x226164343737356133393531313465396139323236323238313633616266643036223B7B7D3B, null, null, '2017-09-01 11:55:52', null, null, null);
INSERT INTO `sys_log` VALUES ('b1dce217cf33448da87a9f2c50ad6c82', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A226564653230333763326237633434366638646132393962616431373437653165222C226E6F7465223A22222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 14:29:37', null, null, null);
INSERT INTO `sys_log` VALUES ('b27c51996c4a4cf2ab3125e5f4a22950', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:05:26', null, null, null);
INSERT INTO `sys_log` VALUES ('b4c1521d25ae43b698d9a5e1602217b2', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:54:56', null, null, null);
INSERT INTO `sys_log` VALUES ('b5a30bc4375d495ba735a8252decb292', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x226164343737356133393531313465396139323236323238313633616266643036223B7B7D3B, null, null, '2017-09-01 14:22:56', null, null, null);
INSERT INTO `sys_log` VALUES ('b7b60c619f0245bbb483673f20860633', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A226333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:21:59', null, null, null);
INSERT INTO `sys_log` VALUES ('b8f69988bac84b6daeb753239cf1a395', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223264366136666538316365633436356638393231313036383663653665356431223B313B, null, null, '2017-09-01 14:17:03', null, null, null);
INSERT INTO `sys_log` VALUES ('b93ccba47c424cbc9fa2455f3eec53a7', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E9808FE4BCA0E7B1BBE59E8B222C226964223A22222C226C6162656C223A22E697A0E9808FE4BCA0222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A227472616E73706172656E74222C2276616C7565223A2231227D3B, null, null, '2017-09-01 11:36:08', null, null, null);
INSERT INTO `sys_log` VALUES ('ba70a2b21df74589aed527b9323837aa', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:35', null, null, null);
INSERT INTO `sys_log` VALUES ('bc063dc6ae8f4b9ba00a5f15f64d2058', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:05:02', null, null, null);
INSERT INTO `sys_log` VALUES ('bc5afa9188dd441fb8d7a3c6d75cb499', '消息推送管理-新增消息推送', 'com.lexue.sso.web.controller.PushMessageController.add()', null, 'http://localhost:8080/push/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 12:30:24', null, null, null);
INSERT INTO `sys_log` VALUES ('bc649590328e43a5b910e929dceec28e', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:38:40', null, null, null);
INSERT INTO `sys_log` VALUES ('bce088da85994e349415d02f91f29f3f', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 13:57:36', null, null, null);
INSERT INTO `sys_log` VALUES ('bd7088be5a974b6f8322bbd8427f0058', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:34', null, null, null);
INSERT INTO `sys_log` VALUES ('bde53ffb1eb748f8863fa290bed5cb84', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x226164343737356133393531313465396139323236323238313633616266643036223B7B7D3B, null, null, '2017-09-01 14:22:26', null, null, null);
INSERT INTO `sys_log` VALUES ('be72dfd016d540bbb046bcd7e80f3387', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A312C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031313A35343A3438222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A312C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031313A35343A3436222C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E8808331222C2274706C4964223A312C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 11:54:52', null, null, null);
INSERT INTO `sys_log` VALUES ('bea7c84e73714708861a6ed5175a7ab3', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:51', null, null, null);
INSERT INTO `sys_log` VALUES ('bf0567a27af94c77a449f8f7003cb0af', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:52:07', null, null, null);
INSERT INTO `sys_log` VALUES ('bf251cfc982448ba9cb8c472cee9d2d5', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:25:12', null, null, null);
INSERT INTO `sys_log` VALUES ('bf7a50823ca14d2aa6162a6bd18d51d6', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27416E64726F6964277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A322C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A22E5AE89E58D93222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083E5AE89E58D93222C2274706C6964223A337D3B, null, null, '2017-09-01 14:24:13', null, null, null);
INSERT INTO `sys_log` VALUES ('bfaa1295f82c4e20a26bb0fac1f918fc', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:21:57', null, null, null);
INSERT INTO `sys_log` VALUES ('c020ff0e80e4440e85f0f0353c770914', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5AEA2E688B7E7ABAF222C226964223A22222C226C6162656C223A22E4B8ADE88083E5BF97E684BF222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A22427573696E65737354797065222C2276616C7565223A2234303938227D3B, null, null, '2017-09-01 11:37:57', null, null, null);
INSERT INTO `sys_log` VALUES ('c09373a75b2840baa152f77faa6407fc', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:49:11', null, null, null);
INSERT INTO `sys_log` VALUES ('c1f982c4c1d94ff688beeb8ee6e2aff3', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:56:51', null, null, null);
INSERT INTO `sys_log` VALUES ('c314a453002e4b2bbfa67cf9d9030d50', '广告帧管理-保存广告帧', 'com.lexue.sso.web.controller.AdFrameController.save()', null, 'http://localhost:8080/adFrame/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B2261645265734964223A312C2263656C6C4C61796F757458436F756E74223A302C2263656C6C4C61796F757458456E64223A302C2263656C6C4C61796F7574585374617274223A302C2263656C6C4C61796F757459436F756E74223A302C2263656C6C4C61796F757459456E64223A302C2263656C6C4C61796F7574595374617274223A302C2264697361626C6554696D657374616D70223A302C22656E61626C6554696D657374616D70223A302C22656E6454696D65223A22323031372D30392D30342031333A35363A3430222C226672616D6554797065223A31302C226964223A22222C226974656D4361706163697479223A312C226974656D5069636B54797065223A312C226974656D5363726F6C6C54696D65223A302C227072696F72697479223A312C2272656C6174696F6E73223A5B5D2C22737461727454696D65223A22323031372D30392D30312031333A35363A3339222C22737461747573223A312C227469746C65223A2262616E6E6572E5AE89E58D93222C2274706C4964223A332C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 13:56:53', null, null, null);
INSERT INTO `sys_log` VALUES ('c36efbd84a4748adb4a5049e0da15693', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:36', null, null, null);
INSERT INTO `sys_log` VALUES ('c39e534cec6843c5bbfd71042900128d', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:50:30', null, null, null);
INSERT INTO `sys_log` VALUES ('c41b014b0fdf494f94bf873548bf4a6c', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F77782F67726F7570222C226964223A223130222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E5BEAEE4BFA1E7BB84E7AEA1E79086222C22706172656E744964223A2232222C227065726D697373696F6E223A227379733A67726F75703A6C697374222C2272656D61726B73223A22222C22736F7274223A307D3B, null, null, '2017-09-01 11:15:08', null, null, null);
INSERT INTO `sys_log` VALUES ('c4302e4b8cee47cfaac529357dda7dbb', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:25:59', null, null, null);
INSERT INTO `sys_log` VALUES ('c4604787eac94de886bc026c52192964', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:50', null, null, null);
INSERT INTO `sys_log` VALUES ('c4c82124139a4691ac88c8da4adb8825', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B323B, null, null, '2017-09-01 14:33:30', null, null, null);
INSERT INTO `sys_log` VALUES ('c54b7e810b89446a9d58f0b545dab519', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5AEA2E688B7E7ABAF222C226964223A22222C226C6162656C223A22E9AB98E88083E5BF97E684BF222C2272656D61726B73223A22222C22736F7274223A34302C2274797065223A22427573696E65737354797065222C2276616C7565223A2238313934227D3B, null, null, '2017-09-01 11:38:25', null, null, null);
INSERT INTO `sys_log` VALUES ('c69ac4e052cd49099fd7668378723188', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:17:51', null, null, null);
INSERT INTO `sys_log` VALUES ('c7bfbc78df324ba2bbd252159b7be261', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:10:18', null, null, null);
INSERT INTO `sys_log` VALUES ('c93c557e5de848f18df784c9fa20ba09', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:23', null, null, null);
INSERT INTO `sys_log` VALUES ('cad1faa0e43c43c59754c03aa8bbed6c', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:30:58', null, null, null);
INSERT INTO `sys_log` VALUES ('cad9e795ec394de6b8303298d245016a', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:12', null, null, null);
INSERT INTO `sys_log` VALUES ('caf9b40630004890b64b0658387a48fc', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:24:44', null, null, null);
INSERT INTO `sys_log` VALUES ('cc343151ee1f43b1b0c82089a23a6822', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27494F53277D222C2264656661756C7446696C746572223A302C226663617073223A312C226672616D654361706163697479223A302C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A302C226C6F676963223A312C226E6F7465223A2262616E6E6572E9AB98E88083222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083222C2274706C6964223A307D3B, null, null, '2017-09-01 11:47:04', null, null, null);
INSERT INTO `sys_log` VALUES ('cc34e7a0310b460fb5ff0cbd157eb662', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223264366136666538316365633436356638393231313036383663653665356431223B323B, null, null, '2017-09-01 14:05:13', null, null, null);
INSERT INTO `sys_log` VALUES ('cc7e3c71718a4404b7139a4b74d84273', '菜单管理-删除菜单', 'com.lexue.sso.web.controller.MenuController.delete()', null, 'http://localhost:8080/menu/delete', 'admin', '0:0:0:0:0:0:0:1', 0x223133223B, null, null, '2017-09-01 11:02:58', null, null, null);
INSERT INTO `sys_log` VALUES ('ccbed00319ec4a9ba91100fa14ea4d19', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:33', null, null, null);
INSERT INTO `sys_log` VALUES ('ce68857d494a4cfb8b69444099ddac51', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:50:06', null, null, null);
INSERT INTO `sys_log` VALUES ('cede5db1c71144d089a8967147caeeca', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E68EA8E98081E5B9B3E58FB0222C226964223A223362313530636136616466353461643361626164363135313938626539363764222C226C6162656C223A22E5AE89E58D93222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A22706C616E74666F726D73222C2276616C7565223A22416E64726F6964227D3B, null, null, '2017-09-01 11:40:37', null, null, null);
INSERT INTO `sys_log` VALUES ('cf38edabb2424491b8e8d5a6ec818c04', '广告模板管理-保存广告模板', 'com.lexue.sso.web.controller.AdTemplateController.save()', null, 'http://localhost:8080/adTpl/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22626F784964223A332C22636F6E646974696F6E4D6170223A227B276F73273A27494F53277D222C2264656661756C7446696C746572223A302C226663617073223A312C2266696C7465724964223A312C226672616D654361706163697479223A312C226672616D655069636B54797065223A302C226672616D6553776974636854696D65223A352C226C6F676963223A312C226E6F7465223A22E88BB9E69E9C222C227072696F72697479223A302C22737461747573223A322C227469746C65223A2262616E6E6572E9AB98E88083E88BB9E69E9C222C2274706C6964223A317D3B, null, null, '2017-09-01 11:56:18', null, null, null);
INSERT INTO `sys_log` VALUES ('d020c29a6c704c02b4eeef60e94311dc', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:23:09', null, null, null);
INSERT INTO `sys_log` VALUES ('d08dc8d6a4484f4ba3f0566fe6c9529e', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:15', null, null, null);
INSERT INTO `sys_log` VALUES ('d151846c4492472bb0cc37cd6d67dd4c', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:44', null, null, null);
INSERT INTO `sys_log` VALUES ('d17cc445f11e410ca621c9af31618f69', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:15:08', null, null, null);
INSERT INTO `sys_log` VALUES ('d3321acc54b2478c9415cedfd728b989', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223434373138396334333961383438313538633730376463363333636431623666223B313B, null, null, '2017-09-01 14:10:14', null, null, null);
INSERT INTO `sys_log` VALUES ('d3ce4e6d9feb4668ade7f767d61046a2', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:30:27', null, null, null);
INSERT INTO `sys_log` VALUES ('d494da4fc0fd456fa8832c69587dfcfe', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:36:08', null, null, null);
INSERT INTO `sys_log` VALUES ('d50f22ff6ff14c85aea238237aa3517d', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:16:26', null, null, null);
INSERT INTO `sys_log` VALUES ('d52ee1eb507e480dbbbdd010cd77d745', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:41:14', null, null, null);
INSERT INTO `sys_log` VALUES ('d58955d3b33a48538bf9f371c5327a8c', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:18:04', null, null, null);
INSERT INTO `sys_log` VALUES ('d6953912e9154600a8836cbc2efc8d75', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A22222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A22E4B8ADE88083E8A786E9A291E5898DE5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 11:45:26', null, null, null);
INSERT INTO `sys_log` VALUES ('d6986381a5eb491ba428896814473980', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F6164426F782F6C697374222C226964223A223135222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E5B9BFE5918AE4BD8D222C22706172656E744964223A2234222C227065726D697373696F6E223A227379733A6164626F783A6C697374222C2272656D61726B73223A22222C22736F7274223A307D3B, null, null, '2017-09-01 11:17:56', null, null, null);
INSERT INTO `sys_log` VALUES ('d70ab61094c2493d97e81a2e3d6b2ec1', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:05:05', null, null, null);
INSERT INTO `sys_log` VALUES ('d79cf9dd5d3a48bb8ede55f71c26018b', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:08', null, null, null);
INSERT INTO `sys_log` VALUES ('d7ded7bbfcb6486aa59abcb1a72fdee9', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A226333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:21:41', null, null, null);
INSERT INTO `sys_log` VALUES ('d8055010de984f0fa188646293ee834c', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223264366136666538316365633436356638393231313036383663653665356431223B323B, null, null, '2017-09-01 14:16:13', null, null, null);
INSERT INTO `sys_log` VALUES ('d867f336f0284dfe9aeb54d02111effc', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A226333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:16:28', null, null, null);
INSERT INTO `sys_log` VALUES ('d88da8f5085849e29e6aa5774a917ff6', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:03:02', null, null, null);
INSERT INTO `sys_log` VALUES ('dac144fd40804807988414d001c1e81a', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:24:55', null, null, null);
INSERT INTO `sys_log` VALUES ('dbde8dc7299547da86533c3bd07f20c6', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:28', null, null, null);
INSERT INTO `sys_log` VALUES ('dd0a8b45eb1c4f4c973a9af4d4d169cb', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:32:06', null, null, null);
INSERT INTO `sys_log` VALUES ('dddc6fdfd1c040e1bfad753ed65861c4', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:22:06', null, null, null);
INSERT INTO `sys_log` VALUES ('dde9ccc716134bf7a6b3b70b83b450f5', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A22222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A22E9AB98E88083E8A786E9A291E5898DE5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 11:45:15', null, null, null);
INSERT INTO `sys_log` VALUES ('de318ffdb2b2411fba0516136b704d77', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x223031663166313765333763353465346561626361373933386361333736393938223B7B7D3B, null, null, '2017-09-01 11:56:10', null, null, null);
INSERT INTO `sys_log` VALUES ('ded2266d482a46deb693ae4a29d900c6', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:56:22', null, null, null);
INSERT INTO `sys_log` VALUES ('df77cfe0fb3e4e2484e7ca5f706fbda2', '广告资源管理-新增广告资源', 'com.lexue.sso.web.controller.AdResourceController.add()', null, 'http://localhost:8080/adRes/add', 'admin', '0:0:0:0:0:0:0:1', 0x226333643035326265653839393435366262623561373364653137643438633962223B7B7D3B, null, null, '2017-09-01 14:21:39', null, null, null);
INSERT INTO `sys_log` VALUES ('e00ad3a8f10b43d9bef993afced54f34', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:05:03', null, null, null);
INSERT INTO `sys_log` VALUES ('e0a4ca91896f49ae87af5bfde5915c4b', '字典管理-字典列表', 'com.lexue.sso.web.controller.DictController.index()', null, 'http://localhost:8080/dict/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:33:05', null, null, null);
INSERT INTO `sys_log` VALUES ('e1dbc86e20934485a6942a270623d145', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:25:16', null, null, null);
INSERT INTO `sys_log` VALUES ('e412ffcfb8a949c5abb7a3a65ba8b554', '广告帧管理-更新广告状态', 'com.lexue.sso.web.controller.AdFrameController.updateStatus()', null, 'http://localhost:8080/adFrame/updateStatus', 'admin', '0:0:0:0:0:0:0:1', 0x223264366136666538316365633436356638393231313036383663653665356431223B313B, null, null, '2017-09-01 14:10:17', null, null, null);
INSERT INTO `sys_log` VALUES ('e42e78a4bae74253bb2e5b904126ecc8', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:30:23', null, null, null);
INSERT INTO `sys_log` VALUES ('e44932ebf37b435092effcf728d83dfe', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:22', null, null, null);
INSERT INTO `sys_log` VALUES ('e472b641123449f1a3af26f7cea8dd5f', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F6164426F782F73617665222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E4BFAEE694B9222C22706172656E744964223A223135222C227065726D697373696F6E223A227379733A6164626F783A73617665222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:22:06', null, null, null);
INSERT INTO `sys_log` VALUES ('e4d26ce48d694acd9fff8ef2ac460743', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x226164343737356133393531313465396139323236323238313633616266643036223B7B7D3B, null, null, '2017-09-01 14:24:10', null, null, null);
INSERT INTO `sys_log` VALUES ('e4d9185f04fa4022b7da0895314aef16', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:27:07', null, null, null);
INSERT INTO `sys_log` VALUES ('e840d19d08384a829f5dbbd0b74038ca', '消息推送管理-消息推送列表', 'com.lexue.sso.web.controller.PushMessageController.index()', null, 'http://localhost:8080/push/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:27:03', null, null, null);
INSERT INTO `sys_log` VALUES ('e85fae07551048c6ad2f764edfbb9aad', '广告位管理-添加广告位', 'com.lexue.sso.web.controller.AdBoxsController.add()', null, 'http://localhost:8080/adBox/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:30:35', null, null, null);
INSERT INTO `sys_log` VALUES ('e96c893e2da542ba85e77651dc595a8e', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F616454706C2F6C697374222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E5B9BFE5918AE6A8A1E69DBF222C22706172656E744964223A2234222C227065726D697373696F6E223A227379733A616474706C3A6C697374222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:18:49', null, null, null);
INSERT INTO `sys_log` VALUES ('ea90fc63530a4aa2badd4c8751a111c2', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 13:56:26', null, null, null);
INSERT INTO `sys_log` VALUES ('ec5985bf0fa945b6bacaef8a341d51f1', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:04:59', null, null, null);
INSERT INTO `sys_log` VALUES ('ec82578384d7402ca66319bb7033f0a2', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:16:10', null, null, null);
INSERT INTO `sys_log` VALUES ('eccb15c4c200443abdadee9a60c820ad', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:45:36', null, null, null);
INSERT INTO `sys_log` VALUES ('ecf4cebd801a46cb930732ffd41fefec', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:17:03', null, null, null);
INSERT INTO `sys_log` VALUES ('ed2d8b2d82074f49bab9fb996778c385', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:15:05', null, null, null);
INSERT INTO `sys_log` VALUES ('ed5759fa08c44b84824c1384ad81ef67', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:30:31', null, null, null);
INSERT INTO `sys_log` VALUES ('ed640ba2058a4b108f4979b72e87f475', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:15:43', null, null, null);
INSERT INTO `sys_log` VALUES ('ed8537b7004d46cea4bf5cc20f4e0d3c', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E69DA1E79BAEE7B1BBE59E8B222C226964223A22222C226C6162656C223A22E69687E5AD97E6A8AAE68E92222C2272656D61726B73223A22222C22736F7274223A35302C2274797065223A226672616D6554797065222C2276616C7565223A2235227D3B, null, null, '2017-09-01 11:34:55', null, null, null);
INSERT INTO `sys_log` VALUES ('ee60a578b19f4b1bb33883a50f73fa1b', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:15:17', null, null, null);
INSERT INTO `sys_log` VALUES ('eff52b0ed7724b818049f4b9039472d9', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:45:15', null, null, null);
INSERT INTO `sys_log` VALUES ('f0d8a28340134aeab51551f1e62e9c43', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A22222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39366636366533383137303930312E6A7067222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E657232222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 11:52:04', null, null, null);
INSERT INTO `sys_log` VALUES ('f113aaa90fe54fa2a104e1b7fcf2cdc3', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:21:41', null, null, null);
INSERT INTO `sys_log` VALUES ('f1dd810a2da3461aa31c1988212c4d0b', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:22:58', null, null, null);
INSERT INTO `sys_log` VALUES ('f20d8f1dcb7e4479b25576ee9886b7a5', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:18:02', null, null, null);
INSERT INTO `sys_log` VALUES ('f2f3e1dd85ea496eb8f5871553f9e5d4', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 13:57:38', null, null, null);
INSERT INTO `sys_log` VALUES ('f326aeb0538f453b9bad23651a6a6291', '广告位管理-保存广告位', 'com.lexue.sso.web.controller.AdBoxsController.save()', null, 'http://localhost:8080/adBox/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22636F6D70616E79223A312C2266696C74657273223A5B5D2C226964223A22222C226E6F7465223A22222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E6572E5B9BFE5918AE4BD8D222C22756E6974223A302C2275706461746554696D65223A302C2275706964223A302C2276696577486569676874223A302C227669657754696D65223A302C22766965775769647468223A307D3B, null, null, '2017-09-01 11:45:36', null, null, null);
INSERT INTO `sys_log` VALUES ('f49bf23ef42345fca428d8d952c50f0d', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5AEBDE9AB98E58D95E4BD8D222C226964223A22222C226C6162656C223A22E7A385222C2272656D61726B73223A22222C22736F7274223A32302C2274797065223A22636F6D70616E79222C2276616C7565223A2232227D3B, null, null, '2017-09-01 11:33:12', null, null, null);
INSERT INTO `sys_log` VALUES ('f5eedd6486f84769b626eef87b4bda57', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22E5B8A7E98089E58F96E7B1BBE59E8B222C226964223A22222C226C6162656C223A22E585A8E983A8222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A226974656D5069636B54797065222C2276616C7565223A2231227D3B, null, null, '2017-09-01 11:33:45', null, null, null);
INSERT INTO `sys_log` VALUES ('f5f426497e4448aab9886312ef632713', '字典管理-新增修改字典', 'com.lexue.sso.web.controller.DictController.save()', null, 'http://localhost:8080/dict/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226465736372697074696F6E223A22427573696E65737354797065222C226964223A22222C226C6162656C223A22E4B8ADE88083222C2272656D61726B73223A22222C22736F7274223A31302C2274797065223A22E68EA8E98081E5AEA2E688B7E7ABAF222C2276616C7565223A2234303937227D3B, null, null, '2017-09-01 11:37:21', null, null, null);
INSERT INTO `sys_log` VALUES ('f67b195f2cd84199a76e40bea49e9805', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F616454706C2F616464222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E6B7BBE58AA0222C22706172656E744964223A226661333861346166373830393466333362353539373830613632616136383039222C227065726D697373696F6E223A227379733A616474706C3A616464222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:25:36', null, null, null);
INSERT INTO `sys_log` VALUES ('f6ccca07f4654dd88243c678f21aab07', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D2C687474703A2F2F7777772E6C657875652E636F6D222C226964223A2263336430353262656538393934353662626235613733646531376434386339622C6333643035326265653839393435366262623561373364653137643438633962222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A70672C687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A312C227469746C65223A2262616E6E6572312C62616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 14:17:16', null, null, null);
INSERT INTO `sys_log` VALUES ('f753c78caefb45acb772aeb40972fe4b', '消息推送管理-新增消息推送', 'com.lexue.sso.web.controller.PushMessageController.add()', null, 'http://localhost:8080/push/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:57:29', null, null, null);
INSERT INTO `sys_log` VALUES ('f77b871d3ef14f5cb05202280b1816cc', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:22:26', null, null, null);
INSERT INTO `sys_log` VALUES ('fa12ccd8739f4633ade743fa45b701f2', '广告模板管理-新增广告模板', 'com.lexue.sso.web.controller.AdTemplateController.add()', null, 'http://localhost:8080/adTpl/add', 'admin', '0:0:0:0:0:0:0:1', 0x226164343737356133393531313465396139323236323238313633616266643036223B7B7D3B, null, null, '2017-09-01 14:22:10', null, null, null);
INSERT INTO `sys_log` VALUES ('fa329a418ae14e27ba8b3273cfe0aa66', '广告帧管理-新增广告帧', 'com.lexue.sso.web.controller.AdFrameController.add()', null, 'http://localhost:8080/adFrame/add', 'admin', '0:0:0:0:0:0:0:1', 0x6E756C6C3B7B7D3B, null, null, '2017-09-01 11:54:30', null, null, null);
INSERT INTO `sys_log` VALUES ('fa870721b0c9443582688431b4d44fb2', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 13:57:15', null, null, null);
INSERT INTO `sys_log` VALUES ('fabd1ea4800c4f46a8ddfe9e950b5589', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:24:00', null, null, null);
INSERT INTO `sys_log` VALUES ('fac8fb13c36746058d69b6a72440dad4', '广告资源管理-广告资源列表', 'com.lexue.sso.web.controller.AdResourceController.index()', null, 'http://localhost:8080/adRes/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:15:07', null, null, null);
INSERT INTO `sys_log` VALUES ('fb84856e4fa44ab48f67920e12c47978', '菜单管理-新增修改菜单', 'com.lexue.sso.web.controller.MenuController.save()', null, 'http://localhost:8080/menu/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B226368696E644D656E75223A5B5D2C2268726566223A222F61645265732F6C697374222C226964223A22222C22697353686F77223A2231222C226C6576656C223A302C226E616D65223A22E5B9BFE5918AE8B584E6BA90E7AEA1E79086222C22706172656E744964223A2234222C227065726D697373696F6E223A227379733A61647265733A6C697374222C2272656D61726B73223A22222C22736F7274223A31307D3B, null, null, '2017-09-01 11:20:02', null, null, null);
INSERT INTO `sys_log` VALUES ('fc42b195e1f84b0cb91564ff010711a2', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:18:07', null, null, null);
INSERT INTO `sys_log` VALUES ('fcaa09e5a87d443ea29354899ac99167', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:33:28', null, null, null);
INSERT INTO `sys_log` VALUES ('fcd8491f28e44957ab5eefb703707dbf', '菜单管理-菜单列表', 'com.lexue.sso.web.controller.MenuController.index()', null, 'http://localhost:8080/menu/list', 'admin', '0:0:0:0:0:0:0:1', 0x7B7D3B, null, null, '2017-09-01 11:03:06', null, null, null);
INSERT INTO `sys_log` VALUES ('fd11d3b005d1446aa9517902c6250041', '广告帧管理-广告帧列表', 'com.lexue.sso.web.controller.AdFrameController.index()', null, 'http://localhost:8080/adFrame/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:27:06', null, null, null);
INSERT INTO `sys_log` VALUES ('fdc278ae869e48aaaef37267200cc6e3', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:30:23', null, null, null);
INSERT INTO `sys_log` VALUES ('fdfbbf8084e543f288ede64bb15bbfa6', '广告模板管理-广告模板列表', 'com.lexue.sso.web.controller.AdTemplateController.index()', null, 'http://localhost:8080/adTpl/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 11:51:08', null, null, null);
INSERT INTO `sys_log` VALUES ('fe7031561a4a418db180396555d079a3', '广告资源管理-保存广告资源', 'com.lexue.sso.web.controller.AdResourceController.save()', null, 'http://localhost:8080/adRes/save', 'admin', '0:0:0:0:0:0:0:1', 0x7B22666F727761726455524C223A22687474703A2F2F7777772E6C657875652E636F6D222C226964223A22222C227265736F7572636554797065223A302C227265736F7572636555524C223A22687474703A2F2F6164766F642E6C657875652E636F6D2F61645F706963747572652F39333334383561623137303930312E6A7067222C2273746174223A302C22737461747573223A322C227469746C65223A2262616E6E657231222C2275706461746554696D65223A302C2275706964223A307D3B, null, null, '2017-09-01 11:51:46', null, null, null);
INSERT INTO `sys_log` VALUES ('fee6ae0d1aad4213a7832cbc0cee24c5', '广告位管理-广告位列表', 'com.lexue.sso.web.controller.AdBoxsController.index()', null, 'http://localhost:8080/adBox/list', 'admin', '0:0:0:0:0:0:0:1', '', null, null, '2017-09-01 14:31:00', null, null, null);

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `parent_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '所有父级编号',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  `href` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '链接',
  `target` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `is_show` char(1) COLLATE utf8_bin NOT NULL COMMENT '是否在菜单中显示',
  `permission` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限标识',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_menu_parent_id` (`parent_id`),
  KEY `sys_menu_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('03d41bc52a2d4d9baf6df42029b0f8e2', 'a7fc1474f8b549a0a43ed97b86d03271', null, '开始', '10', '/job/start', null, null, '1', 'sys:job:start', null, '2017-08-01 14:12:50', null, '2017-08-01 14:12:50', '', '0');
INSERT INTO `sys_menu` VALUES ('1', '0', '0', '系统管理', '0', null, null, null, '1', null, '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('10', '2', '0', '微信组管理', '0', '/wx/group', null, null, '1', 'sys:group:list', '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('11', '2', '0', '微信群管理', '0', '/wx/list', null, null, '1', 'sys:wx:list', '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('14a2fd1e452a42259988247d8c665863', '7', null, '删除', '10', '/user/delete', null, null, '1', 'sys:user:delete', null, '2017-07-26 16:42:19', null, '2017-07-26 16:42:19', '', '0');
INSERT INTO `sys_menu` VALUES ('15', '4', '0', '广告位', '0', '/adBox/list', null, null, '1', 'sys:adbox:list', '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('170eda06535b43679b2f85fda205f0ed', 'a7fc1474f8b549a0a43ed97b86d03271', null, '删除', '10', '/job/delete', null, null, '1', 'sys:job:delete', null, '2017-07-31 15:10:11', null, '2017-07-31 15:10:11', '', '0');
INSERT INTO `sys_menu` VALUES ('1d35b2499673492b86abf093c7cdb470', 'fa38a4af78094f33b559780a62aa6809', null, '添加', '10', '/adTpl/add', null, null, '1', 'sys:adtpl:add', null, '2017-09-01 11:25:36', null, '2017-09-01 11:25:36', '', '0');
INSERT INTO `sys_menu` VALUES ('1ea3557ba82d47279ba7f517ee5a1eec', '6', null, '删除', '10', '/menu/delete', null, null, '1', 'sys:menu:delete', null, '2017-07-26 16:41:33', null, '2017-07-26 16:41:33', '删除', '0');
INSERT INTO `sys_menu` VALUES ('2', '0', '0', '内容管理', '0', null, null, null, '1', null, '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('20505c9a55864920aebd7b026f211ecb', '6de0c6fe441c465f9f10b5f0c16b7e31', null, '删除', '10', '/role/delete', null, null, '1', 'sys:role:delete', null, '2017-07-26 16:41:55', null, '2017-07-26 16:41:55', '', '0');
INSERT INTO `sys_menu` VALUES ('2d60de1e635e4c81a4f62bc553061553', '9', null, '删除', '10', '/push/delete', null, null, '1', 'sys:push:delete', null, '2017-09-01 11:21:18', null, '2017-09-01 11:21:18', '', '0');
INSERT INTO `sys_menu` VALUES ('312331ab3eff48cb9df551372662ea77', '6de0c6fe441c465f9f10b5f0c16b7e31', null, '添加', '10', '/user/add', null, null, '1', 'sys:role:add', null, '2017-07-26 16:20:32', null, '2017-07-26 16:20:32', '添加', '0');
INSERT INTO `sys_menu` VALUES ('3b7565f1c2b54ad9b32c762bc4bfe3c6', '15', null, '添加', '10', '/adBox/add', null, null, '1', 'sys:adbox:add', null, '2017-09-01 11:21:44', null, '2017-09-01 11:21:44', '', '0');
INSERT INTO `sys_menu` VALUES ('4', '0', '0', '广告管理', '0', null, null, null, '1', null, '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('40a6c00dc85f48bc95ae78967f66baa1', 'a7fc1474f8b549a0a43ed97b86d03271', null, '修改', '10', '/job/save', null, null, '1', 'sys:job:save', null, '2017-07-31 15:09:39', null, '2017-07-31 15:09:39', '', '0');
INSERT INTO `sys_menu` VALUES ('4499011b55e04fd5a4944724695a4ef3', 'd12cc000ad7a469298a0a4159a89910e', null, '查看', '10', '/dict/list', null, null, '1', 'sys:dict:list', null, '2017-07-26 17:16:33', null, '2017-07-26 17:16:33', '', '0');
INSERT INTO `sys_menu` VALUES ('467f2ca6925d48c19e21da196a52ca37', '8ad5ea23eec04f86beef36914fe7b7e6', null, '添加', '10', '/adFrame/add', null, null, '1', 'sys:adframe:add', null, '2017-09-01 11:23:09', null, '2017-09-01 11:23:09', '', '0');
INSERT INTO `sys_menu` VALUES ('4ed8ed8c7c37412f87e7b89752226df9', 'fa38a4af78094f33b559780a62aa6809', null, '修改', '10', '/adTpl/save', null, null, '1', 'sys:adtpl:save', null, '2017-09-01 11:25:59', null, '2017-09-01 11:25:59', '', '0');
INSERT INTO `sys_menu` VALUES ('5', '1', '0', '日志管理', '0', '/log/list', null, null, '1', 'sys:log:list', '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', 'sdf', '0');
INSERT INTO `sys_menu` VALUES ('6', '1', '0', '菜单管理', '0', '/menu/list', null, null, '1', 'sys:menu:list', '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('680e9f550d244869b8db07c0af9f6768', '7', null, '添加', '10', '/user/add', null, null, '1', 'sys:user:add', null, '2017-07-26 16:42:42', null, '2017-07-26 16:42:42', '', '0');
INSERT INTO `sys_menu` VALUES ('6de0c6fe441c465f9f10b5f0c16b7e31', '1', null, '角色管理', '10', '/role/list', null, null, '1', 'sys:role:list', null, '2017-07-24 10:06:21', null, '2017-07-24 10:06:21', '角色管理', '0');
INSERT INTO `sys_menu` VALUES ('7', '1', '0', '用户管理', '0', '/user/list', null, null, '1', 'sys:user:list', '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('7231f4789a7e48df8aa5c6e8e918b4c0', '9', null, '添加', '10', '/push/add', null, null, '1', 'sys:push:add', null, '2017-09-01 11:20:33', null, '2017-09-01 11:20:33', '', '0');
INSERT INTO `sys_menu` VALUES ('760e82c0d49a47c7a0bd3ce818095e6e', '7', null, '修改', '10', '/user/save', null, null, '1', 'sys:user:save', null, '2017-07-26 16:43:08', null, '2017-07-26 16:43:08', '', '0');
INSERT INTO `sys_menu` VALUES ('7a376d2845ad4a3889ec62bd10393ebd', 'e0b6fb0d5b45415fa9703dc44a810708', null, '添加', '10', '/adRes/add', null, null, '1', 'sys:adres:add', null, '2017-09-01 11:24:20', null, '2017-09-01 11:24:20', '', '0');
INSERT INTO `sys_menu` VALUES ('7a91ed3a498a4b79be347122c8f80586', '8ad5ea23eec04f86beef36914fe7b7e6', null, '删除', '10', '/adFrame/delete', null, null, '1', 'sys:adframe:delete', null, '2017-09-01 11:24:00', null, '2017-09-01 11:24:00', '', '0');
INSERT INTO `sys_menu` VALUES ('7fe8f33319304c8e8e6547e2a9ccd50a', '6', null, '添加', '10', '/menu/add', null, null, '1', 'sys:menu:add', null, '2017-07-26 16:40:36', null, '2017-07-26 16:40:36', '添加', '0');
INSERT INTO `sys_menu` VALUES ('8ad5ea23eec04f86beef36914fe7b7e6', '4', null, '模板帧管理', '10', '/adFrame/list', null, null, '1', 'sys:adframe:list', null, '2017-09-01 11:19:32', null, '2017-09-01 11:19:32', '', '0');
INSERT INTO `sys_menu` VALUES ('9', '2', '0', '消息推送', '0', '/push/list', null, null, '1', 'sys:push:list', '1', '2017-07-19 11:11:11', '1', '2017-07-19 11:11:11', '', '0');
INSERT INTO `sys_menu` VALUES ('95891679f95348e4a9d78b2818bb13c1', 'd12cc000ad7a469298a0a4159a89910e', null, '修改', '10', '/dict/save', null, null, '1', 'sys:dict:save', null, '2017-07-26 16:44:21', null, '2017-07-26 16:44:21', '', '0');
INSERT INTO `sys_menu` VALUES ('a14da4edea25430f88ebc42f4b06e190', 'd12cc000ad7a469298a0a4159a89910e', null, '删除', '10', '/dict/delete', null, null, '1', 'sys:dict:delete', null, '2017-07-26 16:43:31', null, '2017-07-26 16:43:31', '', '0');
INSERT INTO `sys_menu` VALUES ('a162b0178f1847b38a088e53dd14da61', '15', null, '修改', '10', '/adBox/save', null, null, '1', 'sys:adbox:save', null, '2017-09-01 11:22:06', null, '2017-09-01 11:22:06', '', '0');
INSERT INTO `sys_menu` VALUES ('a2b5b8ecd10346f7b7c0eee181efc1af', 'd12cc000ad7a469298a0a4159a89910e', null, '添加', '10', '/dict/add', null, null, '1', 'sys:dict:add', null, '2017-07-26 16:43:55', null, '2017-07-26 16:43:55', '', '0');
INSERT INTO `sys_menu` VALUES ('a60aacebb22a44cfa2b33e193fe20945', '8ad5ea23eec04f86beef36914fe7b7e6', null, '修改', '10', '/adFrame/save', null, null, '1', 'sys:adframe:save', null, '2017-09-01 11:23:35', null, '2017-09-01 11:23:35', '', '0');
INSERT INTO `sys_menu` VALUES ('a7900270c2b64bb594873606aaccbcee', 'e0b6fb0d5b45415fa9703dc44a810708', null, '修改', '10', '/adRes/save', null, null, '1', 'sys:adres:save', null, '2017-09-01 11:24:44', null, '2017-09-01 11:24:44', '', '0');
INSERT INTO `sys_menu` VALUES ('a7fc1474f8b549a0a43ed97b86d03271', '1', null, '定时任务', '10', '/job/list', null, null, '1', 'sys:job:list', null, '2017-07-27 16:44:41', null, '2017-07-27 16:44:41', '', '0');
INSERT INTO `sys_menu` VALUES ('a82906a8a81e4d58a35f5dadb8471d1d', '7', null, '查看', '10', '/user/list', null, null, '1', 'sys:user:list', null, '2017-07-26 17:16:03', null, '2017-07-26 17:16:03', '', '0');
INSERT INTO `sys_menu` VALUES ('b3356dc52ef74b2685784b5e1bd3bccc', '15', null, '删除', '10', '/adBox/delete', null, null, '1', 'sys:adbox:delete', null, '2017-09-01 11:22:26', null, '2017-09-01 11:22:26', '', '0');
INSERT INTO `sys_menu` VALUES ('b646a90f162d4da58b7e36b96ca641fc', 'a7fc1474f8b549a0a43ed97b86d03271', null, '暂停', '10', '/job/stop', null, null, '1', 'sys:job:stop', null, '2017-08-01 14:13:32', null, '2017-08-01 14:13:32', '', '0');
INSERT INTO `sys_menu` VALUES ('bc3c3b3817a842f6a10a9d6b38d6ae64', 'a7fc1474f8b549a0a43ed97b86d03271', null, '添加', '10', '/job/add', null, null, '1', 'sys:job:add', null, '2017-07-31 15:09:13', null, '2017-07-31 15:09:13', '', '0');
INSERT INTO `sys_menu` VALUES ('bd953d739b1649ae9fc326a3bff87eed', 'e0b6fb0d5b45415fa9703dc44a810708', null, '删除', '10', '/adRes/delete', null, null, '1', 'sys:adres:delete', null, '2017-09-01 11:25:12', null, '2017-09-01 11:25:12', '', '0');
INSERT INTO `sys_menu` VALUES ('c4167b6f933b47babdb0982caec3ce8e', 'a7fc1474f8b549a0a43ed97b86d03271', null, '查看', '10', '/job/list', null, null, '1', 'sys:job:list', null, '2017-07-31 15:08:22', null, '2017-07-31 15:08:22', '', '0');
INSERT INTO `sys_menu` VALUES ('ccf16c915f7e4ba893e0da5e91a3e56f', 'fa38a4af78094f33b559780a62aa6809', null, '删除', '10', '/adTpl/delete', null, null, '1', 'sys:adtpl:delete', null, '2017-09-01 11:26:17', null, '2017-09-01 11:26:17', '', '0');
INSERT INTO `sys_menu` VALUES ('d12cc000ad7a469298a0a4159a89910e', '1', null, '字典管理', '10', '/dict/list', null, null, '1', 'sys:dict:list', null, '2017-07-24 11:02:46', null, '2017-07-24 11:02:46', 'sdfsdf', '0');
INSERT INTO `sys_menu` VALUES ('dbddee012f4449bca860cff038342f63', '6de0c6fe441c465f9f10b5f0c16b7e31', null, '修改', '10', '/role/save', null, null, '1', 'sys:role:save', null, '2017-07-26 16:39:28', null, '2017-07-26 16:39:28', '修改', '0');
INSERT INTO `sys_menu` VALUES ('dcdb095d688d4a7ca9d53e7ebbd8d94b', '6', null, '查看', '10', '/menu/list', null, null, '1', 'sys:menu:list', null, '2017-07-26 17:13:21', null, '2017-07-26 17:13:21', '', '0');
INSERT INTO `sys_menu` VALUES ('e0b6fb0d5b45415fa9703dc44a810708', '4', null, '广告资源管理', '10', '/adRes/list', null, null, '1', 'sys:adres:list', null, '2017-09-01 11:20:02', null, '2017-09-01 11:20:02', '', '0');
INSERT INTO `sys_menu` VALUES ('e5a8bcbe691a49caa8163b34037ff538', '6de0c6fe441c465f9f10b5f0c16b7e31', null, '查看', '10', '/role/list', null, null, '1', 'sys:role:list', null, '2017-07-26 17:15:32', null, '2017-07-26 17:15:32', '', '0');
INSERT INTO `sys_menu` VALUES ('eb2064b5111a4d3e943305c1c0814289', '5', null, '查看', '10', '/log/list', null, null, '1', 'sys:log:list', null, '2017-07-31 15:10:41', null, '2017-07-31 15:10:41', '', '0');
INSERT INTO `sys_menu` VALUES ('f2f5c89baaeb450e90b64a4de8d110d4', '9', null, '修改', '10', '/push/save', null, null, '1', 'sys:push:save', null, '2017-09-01 11:20:59', null, '2017-09-01 11:20:59', '', '0');
INSERT INTO `sys_menu` VALUES ('f9de26426e5d41bf9b577248ce0fa65c', '6', null, '修改', '10', '/menu/save', null, null, '1', 'sys:menu:save', null, '2017-07-26 16:41:04', null, '2017-07-26 16:41:04', '修改', '0');
INSERT INTO `sys_menu` VALUES ('fa38a4af78094f33b559780a62aa6809', '4', null, '广告模板', '10', '/adTpl/list', null, null, '1', 'sys:adtpl:list', null, '2017-09-01 11:18:49', null, '2017-09-01 11:18:49', '', '0');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `useable` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '是否可用',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_role_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO `sys_role` VALUES ('2', '公司管理员', '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO `sys_role` VALUES ('4', '部门管理员', '2', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');
INSERT INTO `sys_role` VALUES ('6', '普通用户', '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '', '0');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '角色编号',
  `menu_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '菜单编号',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色-菜单';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '03d41bc52a2d4d9baf6df42029b0f8e2');
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '14a2fd1e452a42259988247d8c665863');
INSERT INTO `sys_role_menu` VALUES ('1', '15');
INSERT INTO `sys_role_menu` VALUES ('1', '170eda06535b43679b2f85fda205f0ed');
INSERT INTO `sys_role_menu` VALUES ('1', '1d35b2499673492b86abf093c7cdb470');
INSERT INTO `sys_role_menu` VALUES ('1', '1ea3557ba82d47279ba7f517ee5a1eec');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '20505c9a55864920aebd7b026f211ecb');
INSERT INTO `sys_role_menu` VALUES ('1', '2d60de1e635e4c81a4f62bc553061553');
INSERT INTO `sys_role_menu` VALUES ('1', '312331ab3eff48cb9df551372662ea77');
INSERT INTO `sys_role_menu` VALUES ('1', '3b7565f1c2b54ad9b32c762bc4bfe3c6');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '40a6c00dc85f48bc95ae78967f66baa1');
INSERT INTO `sys_role_menu` VALUES ('1', '4499011b55e04fd5a4944724695a4ef3');
INSERT INTO `sys_role_menu` VALUES ('1', '467f2ca6925d48c19e21da196a52ca37');
INSERT INTO `sys_role_menu` VALUES ('1', '4ed8ed8c7c37412f87e7b89752226df9');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '680e9f550d244869b8db07c0af9f6768');
INSERT INTO `sys_role_menu` VALUES ('1', '6de0c6fe441c465f9f10b5f0c16b7e31');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '7231f4789a7e48df8aa5c6e8e918b4c0');
INSERT INTO `sys_role_menu` VALUES ('1', '760e82c0d49a47c7a0bd3ce818095e6e');
INSERT INTO `sys_role_menu` VALUES ('1', '7a376d2845ad4a3889ec62bd10393ebd');
INSERT INTO `sys_role_menu` VALUES ('1', '7a91ed3a498a4b79be347122c8f80586');
INSERT INTO `sys_role_menu` VALUES ('1', '7fe8f33319304c8e8e6547e2a9ccd50a');
INSERT INTO `sys_role_menu` VALUES ('1', '8ad5ea23eec04f86beef36914fe7b7e6');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('1', '95891679f95348e4a9d78b2818bb13c1');
INSERT INTO `sys_role_menu` VALUES ('1', 'a14da4edea25430f88ebc42f4b06e190');
INSERT INTO `sys_role_menu` VALUES ('1', 'a162b0178f1847b38a088e53dd14da61');
INSERT INTO `sys_role_menu` VALUES ('1', 'a2b5b8ecd10346f7b7c0eee181efc1af');
INSERT INTO `sys_role_menu` VALUES ('1', 'a60aacebb22a44cfa2b33e193fe20945');
INSERT INTO `sys_role_menu` VALUES ('1', 'a7900270c2b64bb594873606aaccbcee');
INSERT INTO `sys_role_menu` VALUES ('1', 'a7fc1474f8b549a0a43ed97b86d03271');
INSERT INTO `sys_role_menu` VALUES ('1', 'a82906a8a81e4d58a35f5dadb8471d1d');
INSERT INTO `sys_role_menu` VALUES ('1', 'b3356dc52ef74b2685784b5e1bd3bccc');
INSERT INTO `sys_role_menu` VALUES ('1', 'b646a90f162d4da58b7e36b96ca641fc');
INSERT INTO `sys_role_menu` VALUES ('1', 'bc3c3b3817a842f6a10a9d6b38d6ae64');
INSERT INTO `sys_role_menu` VALUES ('1', 'bd953d739b1649ae9fc326a3bff87eed');
INSERT INTO `sys_role_menu` VALUES ('1', 'c4167b6f933b47babdb0982caec3ce8e');
INSERT INTO `sys_role_menu` VALUES ('1', 'ccf16c915f7e4ba893e0da5e91a3e56f');
INSERT INTO `sys_role_menu` VALUES ('1', 'd12cc000ad7a469298a0a4159a89910e');
INSERT INTO `sys_role_menu` VALUES ('1', 'dbddee012f4449bca860cff038342f63');
INSERT INTO `sys_role_menu` VALUES ('1', 'dcdb095d688d4a7ca9d53e7ebbd8d94b');
INSERT INTO `sys_role_menu` VALUES ('1', 'e0b6fb0d5b45415fa9703dc44a810708');
INSERT INTO `sys_role_menu` VALUES ('1', 'e5a8bcbe691a49caa8163b34037ff538');
INSERT INTO `sys_role_menu` VALUES ('1', 'eb2064b5111a4d3e943305c1c0814289');
INSERT INTO `sys_role_menu` VALUES ('1', 'f2f5c89baaeb450e90b64a4de8d110d4');
INSERT INTO `sys_role_menu` VALUES ('1', 'f9de26426e5d41bf9b577248ce0fa65c');
INSERT INTO `sys_role_menu` VALUES ('1', 'fa38a4af78094f33b559780a62aa6809');
INSERT INTO `sys_role_menu` VALUES ('2', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '10');
INSERT INTO `sys_role_menu` VALUES ('2', '11');
INSERT INTO `sys_role_menu` VALUES ('2', '12');
INSERT INTO `sys_role_menu` VALUES ('2', '13');
INSERT INTO `sys_role_menu` VALUES ('2', '14');
INSERT INTO `sys_role_menu` VALUES ('2', '15');
INSERT INTO `sys_role_menu` VALUES ('2', '16');
INSERT INTO `sys_role_menu` VALUES ('2', '17');
INSERT INTO `sys_role_menu` VALUES ('2', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '3');
INSERT INTO `sys_role_menu` VALUES ('2', '312331ab3eff48cb9df551372662ea77');
INSERT INTO `sys_role_menu` VALUES ('2', '4');
INSERT INTO `sys_role_menu` VALUES ('2', '5');
INSERT INTO `sys_role_menu` VALUES ('2', '6');
INSERT INTO `sys_role_menu` VALUES ('2', '680e9f550d244869b8db07c0af9f6768');
INSERT INTO `sys_role_menu` VALUES ('2', '6de0c6fe441c465f9f10b5f0c16b7e31');
INSERT INTO `sys_role_menu` VALUES ('2', '7');
INSERT INTO `sys_role_menu` VALUES ('2', '7fe8f33319304c8e8e6547e2a9ccd50a');
INSERT INTO `sys_role_menu` VALUES ('2', '9');
INSERT INTO `sys_role_menu` VALUES ('2', 'a14da4edea25430f88ebc42f4b06e190');
INSERT INTO `sys_role_menu` VALUES ('2', 'd12cc000ad7a469298a0a4159a89910e');
INSERT INTO `sys_role_menu` VALUES ('4', '10');
INSERT INTO `sys_role_menu` VALUES ('4', '11');
INSERT INTO `sys_role_menu` VALUES ('4', '12');
INSERT INTO `sys_role_menu` VALUES ('4', '13');
INSERT INTO `sys_role_menu` VALUES ('4', '14');
INSERT INTO `sys_role_menu` VALUES ('4', '15');
INSERT INTO `sys_role_menu` VALUES ('4', '2');
INSERT INTO `sys_role_menu` VALUES ('4', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '4');
INSERT INTO `sys_role_menu` VALUES ('4', '9');
INSERT INTO `sys_role_menu` VALUES ('6', '1');
INSERT INTO `sys_role_menu` VALUES ('6', '10');
INSERT INTO `sys_role_menu` VALUES ('6', '11');
INSERT INTO `sys_role_menu` VALUES ('6', '12');
INSERT INTO `sys_role_menu` VALUES ('6', '13');
INSERT INTO `sys_role_menu` VALUES ('6', '14');
INSERT INTO `sys_role_menu` VALUES ('6', '15');
INSERT INTO `sys_role_menu` VALUES ('6', '2');
INSERT INTO `sys_role_menu` VALUES ('6', '3');
INSERT INTO `sys_role_menu` VALUES ('6', '4');
INSERT INTO `sys_role_menu` VALUES ('6', '4499011b55e04fd5a4944724695a4ef3');
INSERT INTO `sys_role_menu` VALUES ('6', '6');
INSERT INTO `sys_role_menu` VALUES ('6', '6de0c6fe441c465f9f10b5f0c16b7e31');
INSERT INTO `sys_role_menu` VALUES ('6', '7');
INSERT INTO `sys_role_menu` VALUES ('6', '9');
INSERT INTO `sys_role_menu` VALUES ('6', 'a82906a8a81e4d58a35f5dadb8471d1d');
INSERT INTO `sys_role_menu` VALUES ('6', 'd12cc000ad7a469298a0a4159a89910e');
INSERT INTO `sys_role_menu` VALUES ('6', 'dcdb095d688d4a7ca9d53e7ebbd8d94b');
INSERT INTO `sys_role_menu` VALUES ('6', 'e5a8bcbe691a49caa8163b34037ff538');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `login_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '登录名',
  `password` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `email` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `login_ip` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '最后登陆IP',
  `login_flag` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '是否可登录',
  `create_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_user_login_name` (`login_name`),
  KEY `sys_user_update_date` (`update_date`),
  KEY `sys_user_del_flag` (`del_flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '51f9e91dd0a1e79602b7d2de282e65bc6dc777eea7fd55b73c70e274', '系统管理员', 'zww11199009@163.com', '0354-5774554', '15209840499', '127.0.0.1', '1', '1', '2013-05-27 08:00:00', '1', '2017-01-15 20:31:55', '最高管理员', '0');
INSERT INTO `sys_user` VALUES ('10', 'jn_jsb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '济南技术部', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('11', 'lc_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '济南历城领导', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('12', 'lx_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '济南历下领导', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('2', 'sd_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '管理员', '', '', '', null, '1', '1', '2013-05-27 08:00:00', '1', '2017-01-15 17:01:12', '', '0');
INSERT INTO `sys_user` VALUES ('26eb69c72a6c44b4a6f5d59c9a7f391f', 'test', '51f9e91dd0a1e79602b7d2de282e65bc6dc777eea7fd55b73c70e274', '测试用户', null, null, null, null, '1', '1', null, null, '2017-07-26 14:54:16', 'cesdsfs', '0');
INSERT INTO `sys_user` VALUES ('3', 'sd_zhb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '综合部', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('4', 'sd_scb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '市场部', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('4c93373a59b44f04b88454569d6cb762', 'test1', '', '测试用户', null, null, null, null, '2', '1', null, null, '2017-07-26 14:56:15', 'v测试用户', '0');
INSERT INTO `sys_user` VALUES ('6', 'sd_yfb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '研发部', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('7', 'jn_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '济南领导', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('7109c86bf5c547ca9da910db81636d6d', 'caowei', '123456', '曹伟', '', '', '', '127.0.0.1', '1', '1', '2017-01-15 17:42:00', '1', '2017-01-15 17:53:14', '', '0');
INSERT INTO `sys_user` VALUES ('8', 'jn_zhb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '济南综合部', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('9', 'jn_scb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '济南市场部', null, null, null, null, '1', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_user` VALUES ('987bc549118445ad939adfeb9210b231', 'sd_zhb', '70eaf2b4220f621046e9b18e7761c15c2e0652b75a525ccf25047008', '综合部', null, null, null, null, '1', '1', null, null, '2017-07-24 11:09:22', 'sdfsdfsd', '0');
INSERT INTO `sys_user` VALUES ('ae9bfc42737c498b87fac1322a78dbc7', '1212121', 'd2fbf7557e00cc143960a5ed38a0b581ef46cb7c0c6a2eeccf215add', 'ceshishsi', '1', null, '21212', null, '2', '1', null, null, '2017-07-20 16:24:50', '222', '0');
INSERT INTO `sys_user` VALUES ('c729025d3920490f9bbea4de35b0fdb3', '332323', '42b5fff3b168716902a32fae9113d36231a3766682526a8d621220c1', '323232', null, null, null, null, '1', '1', null, null, '2017-07-21 11:14:11', '3232', '0');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '用户编号',
  `role_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('04c1b0d3b24f4519b7f1ff746011345b', '1');
INSERT INTO `sys_user_role` VALUES ('069e1ab61b0e4dfaae62a3724b216827', '1');
INSERT INTO `sys_user_role` VALUES ('0f5f02ec2f6a4bcb8c18fe125798d3d9', '1');
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1', '2');
INSERT INTO `sys_user_role` VALUES ('10', '2');
INSERT INTO `sys_user_role` VALUES ('10,', '2');
INSERT INTO `sys_user_role` VALUES ('10,', '4');
INSERT INTO `sys_user_role` VALUES ('10,', '5');
INSERT INTO `sys_user_role` VALUES ('10,', '6');
INSERT INTO `sys_user_role` VALUES ('11', '3');
INSERT INTO `sys_user_role` VALUES ('11,', '3');
INSERT INTO `sys_user_role` VALUES ('11,', '5');
INSERT INTO `sys_user_role` VALUES ('11,', '6');
INSERT INTO `sys_user_role` VALUES ('12', '4');
INSERT INTO `sys_user_role` VALUES ('13', '5');
INSERT INTO `sys_user_role` VALUES ('14', '6');
INSERT INTO `sys_user_role` VALUES ('1529503d82094e86a573e7b73e5b7d93', '1');
INSERT INTO `sys_user_role` VALUES ('1e7b6193409d4b2b918666e6670ec7a4', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1');
INSERT INTO `sys_user_role` VALUES ('21a4192c41a7417d9dabcae28bc5e3f2', '1');
INSERT INTO `sys_user_role` VALUES ('26eb69c72a6c44b4a6f5d59c9a7f391f', '6');
INSERT INTO `sys_user_role` VALUES ('27785e7a67ad435b87691b9654234000', '1');
INSERT INTO `sys_user_role` VALUES ('29297f9c3579409c8395deb12b03b22a', '1');
INSERT INTO `sys_user_role` VALUES ('3', '2');
INSERT INTO `sys_user_role` VALUES ('4', '3');
INSERT INTO `sys_user_role` VALUES ('4da194f5b3c14680baf0d89d985702a6', '1');
INSERT INTO `sys_user_role` VALUES ('4da194f5b3c14680baf0d89d985702a6', '2');
INSERT INTO `sys_user_role` VALUES ('4da194f5b3c14680baf0d89d985702a6', '4');
INSERT INTO `sys_user_role` VALUES ('4da194f5b3c14680baf0d89d985702a6', '6');
INSERT INTO `sys_user_role` VALUES ('4f571fdaa08d4b2aaed210bd4db391da', '1');
INSERT INTO `sys_user_role` VALUES ('5', '2');
INSERT INTO `sys_user_role` VALUES ('5', '4');
INSERT INTO `sys_user_role` VALUES ('5', '7');
INSERT INTO `sys_user_role` VALUES ('575d39dc86b7457f85c2aa49bb269814', '1');
INSERT INTO `sys_user_role` VALUES ('5d3a19d0517645268ab3f5fa650a7639', '1');
INSERT INTO `sys_user_role` VALUES ('6', '5');
INSERT INTO `sys_user_role` VALUES ('6228be719a1647f0a0b135189a51911d', '1');
INSERT INTO `sys_user_role` VALUES ('6d0e741230834416976208510a81b806', '0363ad42b7b34af9b68d6191c3f87991');
INSERT INTO `sys_user_role` VALUES ('7', '2');
INSERT INTO `sys_user_role` VALUES ('7', '7');
INSERT INTO `sys_user_role` VALUES ('7109c86bf5c547ca9da910db81636d6d', '5');
INSERT INTO `sys_user_role` VALUES ('7109c86bf5c547ca9da910db81636d6d', '6');
INSERT INTO `sys_user_role` VALUES ('8', '2');
INSERT INTO `sys_user_role` VALUES ('9', '1');
INSERT INTO `sys_user_role` VALUES ('bc3972d057c54aa08e9e0ecb76e49f95', '1');
INSERT INTO `sys_user_role` VALUES ('bcdeae3da6d9442aa452417f638e562a', '1');
INSERT INTO `sys_user_role` VALUES ('dd3ed3acd4ee437f9945790399395bd3', '1');
INSERT INTO `sys_user_role` VALUES ('e36e14186e0342429f7649da7bdb7c77', '1');

-- ----------------------------
-- Table structure for `tb_adbox`
-- ----------------------------
DROP TABLE IF EXISTS `tb_adbox`;
CREATE TABLE `tb_adbox` (
  `boxid` int(11) NOT NULL AUTO_INCREMENT,
  `stat` tinyint(4) DEFAULT '2',
  `title` varchar(256) DEFAULT NULL,
  `label` varchar(256) DEFAULT NULL,
  `widv` int(11) DEFAULT NULL,
  `heiv` int(11) DEFAULT NULL,
  `unit` tinyint(4) DEFAULT NULL,
  `vwtm` int(11) DEFAULT NULL,
  `note` varchar(2048) DEFAULT NULL,
  `uptime` bigint(20) DEFAULT NULL,
  `upid` int(11) DEFAULT NULL,
  `client` varchar(11) DEFAULT NULL,
  `id` varchar(255) NOT NULL DEFAULT '',
  `upName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`boxid`,`id`),
  KEY `adbox_idx_1` (`stat`) USING BTREE,
  KEY `adbox_idx_2` (`label`(255)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_adbox
-- ----------------------------
INSERT INTO `tb_adbox` VALUES ('1', '2', '高考视频前广告位', null, '0', '0', '1', '0', '', '1504237515618', '0', 'gk', 'b976c68d7efc43b693bd63c8419f03db', 'admin');
INSERT INTO `tb_adbox` VALUES ('2', '2', '中考视频前广告位', null, '0', '0', '1', '0', '', '1504237526126', '0', 'gk', '723a90cd105b4d159c02071e6b2046e8', 'admin');
INSERT INTO `tb_adbox` VALUES ('3', '2', 'banner广告位', null, '0', '0', '1', '0', '', '1504247619171', '0', 'gk', 'ede2037c2b7c446f8da299bad1747e1e', 'admin');

-- ----------------------------
-- Table structure for `tb_adfilter`
-- ----------------------------
DROP TABLE IF EXISTS `tb_adfilter`;
CREATE TABLE `tb_adfilter` (
  `fltid` int(11) NOT NULL AUTO_INCREMENT,
  `boxid` int(11) DEFAULT NULL,
  `dflt` tinyint(4) DEFAULT NULL,
  `fcdmap` varchar(1024) DEFAULT NULL,
  `logic` tinyint(4) DEFAULT NULL,
  `prio` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `tplid` int(11) DEFAULT NULL,
  `uptime` bigint(20) DEFAULT NULL,
  `upid` int(11) DEFAULT NULL,
  `id` varchar(255) NOT NULL DEFAULT '',
  `client` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`fltid`,`id`),
  KEY `adfilter_idx_1` (`boxid`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_adfilter
-- ----------------------------
INSERT INTO `tb_adfilter` VALUES ('1', '3', '0', '{\"os\":\"IOS\"}', '1', '0', '2', '1', '1504238178668', '0', '4a24562758b04957a98e7160cc8ba101', 'gk');
INSERT INTO `tb_adfilter` VALUES ('2', '3', '0', '{\"os\":\"Android\"}', '1', '0', '2', '3', '1504247099430', '0', '91dc7d9318e047bcb8de7792f3e96a33', 'gk');

-- ----------------------------
-- Table structure for `tb_adframe`
-- ----------------------------
DROP TABLE IF EXISTS `tb_adframe`;
CREATE TABLE `tb_adframe` (
  `fmid` int(11) NOT NULL AUTO_INCREMENT,
  `tplid` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `prio` int(11) DEFAULT NULL,
  `icap` tinyint(4) DEFAULT NULL,
  `iptp` tinyint(4) DEFAULT NULL,
  `clxc` int(11) DEFAULT NULL,
  `clyc` int(11) DEFAULT NULL,
  `ist` tinyint(4) DEFAULT NULL,
  `tsea` bigint(20) DEFAULT NULL,
  `tsds` bigint(20) DEFAULT NULL,
  `eftm` tinyblob,
  `note` varchar(2048) DEFAULT NULL,
  `uptime` bigint(20) DEFAULT NULL,
  `upid` int(11) DEFAULT NULL,
  `id` varchar(255) NOT NULL DEFAULT '',
  `client` varchar(11) DEFAULT NULL,
  `upName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fmid`,`id`),
  KEY `adframe_idx_1` (`tplid`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_adframe
-- ----------------------------
INSERT INTO `tb_adframe` VALUES ('1', '3', '1', 'banner安卓', '10', '1', '1', '1', '0', '0', '0', '1504245399000', '1504504600000', null, null, '1504245413800', '0', '447189c439a848158c707dc633cd1b6f', 'gk', 'admin');
INSERT INTO `tb_adframe` VALUES ('2', '3', '1', 'banner安卓2', '10', '0', '1', '1', '0', '0', '0', '1504245428000', '1504504629000', null, null, '1504245435514', '0', '994c42b6b2974d74b83dcda75d56df4d', 'gk', 'admin');
INSERT INTO `tb_adframe` VALUES ('3', '1', '1', 'banner苹果', '10', '1', '1', '1', '0', '0', '0', '1504245452000', '1504504653000', null, null, '1504245456811', '0', '2d6a6fe81cec465f892110686ce6e5d1', 'gk', 'admin');
INSERT INTO `tb_adframe` VALUES ('4', '1', '1', 'banner苹果2', '10', '1', '1', '1', '0', '0', '0', '1504245471000', '1504504672000', null, null, '1504245477657', '0', 'a6f7ccac62734b53b3a9032e09c5b2b8', 'gk', 'admin');

-- ----------------------------
-- Table structure for `tb_adrela`
-- ----------------------------
DROP TABLE IF EXISTS `tb_adrela`;
CREATE TABLE `tb_adrela` (
  `fmid` int(11) NOT NULL AUTO_INCREMENT,
  `rsid` int(11) NOT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `prio` int(11) DEFAULT NULL,
  `clxs` int(11) DEFAULT NULL,
  `clxe` int(11) DEFAULT NULL,
  `clys` int(11) DEFAULT NULL,
  `clye` int(11) DEFAULT NULL,
  `extra` varchar(64) DEFAULT NULL,
  `note` varchar(2048) DEFAULT NULL,
  `uptime` bigint(20) DEFAULT NULL,
  `upid` int(11) DEFAULT NULL,
  `client` varchar(11) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fmid`),
  KEY `adrela_idx_1` (`fmid`,`status`) USING BTREE,
  KEY `IDX74gqksmwtua1boyd43v05g1rf` (`fmid`,`status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_adrela
-- ----------------------------
INSERT INTO `tb_adrela` VALUES ('1', '1', '2', '1', '0', '0', '0', '0', null, null, '0', '0', 'gk', null);
INSERT INTO `tb_adrela` VALUES ('2', '2', '1', '1', '0', '0', '0', '0', null, null, '0', '0', 'gk', null);
INSERT INTO `tb_adrela` VALUES ('3', '1', '1', '1', '0', '0', '0', '0', null, null, '0', '0', 'gk', null);
INSERT INTO `tb_adrela` VALUES ('4', '2', '1', '1', '0', '0', '0', '0', null, null, '0', '0', 'gk', null);

-- ----------------------------
-- Table structure for `tb_adres`
-- ----------------------------
DROP TABLE IF EXISTS `tb_adres`;
CREATE TABLE `tb_adres` (
  `rsid` int(11) NOT NULL AUTO_INCREMENT,
  `stat` tinyint(4) DEFAULT NULL,
  `rstp` int(11) DEFAULT NULL,
  `text` varchar(2048) DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `furi` varchar(512) DEFAULT NULL,
  `ruri` varchar(512) DEFAULT NULL,
  `note` varchar(2048) DEFAULT NULL,
  `uptime` bigint(20) DEFAULT NULL,
  `upid` int(11) DEFAULT NULL,
  `id` varchar(255) NOT NULL DEFAULT '',
  `client` varchar(11) DEFAULT NULL,
  `upName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rsid`,`id`),
  KEY `adres_idx_1` (`stat`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_adres
-- ----------------------------
INSERT INTO `tb_adres` VALUES ('1', '2', '0', null, 'banner1', 'http://www.lexue.com', 'http://advod.lexue.com/ad_picture/933485ab170901.jpg', null, '1504246919411', '0', 'c3d052bee899456bbb5a73de17d48c9b', 'gk', 'admin');
INSERT INTO `tb_adres` VALUES ('2', '2', '0', null, 'banner2', 'http://www.lexue.com', 'http://advod.lexue.com/ad_picture/96f66e38170901.jpg', null, '1504237924148', '0', '33afc94f55144384b409b27f81644c71', 'gk', 'admin');

-- ----------------------------
-- Table structure for `tb_adtpl`
-- ----------------------------
DROP TABLE IF EXISTS `tb_adtpl`;
CREATE TABLE `tb_adtpl` (
  `tplid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL,
  `label` varchar(256) DEFAULT NULL,
  `fcap` tinyint(4) DEFAULT NULL,
  `fptp` tinyint(4) DEFAULT NULL,
  `fst` tinyint(4) DEFAULT NULL,
  `note` varchar(2048) DEFAULT NULL,
  `uptime` bigint(20) DEFAULT NULL,
  `upid` int(11) DEFAULT NULL,
  `id` varchar(255) NOT NULL DEFAULT '',
  `client` varchar(11) DEFAULT NULL,
  `upName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tplid`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_adtpl
-- ----------------------------
INSERT INTO `tb_adtpl` VALUES ('1', 'banner高考苹果', null, '1', '0', '5', '苹果', '1504238178680', '0', '01f1f17e37c54e4eabca7938ca376998', 'gk', null);
INSERT INTO `tb_adtpl` VALUES ('3', 'banner高考安卓', null, '1', '0', '5', '安卓', '1504247099430', '0', 'ad4775a395114e9a9226228163abfd06', 'gk', null);
