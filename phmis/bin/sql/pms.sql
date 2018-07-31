/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : pms

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2018-04-20 09:08:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `people`
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `addr` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('2c92486862dbeab20162dbf4ee9f0006', '212', '21', '21');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc1066680009', '12', '21', '12');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc575a11000c', '1221d', '12', '1221');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc5788e2000e', '211212', '12', '212112');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc57ae85000f', '打', '32', '21是啥');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc57d90e0010', '3232322', '1', '12打算');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc57f6650011', '12', '12', '1221');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc5815c80012', '1221', 'F', 'F');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc58418b0014', 'fff', 'F', 'F');
INSERT INTO `people` VALUES ('2c92486862dbeab20162dc585a250015', 'FF', 'FF', 'FF');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `cas_Code` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `dept_Type` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `dept_No` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `parent_No` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `sys_Code` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `dept_Name` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `conn_Man` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `fax` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(300) CHARACTER SET utf8 DEFAULT NULL,
  `is_Use` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('4028818249bbe43d0149bbe4d8600002', '001001', '8', '05847', '0', '05847', '中国银行成都分行abc', '李四', '18048568965', 'abc', null, 'adfasdasdfasdsdf', '1', null, '86AABBE2C24848AEBA7E0499761C2D7A');
INSERT INTO `sys_dept` VALUES ('4028818249bbe43d0149bbe54c1e0004', '001001001', '8', '07177', '0', '07177', '营业部', ',代维管理员12', '', '', null, '', '1', null, '4028818249bbe43d0149bbe4d8600002');
INSERT INTO `sys_dept` VALUES ('4028838f6258b1cf01625925d384007d', '002', '8', '00012', null, '00012', '测试', '', '', '', null, '', '1', null, '86AABBE2C24848AEBA7E0499761C2D7A');
INSERT INTO `sys_dept` VALUES ('4028838f625bf87e01625fe797110117', '003', '8', '22222', null, '22222', '122132', '', '', '', null, '', '1', null, '4028818249bbe43d0149bbe4d8600002');
INSERT INTO `sys_dept` VALUES ('86AABBE2C24848AEBA7E0499761C2D7A', '001', '5', '00010', '1', '00010', '中国银行', '', '', '', '', '', '1', null, '0');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `cas_Code` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `menu_Name` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `menu_Type` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `action_Path` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `serial` bigint(20) DEFAULT NULL,
  `is_Use` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `PARENT_ID` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `menu_icon` varchar(100) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '001', '系统管理', '1', '', '10', '1', '0', 'menu-icon fa fa-laptop red');
INSERT INTO `sys_menu` VALUES ('2', '001001', '模块管理', '3', 'menu/listAllMenu.do', '11', '1', '1', 'menu-icon fa fa-folder-open-o red');
INSERT INTO `sys_menu` VALUES ('2c92b6065b798678015b7997e3260000', '001022', '机构管理', null, 'department/listAllDepartment.do?DEPARTMENT_ID=0', '12', '1', '1', 'menu-icon fa fa-credit-card blue');
INSERT INTO `sys_menu` VALUES ('2c92b6065b7fc5e6015b80282fc90002', '001023', '用户管理', null, 'user/listAllUser.do', '13', '1', '1', 'menu-icon fa fa-users blue');
INSERT INTO `sys_menu` VALUES ('2c92b6065b8e2fc0015b8e434f790000', '001024', '角色管理', null, 'role/list.do?entityOrField=true', '14', '1', '1', 'menu-icon fa fa-asterisk blue');
INSERT INTO `sys_menu` VALUES ('4028b981624628f501624632d2980008', '001026', '安全策略', null, '/html/sys/account_security_setting.html', '27', '1', '1', 'menu-icon fa fa-leaf black');
INSERT INTO `sys_menu` VALUES ('2c92486862dbeab20162dbec761e0000', '027', '开发列子', null, '#', '28', '1', '0', 'menu-icon fa fa-hdd-o red');
INSERT INTO `sys_menu` VALUES ('2c92486862dbeab20162dbecc59d0001', '027001', 'demo', null, '/html/people/people_list.html', '29', '1', '2c92486862dbeab20162dbec761e0000', 'menu-icon fa fa-leaf black');

-- ----------------------------
-- Table structure for `sys_menu_module_fun`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_module_fun`;
CREATE TABLE `sys_menu_module_fun` (
  `menu_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `fun_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_menu_module_fun
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_module_fun`
-- ----------------------------
DROP TABLE IF EXISTS `sys_module_fun`;
CREATE TABLE `sys_module_fun` (
  `id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `cas_Code` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `key_Word` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `serial` bigint(11) DEFAULT NULL,
  `is_Allow_Ctr` varchar(1) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_module_fun
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_operation`
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation`;
CREATE TABLE `sys_operation` (
  `id` varchar(32) NOT NULL,
  `btn_code` varchar(20) DEFAULT NULL,
  `btn_name` varchar(100) DEFAULT NULL,
  `menu_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_operation
-- ----------------------------
INSERT INTO `sys_operation` VALUES ('4028838f61e9d2c80161e9e9e2ba0001', '32', '32', '4028838f61e569ae0161e56c28580001');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c41d790002', 'goAdd', '新增', '4028838f6243ae38016243c3c9610001');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c432cb0003', 'goEdit', '编辑', '4028838f6243ae38016243c3c9610001');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c44cfc0004', 'goView', '查看', '4028838f6243ae38016243c3c9610001');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c470520005', 'delete', '删除', '4028838f6243ae38016243c3c9610001');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c551e70008', 'goAdd', '新增', '4028838f6243ae38016243c52baa0007');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c5672a0009', 'goEdit', '编辑', '4028838f6243ae38016243c52baa0007');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c57ddc000a', 'goView', '查看', '4028838f6243ae38016243c52baa0007');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c5944e000b', 'delete', '删除', '4028838f6243ae38016243c52baa0007');
INSERT INTO `sys_operation` VALUES ('4028838f6243ae38016243c5d4fe000c', 'goTask', '巡检任务', '4028838f6243ae38016243c52baa0007');
INSERT INTO `sys_operation` VALUES ('4028838f6243ece8016243ff5b750055', 'deviceRecord', '巡检详情', '4028838f6243ae38016243c52baa0007');
INSERT INTO `sys_operation` VALUES ('4028b981624628f5016246355ac00009', 'goView', '查看', '4028b981624628f50162462fe9e00002');
INSERT INTO `sys_operation` VALUES ('4028b981624628f501624635a4f4000a', 'goView', '查看', '4028b981624628f5016246302f040003');
INSERT INTO `sys_operation` VALUES ('4028b981624628f501624642954a001a', 'delete', '删除', '4028b981624628f50162462ea8160000');
INSERT INTO `sys_operation` VALUES ('4028b981624628f501624642ca1e001b', 'goAdd', '新增', '4028b981624628f50162462ea8160000');
INSERT INTO `sys_operation` VALUES ('4028b981624628f501624642e5fe001c', 'goEdit', '编辑', '4028b981624628f50162462ea8160000');
INSERT INTO `sys_operation` VALUES ('4028b981624628f501624642fc31001d', 'goView', '查看', '4028b981624628f50162462ea8160000');
INSERT INTO `sys_operation` VALUES ('2c9249936246271401624657951c0000', 'verify', '审核', '4028838f6243ae38016243c52baa0007');
INSERT INTO `sys_operation` VALUES ('2c9249936246271401624657c7da0001', 'reject', '驳回', '4028838f6243ae38016243c52baa0007');
INSERT INTO `sys_operation` VALUES ('2c9249936247372b01624744c7900000', 'goTaskDetail', '详细任务', '4028838f6243ae38016243c645aa000d');
INSERT INTO `sys_operation` VALUES ('2c9249936247372b0162474608c50001', 'deviceRecord', '巡检详情', '4028838f6243ae38016243c645aa000d');
INSERT INTO `sys_operation` VALUES ('4028838f6258b1cf01625b82d304012f', 'verify', '审核', '4028838f6258b1cf01625b81b726012e');
INSERT INTO `sys_operation` VALUES ('4028838f6258b1cf01625b8312d30130', 'reject', '驳回', '4028838f6258b1cf01625b81b726012e');
INSERT INTO `sys_operation` VALUES ('4028838f6258b1cf01625b9130640131', 'deviceRecord', '进入审核页面', '4028838f6258b1cf01625b81b726012e');
INSERT INTO `sys_operation` VALUES ('2c92486862dbeab20162dbed0ff80002', 'goAdd', '新增', '2c92486862dbeab20162dbecc59d0001');
INSERT INTO `sys_operation` VALUES ('2c92486862dbeab20162dbed25c20003', 'goEdit', '编辑', '2c92486862dbeab20162dbecc59d0001');
INSERT INTO `sys_operation` VALUES ('2c92486862dbeab20162dbed3c1a0004', 'delete', '删除', '2c92486862dbeab20162dbecc59d0001');
INSERT INTO `sys_operation` VALUES ('2c92486862dbeab20162dbfa9e3d0008', 'goView', '查看', '2c92486862dbeab20162dbecc59d0001');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `dept_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `role_Code` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `role_Name` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `role_Type` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_Use` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `oldRoleType` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `roleCode` varchar(255) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('4028838f6243d61f016243d72d3c0000', '', 'DWGLY', '管理员', '2', '即班组长，拥有PC端权限，管理巡检人员，制定巡检计划，审核巡检报告\r\n\r\n', '1', null, null);
INSERT INTO `sys_role` VALUES ('4028838f6243d61f016243d7a3720001', '', 'XJRY', '人员', '1', '拥有手持APP端登录使用权限', '1', null, null);

-- ----------------------------
-- Table structure for `sys_role_fun`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_fun`;
CREATE TABLE `sys_role_fun` (
  `fun_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `role_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_role_fun
-- ----------------------------
INSERT INTO `sys_role_fun` VALUES ('2c92486862dbeab20162dbed3c1a0004', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_fun` VALUES ('2c92486862dbeab20162dbed25c20003', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_fun` VALUES ('2c92486862dbeab20162dbfa9e3d0008', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_fun` VALUES ('2c92486862dbeab20162dbed0ff80002', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_fun` VALUES ('2c92486862dbeab20162dbed25c20003', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_fun` VALUES ('2c92486862dbeab20162dbfa9e3d0008', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_fun` VALUES ('2c92486862dbeab20162dbed3c1a0004', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_fun` VALUES ('2c92486862dbeab20162dbed0ff80002', '4028838f6243d61f016243d7a3720001');

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `menu_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `role_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('ff8080814a13f10f014a13f46c110005', '2c92491756da4d130156dadc4b310110');
INSERT INTO `sys_role_menu` VALUES ('2c92b611494fbb1b01494fbd15d70003', '4028b9815caeeec8015caf0c18870003');
INSERT INTO `sys_role_menu` VALUES ('4028b9815c62c774015c62cb87c60000', '4028b9815caeeec8015caf0c18870003');
INSERT INTO `sys_role_menu` VALUES ('2c92b611494fbb1b01494fbd15d70003', '4028b9815caeeec8015caf0c44780004');
INSERT INTO `sys_role_menu` VALUES ('4028b9815c62c774015c62cb87c60000', '4028b9815caeeec8015caf0c44780004');
INSERT INTO `sys_role_menu` VALUES ('2c92b611494fbb1b01494fbd15d70003', '4028b9815caeeec8015caf0c67680005');
INSERT INTO `sys_role_menu` VALUES ('4028b9815c62c774015c62cb87c60000', '4028b9815caeeec8015caf0c67680005');
INSERT INTO `sys_role_menu` VALUES ('2c92b611494fbb1b01494fbd15d70003', '4028b9815caeeec8015caf0c821d0006');
INSERT INTO `sys_role_menu` VALUES ('4028b9815c62c774015c62cb87c60000', '4028b9815caeeec8015caf0c821d0006');
INSERT INTO `sys_role_menu` VALUES ('2c92b611494fbb1b01494fbd15d70003', '4028b9815caeeec8015caf0c9bd00007');
INSERT INTO `sys_role_menu` VALUES ('4028b9815c62c774015c62cb87c60000', '4028b9815caeeec8015caf0c9bd00007');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c34fe40000', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c4babb0006', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f50162462fa3710001', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f50162462ea8160000', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c52baa0007', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f50162462fe9e00002', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f5016246302f040003', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c3c9610001', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c34fe40000', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c4babb0006', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f50162462fa3710001', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f50162462ea8160000', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c52baa0007', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c645aa000d', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f50162462fe9e00002', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f5016246302f040003', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028838f6258b1cf01625b81b726012e', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028838f6243ae38016243c3c9610001', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('2c92486862dbeab20162dbec761e0000', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('2c92b6065b7fc5e6015b80282fc90002', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('2c92b6065b798678015b7997e3260000', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('4028b981624628f501624632d2980008', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('1', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('2', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('2c92486862dbeab20162dbecc59d0001', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('2c92b6065b8e2fc0015b8e434f790000', '4028838f6243d61f016243d72d3c0000');
INSERT INTO `sys_role_menu` VALUES ('2c92486862dbeab20162dbecc59d0001', '4028838f6243d61f016243d7a3720001');
INSERT INTO `sys_role_menu` VALUES ('2c92486862dbeab20162dbec761e0000', '4028838f6243d61f016243d7a3720001');

-- ----------------------------
-- Table structure for `sys_strategy`
-- ----------------------------
DROP TABLE IF EXISTS `sys_strategy`;
CREATE TABLE `sys_strategy` (
  `id` varchar(32) NOT NULL,
  `account_lock_strage` int(11) DEFAULT NULL,
  `account_strage` int(11) DEFAULT NULL,
  `no_use_days` int(11) DEFAULT NULL,
  `limit_time` int(11) DEFAULT NULL,
  `login_err_count` int(11) DEFAULT NULL,
  `login_err_limt_time` int(11) DEFAULT NULL,
  `pass_min_length` int(11) DEFAULT NULL,
  `some_char_count` int(11) DEFAULT NULL,
  `special_chars` varchar(50) DEFAULT NULL,
  `pass_use_days` int(11) DEFAULT NULL,
  `pass_expire_remind_days` int(11) DEFAULT NULL,
  `is_use_pass_strage` int(11) DEFAULT NULL,
  `notice_type` varchar(32) DEFAULT NULL,
  `is_contain_special_char` int(11) DEFAULT NULL,
  `account_stop_strage` int(11) DEFAULT NULL,
  `is_use_ordinary_strage` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_strategy
-- ----------------------------
INSERT INTO `sys_strategy` VALUES ('4028809f62aeae370162aec2f5af0002', '0', '0', '1', '1', '1', '1', '6', '1', '!@#$%^&*()_+:', '110', '1', '1', '1,2,3', '1', '0', '1');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `dept_Id` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `no` varchar(8) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(128) CHARACTER SET utf8 DEFAULT NULL,
  `finger_print` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `use_Date` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `sex` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `work_Phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `family_Phone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(64) CHARACTER SET utf8 DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_Lock` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `state` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `first_Menu_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `pwd_Edit_Date` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `last_Login_Date` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `come_From_Sign` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `business_Type` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `from_id` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `assignDept` tinyblob,
  `code` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_Type` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '1.成员 2.责任人',
  `token` varchar(155) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2c92486862dbeab20162dcd4d1e40018', '4028818249bbe43d0149bbe54c1e0004', '00001', '管理员', 'e10adc3949ba59abbe56e057f20f883e', null, '2018-04-19', '1', '15882321247', '', null, '212@163.com', '', '1', '1', '', '2018-04-19', '2018-04-19 16:36:32', null, null, '', null, null, '1', null);
INSERT INTO `sys_user` VALUES ('4028838f625bf87e01625fe57b2d0116', '4028838f625bf87e01625fe797110117', '00003', '1221', 'e10adc3949ba59abbe56e057f20f883e', null, '2018-03-26', '1', '15882321243', '', null, '2864333077@qq.com', '', '1', '1', '', '2018-03-26', '2018-03-26 12:12:12', null, null, '', null, null, '1', null);

-- ----------------------------
-- Table structure for `sys_user_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept` (
  `dept_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `user_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
INSERT INTO `sys_user_dept` VALUES ('4028838f625bf87e01625fe797110117', '4028838f625bf87e01625fe57b2d0116');
INSERT INTO `sys_user_dept` VALUES ('4028818249bbe43d0149bbe54c1e0004', '2c92486862dbeab20162dcd4d1e40018');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `role_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL,
  `user_Id` varchar(32) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('4028838f6243d61f016243d72d3c0000', '4028838f625bf87e01625fe57b2d0116');
INSERT INTO `sys_user_role` VALUES ('4028838f6243d61f016243d72d3c0000', '2c92486862dbeab20162dcd4d1e40018');
DROP TRIGGER IF EXISTS `sysDept_deptNo_update_for_arch_code`;
DELIMITER ;;
CREATE TRIGGER `sysDept_deptNo_update_for_arch_code` AFTER UPDATE ON `sys_dept` FOR EACH ROW BEGIN
	IF(new.dept_no!=old.dept_no)
	THEN
		UPDATE biz_intelli_arch a SET a.code=REPLACE(a.code,old.dept_no,new.dept_no) WHERE a.code LIKE CONCAT(old.dept_no,'-%');
	END IF;
    END
;;
DELIMITER ;
