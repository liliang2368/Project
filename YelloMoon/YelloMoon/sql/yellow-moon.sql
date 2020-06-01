/*
Navicat MySQL Data Transfer

Source Server         : ss
Source Server Version : 50018
Source Host           : localhost:3306
Source Database       : yellow-moon

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2020-04-06 21:11:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for addr
-- ----------------------------
DROP TABLE IF EXISTS `addr`;
CREATE TABLE `addr` (
  `id` int(11) NOT NULL auto_increment,
  `userid` int(11) default NULL,
  `name` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `jtaddr` varchar(255) default NULL,
  `sta` int(11) default NULL,
  `area` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of addr
-- ----------------------------
INSERT INTO `addr` VALUES ('1', '1', '李阳', '16673478081', '湖南省娄底市', '1', '珠晖区', '17100');
INSERT INTO `addr` VALUES ('2', '1', '张三', '1775558825', '湖南省衡阳市', '2', '珠晖区', '17100');
INSERT INTO `addr` VALUES ('3', '1', '李四', '1223556633', '湖南省衡阳市', '2', '珠晖区', '17100');
INSERT INTO `addr` VALUES ('4', '1', '王五', '1526633666', '湖南省衡阳市', '2', '珠晖区', '17100');

-- ----------------------------
-- Table structure for admininfo
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo` (
  `ID` int(11) NOT NULL auto_increment,
  `A_NAME` varchar(20) default NULL,
  `A_PWD` varchar(20) default NULL,
  `A_MARK` varchar(200) default NULL,
  `A_STATE` int(11) NOT NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admininfo
-- ----------------------------
INSERT INTO `admininfo` VALUES ('2', 'a', 'a', '描述.....', '1');
INSERT INTO `admininfo` VALUES ('21', 'b', 'a', '描述.....', '1');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL auto_increment,
  `foodid` int(11) default NULL,
  `num` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('56', '0', '5');
INSERT INTO `cart` VALUES ('76', '13', '2');
INSERT INTO `cart` VALUES ('77', '12', '1');
INSERT INTO `cart` VALUES ('78', '10', '2');
INSERT INTO `cart` VALUES ('79', '1', '1');

-- ----------------------------
-- Table structure for commentinfo
-- ----------------------------
DROP TABLE IF EXISTS `commentinfo`;
CREATE TABLE `commentinfo` (
  `ID` int(11) NOT NULL auto_increment,
  `O_ID` int(11) default NULL,
  `M_ID` int(11) default NULL,
  `CO_TITLE` varchar(255) default NULL,
  `CO_CONTENT` longtext,
  `CO_RANK` decimal(10,4) default NULL,
  `CO_PIC` varchar(255) default NULL,
  `CO_DATE` datetime default NULL,
  `CO_TEMP` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commentinfo
-- ----------------------------

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL auto_increment,
  `userid` int(11) default NULL,
  `foodid` int(11) default NULL,
  `start` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  `createtime` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('8', '1', '1', null, 'dsifouioiuo', '2019-06-01 19:44:14');
INSERT INTO `comments` VALUES ('11', '1', '8', null, 'dsadsa', '2019-06-02 17:00:35');
INSERT INTO `comments` VALUES ('12', '1', '10', null, '美味u\r\n', '2019-06-03 23:02:45');
INSERT INTO `comments` VALUES ('13', '1', '1', null, '打算', '2019-06-29 14:24:56');

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `id` int(11) NOT NULL auto_increment,
  `foodname` varchar(255) default NULL,
  `dec` varchar(255) default NULL,
  `oldprice` decimal(10,0) default NULL,
  `createtime` varchar(50) default NULL,
  `pic` varchar(255) default NULL,
  `num` int(11) default NULL,
  `newprice` decimal(10,0) default NULL,
  `typeid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', '美味薯条', '十分爽脆', '22', '2019-05-19 19:50:50', 'images/shutiao1.jpg', '99', '10', '2');
INSERT INTO `food` VALUES ('8', '擂茶', '特色美食', '88', '2019-05-27 20:02:19', 'images/of1.jpg', '55', '55', '2');
INSERT INTO `food` VALUES ('10', '牛肉粒', '美味', '66', '2019-05-28 09:13:58', 'images/nl.jpg', '0', '22', '1');
INSERT INTO `food` VALUES ('11', '燕麦', '美味', '66', '2019-05-28 09:14:42', 'images/ym.jpg', '0', '22', '1');
INSERT INTO `food` VALUES ('12', '美味薯条', '营养美味', '22', '2019-05-28 09:16:07', 'images/shutiao1.jpg', '22', '11', '5');
INSERT INTO `food` VALUES ('13', '擂茶', '美味营养', '22', '2019-05-28 09:17:35', 'images/of1.jpg', '66', '10', '1');
INSERT INTO `food` VALUES ('15', '牛肉粒', '十分美味', '22', '2019-10-21', 'images/nl.jpg', '22', '11', '1');
INSERT INTO `food` VALUES ('16', '燕麦', '美味', '66', '2019-05-28 09:14:42', 'images/ym.jpg', '0', '22', '1');

-- ----------------------------
-- Table structure for fooddetail
-- ----------------------------
DROP TABLE IF EXISTS `fooddetail`;
CREATE TABLE `fooddetail` (
  `id` int(11) NOT NULL auto_increment,
  `head` varchar(255) default NULL,
  `head1` varchar(255) default NULL,
  `head2` varchar(255) default NULL,
  `info` varchar(255) default NULL,
  `foodid` int(11) default NULL,
  `head3` varchar(255) default NULL,
  `head4` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fooddetail
-- ----------------------------
INSERT INTO `fooddetail` VALUES ('1', 'images/timg.jpg', 'images/shuigshala.jpg', 'images/shuigshala1.jpg', null, '1', 'images/shuigshala2.jpg', 'images/shuigshala3.jpg');
INSERT INTO `fooddetail` VALUES ('8', 'images/timg10.jpg', 'images/tim7.jpg', 'images/tim8.jpg', null, '7', 'images/timg9.jpg', 'images/timg11.jpg');
INSERT INTO `fooddetail` VALUES ('9', 'images/product-image-5.jpg', 'images/kaoya2.jpg', 'images/kaoya3.jpg', null, '8', 'images/kaoya4.jpg', 'images/kaoya1.jpg');
INSERT INTO `fooddetail` VALUES ('10', 'images/chaofen1.jpg', 'images/chaofen1.jpg', 'images/chaofen2.jpg', null, '9', 'images/chaofen3.jpg', 'images/chaofen4.jpg');
INSERT INTO `fooddetail` VALUES ('11', 'images/zhujiao.jpg', 'images/zhujiao1.jpg', 'images/zhujiao2.jpg', null, '10', 'images/zhujiao3.jpg', 'images/zhujiao.jpg');
INSERT INTO `fooddetail` VALUES ('12', 'images/product-image-5.jpg', 'images/kaoya2.jpg', 'images/kaoya3.jpg', null, '11', 'images/kaoya4.jpg', 'images/hongshao.jpg');
INSERT INTO `fooddetail` VALUES ('13', 'images/product-image-5.jpg', 'images/kaoya2.jpg', 'images/kaoya3.jpg', null, '12', 'images/kaoya4.jpg', 'images/hongshao.jpg');
INSERT INTO `fooddetail` VALUES ('14', 'images/product-image-5.jpg', 'images/kaoya2.jpg', 'images/kaoya3.jpg', null, '13', 'images/kaoya4.jpg', 'images/hongshao.jpg');
INSERT INTO `fooddetail` VALUES ('15', 'images/product-image-5.jpg', 'images/kaoya2.jpg', 'images/kaoya3.jpg', null, '15', 'images/kaoya4.jpg', 'images/hongshao.jpg');

-- ----------------------------
-- Table structure for foodtype
-- ----------------------------
DROP TABLE IF EXISTS `foodtype`;
CREATE TABLE `foodtype` (
  `id` int(11) NOT NULL auto_increment,
  `type` varchar(255) default NULL,
  `info` varchar(255) default NULL,
  `head` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodtype
-- ----------------------------
INSERT INTO `foodtype` VALUES ('1', '牛肉类', '美味', 'images/aa3.jpg');
INSERT INTO `foodtype` VALUES ('2', '家鸭类', '美味', 'images/spander1.jpg');
INSERT INTO `foodtype` VALUES ('3', '鱼类', '美味', 'images/aa2.jpg');
INSERT INTO `foodtype` VALUES ('4', '医疗', '十分美味', 'images/product-image-5.jpg');

-- ----------------------------
-- Table structure for goodtype
-- ----------------------------
DROP TABLE IF EXISTS `goodtype`;
CREATE TABLE `goodtype` (
  `ID` int(11) NOT NULL auto_increment,
  `T_NAME` varchar(200) default NULL,
  `T_DISC` varchar(255) default NULL,
  `T_PARENT` int(11) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goodtype
-- ----------------------------
INSERT INTO `goodtype` VALUES ('2', '美食', null, '1');
INSERT INTO `goodtype` VALUES ('3', '娱乐', 'aaa', '1');
INSERT INTO `goodtype` VALUES ('4', '电影', 'aa', '1');
INSERT INTO `goodtype` VALUES ('5', '美容保健', null, '1');
INSERT INTO `goodtype` VALUES ('6', '生活服务', '1', '1');
INSERT INTO `goodtype` VALUES ('7', '结婚', null, '1');
INSERT INTO `goodtype` VALUES ('8', '旅行', null, '1');
INSERT INTO `goodtype` VALUES ('21', '酒店', 'bbbbb', '1');
INSERT INTO `goodtype` VALUES ('22', '网购', 'bbb', '1');
INSERT INTO `goodtype` VALUES ('41', '火锅', 'aaaa', '2');
INSERT INTO `goodtype` VALUES ('42', '湘菜', 'aaa', '2');
INSERT INTO `goodtype` VALUES ('43', '团购汇', 'sfs', '1');
INSERT INTO `goodtype` VALUES ('44', '其他', 'aa', '1');
INSERT INTO `goodtype` VALUES ('61', '婚纱', 'assss', '7');
INSERT INTO `goodtype` VALUES ('81', '海鲜', 'aa', '2');
INSERT INTO `goodtype` VALUES ('82', '自助餐', 'aaa', '2');

-- ----------------------------
-- Table structure for home
-- ----------------------------
DROP TABLE IF EXISTS `home`;
CREATE TABLE `home` (
  `id` int(11) NOT NULL auto_increment,
  `home_info` varchar(255) default '' COMMENT '房间介绍',
  `are` varchar(255) default NULL COMMENT '房间地区',
  `bed_id` int(11) default NULL COMMENT '床位数',
  `person_in` int(11) default NULL COMMENT '几个人住',
  `price` double default NULL COMMENT '房间价格',
  `peizhi_id` int(11) default NULL COMMENT '房间配置 关联配置表  (有热水器  沙发  )',
  `pic` varchar(255) default NULL,
  `type_id` int(11) default NULL,
  `cheku` int(11) default NULL,
  `bash` int(11) default NULL,
  `user1` int(11) default NULL,
  `user2` int(11) default NULL,
  `user3` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of home
-- ----------------------------
INSERT INTO `home` VALUES ('1', '超级无敌海景房', '衡阳市 ', '1', '3', '10', '1', 'images/pic3.jpg', '1', '2', '3', '1', '2', '3');
INSERT INTO `home` VALUES ('2', '全网低价房', '衡阳市', '2', '3', '20', '2', 'images/pic2.jpg', '2', '1', '2', '1', '2', '3');
INSERT INTO `home` VALUES ('3', '全网低价房', '衡阳市', '2', '3', '69', '1', 'images/pic1.jpg', '1', '2', '2', '1', '2', '3');
INSERT INTO `home` VALUES ('4', '豪华套餐', '珠晖区', '2', '3', '100', '1', 'images/pic4.jpg', '1', '2', '3', '1', '2', '3');
INSERT INTO `home` VALUES ('5', '房一', null, null, null, null, null, 'images/pic5.jpg', '1', null, null, null, null, null);
INSERT INTO `home` VALUES ('6', '房二', null, null, null, null, null, 'images/pic1.jpg', '1', null, null, null, null, null);
INSERT INTO `home` VALUES ('7', '房三', null, null, null, null, null, 'images/pic1.jpg', '2', null, null, null, null, null);
INSERT INTO `home` VALUES ('8', '房四', null, null, null, null, null, 'images/pic3.jpg', '2', null, null, null, null, null);
INSERT INTO `home` VALUES ('9', '房五', null, null, null, null, null, 'images/pic3.jpg', '3', null, null, null, null, null);
INSERT INTO `home` VALUES ('10', '房六', null, null, null, null, null, 'images/pic3.jpg', '3', null, null, null, null, null);
INSERT INTO `home` VALUES ('11', '房七', null, null, null, null, null, 'images/pic3.jpg', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for home_detail
-- ----------------------------
DROP TABLE IF EXISTS `home_detail`;
CREATE TABLE `home_detail` (
  `id` int(11) NOT NULL auto_increment,
  `home_id` int(11) default NULL COMMENT '关联房间id  主要为房间提供更多得刻度信息',
  `pic1` varchar(255) default NULL COMMENT '图片',
  `pic2` varchar(255) default NULL,
  `pic3` varchar(255) default NULL,
  `info1` varchar(255) default NULL COMMENT '详细介绍',
  `pic4` varchar(255) default NULL,
  `pic5` varchar(255) default NULL,
  `pic6` varchar(255) default NULL,
  `pic7` varchar(255) default NULL,
  `info2` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of home_detail
-- ----------------------------
INSERT INTO `home_detail` VALUES ('1', '1', 'images/home1.jpg', 'images/home.jpg', 'images/home2.jpg', 'Hello,您所浏览的房源[白可可]是一套46mi一室一厅，无敌江景落地窗!房间配有马歇尔蓝牙音响与移动网络坚果投影。床.上配备宜家高品质床垫，宜家乳胶枕头和纯棉床品,- - -客-换的高品质毛巾与浴巾,高品质洗发水，沐浴露超高品质一次性用品等绝对给您带来超棒的居住体验,是一套美景与地段绝佳，兼具美貌与舒适度的房子。', 'images/home3.jpg', 'images/home4.jpg', 'images/home5.jpg', 'images/home6.jpg', '同区域中最实惠的房子但同样保证绝对的清洁与舒适，力求带来最好的住房体验');
INSERT INTO `home_detail` VALUES ('2', '2', 'images/home7.jpg', 'images/home8.jpg', 'images/home9.jpg', 'Hello,您所浏览的房源[白可可]是一套46mi一室一厅，无敌江景落地窗!房间配有马歇尔蓝牙音响与移动网络坚果投影。床.上配备宜家高品质床垫，宜家乳胶枕头和纯棉床品,- - -客-换的高品质毛巾与浴巾,高品质洗发水，沐浴露超高品质一次性用品等绝对给您带来超棒的居住体验,是一套美景与地段绝佳，兼具美貌与舒适度的房子。', 'images/home10.jpg', 'images/home11.jpg', 'images/home12.jpg', 'images/home13.jpg', '  *房子为两室一厅一厨一卫一阳台，每个独立房间都能充分保证隐私和私人空间,请您放心入住。您可选择预定一个独立房间或者是四个好朋友一起预定整套房间，都是超赞der.两个房间均为一米五双人床,只能最多两人入住:)');
INSERT INTO `home_detail` VALUES ('3', '3', 'images/home14.jpg', 'images/home15.jpg', 'images/home16.jpg', '我会为您准备干净的拖鞋，超好用的洗发水护发素与沐浴露，超清新的牙膏和一次性又很舒服的牙刷，旨在让您像在家中- -般自由与舒适^^为了干净与卫生，不提供毛巾浴巾,请您自己携带哦', 'images/home17.jpg', 'images/home18.jpg', 'images/home19.jpg', 'images/home.jpg', '  *房子为两室一厅一厨一卫一阳台，每个独立房间都能充分保证隐私和私人空间,请您放心入住。您可选择预定一个独立房间或者是四个好朋友一起预定整套房间，都是超赞der.两个房间均为一米五双人床,只能最多两人入住:)');
INSERT INTO `home_detail` VALUES ('4', '4', 'images/home2.jpg', 'images/home3.jpg', 'images/home4.jpg', 'Hello,您所浏览的房源[白可可]是一套46mi一室一厅，无敌江景落地窗!房间配有马歇尔蓝牙音响与移动网络坚果投影。床.上配备宜家高品质床垫，宜家乳胶枕头和纯棉床品,- - -客-换的高品质毛巾与浴巾,高品质洗发水，沐浴露超高品质一次性用品等绝对给您带来超棒的居住体验,是一套美景与地段绝佳，兼具美貌与舒适度的房子。', 'images/home5.jpg', 'images/home6.jpg', 'images/home7.jpg', 'images/home8.jpg', '  *房子为两室一厅一厨一卫一阳台，每个独立房间都能充分保证隐私和私人空间,请您放心入住。您可选择预定一个独立房间或者是四个好朋友一起预定整套房间，都是超赞der.两个房间均为一米五双人床,只能最多两人入住:)');
INSERT INTO `home_detail` VALUES ('5', '5', 'images/home5.jpg', 'images/home6.jpg', 'images/home7.jpg', 'Hello,您所浏览的房源[白可可]是一套46mi一室一厅，无敌江景落地窗!房间配有马歇尔蓝牙音响与移动网络坚果投影。床.上配备宜家高品质床垫，宜家乳胶枕头和纯棉床品,- - -客-换的高品质毛巾与浴巾,高品质洗发水，沐浴露超高品质一次性用品等绝对给您带来超棒的居住体验,是一套美景与地段绝佳，兼具美貌与舒适度的房子。', 'images/home8.jpg', 'images/home9.jpg', 'images/home10.jpg', 'images/home11.jpg', '同区域中最实惠的房子但同样保证绝对的清洁与舒适，力求带来最好的住房体验');
INSERT INTO `home_detail` VALUES ('6', '6', 'images/home12.jpg', 'images/home13.jpg', 'images/home14.jpg', 'Hello,您所浏览的房源[白可可]是一套46mi一室一厅，无敌江景落地窗!房间配有马歇尔蓝牙音响与移动网络坚果投影。床.上配备宜家高品质床垫，宜家乳胶枕头和纯棉床品,- - -客-换的高品质毛巾与浴巾,高品质洗发水，沐浴露超高品质一次性用品等绝对给您带来超棒的居住体验,是一套美景与地段绝佳，兼具美貌与舒适度的房子。', 'images/home15.jpg', 'images/home16.jpg', 'images/home17.jpg', 'images/home18.jpg', '同区域中最实惠的房子但同样保证绝对的清洁与舒适，力求带来最好的住房体验');
INSERT INTO `home_detail` VALUES ('7', '7', 'images/home1.jpg', 'images/home2.jpg', 'images/home3.jpg', 'Hello,您所浏览的房源[白可可]是一套46mi一室一厅，无敌江景落地窗!房间配有马歇尔蓝牙音响与移动网络坚果投影。床.上配备宜家高品质床垫，宜家乳胶枕头和纯棉床品,- - -客-换的高品质毛巾与浴巾,高品质洗发水，沐浴露超高品质一次性用品等绝对给您带来超棒的居住体验,是一套美景与地段绝佳，兼具美貌与舒适度的房子。', 'images/home4.jpg', 'images/home5.jpg', 'images/home6.jpg', 'images/home7.jpg', '同区域中最实惠的房子但同样保证绝对的清洁与舒适，力求带来最好的住房体验');
INSERT INTO `home_detail` VALUES ('8', '8', 'images/home10.jpg', 'images/home11.jpg', 'images/home12.jpg', 'Hello,您所浏览的房源[白可可]是一套46mi一室一厅，无敌江景落地窗!房间配有马歇尔蓝牙音响与移动网络坚果投影。床.上配备宜家高品质床垫，宜家乳胶枕头和纯棉床品,- - -客-换的高品质毛巾与浴巾,高品质洗发水，沐浴露超高品质一次性用品等绝对给您带来超棒的居住体验,是一套美景与地段绝佳，兼具美貌与舒适度的房子。', 'images/home13.jpg', 'images/home14.jpg', 'images/home15.jpg', 'images/home16.jpg', '同区域中最实惠的房子但同样保证绝对的清洁与舒适，力求带来最好的住房体验');

-- ----------------------------
-- Table structure for home_type
-- ----------------------------
DROP TABLE IF EXISTS `home_type`;
CREATE TABLE `home_type` (
  `id` int(11) NOT NULL auto_increment,
  `type_name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of home_type
-- ----------------------------
INSERT INTO `home_type` VALUES ('1', '别墅');
INSERT INTO `home_type` VALUES ('2', '避暑别墅');
INSERT INTO `home_type` VALUES ('3', '商业用');

-- ----------------------------
-- Table structure for memberinfo
-- ----------------------------
DROP TABLE IF EXISTS `memberinfo`;
CREATE TABLE `memberinfo` (
  `ID` int(11) NOT NULL auto_increment,
  `M_NAME` varchar(20) NOT NULL,
  `M_PWD` varchar(40) NOT NULL,
  `M_TEL` varchar(20) NOT NULL,
  `M_SEX` varchar(4) NOT NULL,
  `M_EMAIL` varchar(50) NOT NULL,
  `M_REG` datetime NOT NULL,
  `M_SCORE` decimal(10,4) NOT NULL,
  `M_RANK` decimal(10,4) default NULL,
  `M_MONEY` decimal(10,4) default NULL,
  `M_PIC` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of memberinfo
-- ----------------------------
INSERT INTO `memberinfo` VALUES ('21', 'yt', 'a', '18175970260', '女', 'qing.yt@163.com', '2019-10-03 08:07:08', '0.0000', '1.0000', '0.0000', null);

-- ----------------------------
-- Table structure for oorder
-- ----------------------------
DROP TABLE IF EXISTS `oorder`;
CREATE TABLE `oorder` (
  `id` int(11) NOT NULL auto_increment,
  `userid` int(11) default NULL,
  `foodid` int(11) default NULL,
  `addrid` varchar(11) default NULL,
  `status` varchar(255) default NULL,
  `comment` varchar(255) default NULL,
  `cratetime` varchar(255) default NULL,
  `num` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oorder
-- ----------------------------
INSERT INTO `oorder` VALUES ('20', '1', null, '1', '待支付', null, '2019-05-30 15:46:59', '1');
INSERT INTO `oorder` VALUES ('21', '1', null, '1', '待支付', null, '2019-05-30 17:16:37', '1');
INSERT INTO `oorder` VALUES ('22', '1', null, '1', '待支付', null, '2019-05-30 17:17:11', '1');
INSERT INTO `oorder` VALUES ('23', '1', null, '1', '待支付', null, '2019-05-30 17:19:14', '1');
INSERT INTO `oorder` VALUES ('24', '1', null, '1', '待支付', null, '2019-05-30 17:23:52', '1');
INSERT INTO `oorder` VALUES ('25', '1', null, '1', '待支付', null, '2019-05-30 17:28:00', '1');
INSERT INTO `oorder` VALUES ('26', '1', null, '1', '待支付', null, '2019-05-30 17:29:06', '1');
INSERT INTO `oorder` VALUES ('27', '1', null, '1', '待支付', null, '2019-05-30 17:56:30', '1');
INSERT INTO `oorder` VALUES ('28', '1', null, '1', '待支付', null, '2019-05-30 18:40:11', '1');
INSERT INTO `oorder` VALUES ('29', '1', null, '1', '待支付', null, '2019-05-30 18:48:58', '1');
INSERT INTO `oorder` VALUES ('30', '1', null, '1', '待支付', null, '2019-05-30 19:15:34', '1');
INSERT INTO `oorder` VALUES ('31', '1', null, '1', '待支付', null, '2019-05-30 19:41:14', '1');
INSERT INTO `oorder` VALUES ('32', '1', null, '1', '待支付', null, '2019-05-30 20:03:40', '1');
INSERT INTO `oorder` VALUES ('33', '1', null, '1', '待支付', null, '2019-05-30 20:07:49', '1');
INSERT INTO `oorder` VALUES ('34', '1', null, '1', '待支付', null, '2019-05-30 20:13:17', '1');
INSERT INTO `oorder` VALUES ('35', '1', null, null, '待支付', null, '2019-05-30 20:32:35', null);
INSERT INTO `oorder` VALUES ('36', '1', null, null, '待支付', '0', '2019-05-30 20:35:28', null);
INSERT INTO `oorder` VALUES ('37', '1', null, null, '待支付', null, '2019-05-30 20:38:31', null);
INSERT INTO `oorder` VALUES ('38', '1', null, '湖南省娄底市', '待支付', '加辣', '2019-05-30 20:40:54', null);
INSERT INTO `oorder` VALUES ('39', '1', null, '湖南省娄底市', '已支付', null, '2019-05-30 20:49:18', null);
INSERT INTO `oorder` VALUES ('40', '1', null, '湖南省娄底市', '已支付', 'j加辣', '2019-05-30 20:53:11', null);
INSERT INTO `oorder` VALUES ('41', '1', null, null, '待支付', null, '2019-05-30 20:53:59', null);
INSERT INTO `oorder` VALUES ('42', '1', null, '湖南省娄底市', '已支付', '', '2019-05-30 21:40:04', null);
INSERT INTO `oorder` VALUES ('43', '1', null, '湖南省娄底市', '已支付', '加辣', '2019-05-31 09:16:38', null);
INSERT INTO `oorder` VALUES ('44', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 09:29:29', null);
INSERT INTO `oorder` VALUES ('45', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:09:18', null);
INSERT INTO `oorder` VALUES ('46', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:12:22', null);
INSERT INTO `oorder` VALUES ('47', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:16:35', null);
INSERT INTO `oorder` VALUES ('48', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:17:08', null);
INSERT INTO `oorder` VALUES ('49', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:17:32', null);
INSERT INTO `oorder` VALUES ('53', '1', null, '湖南省娄底市', '已支付', '', '2019-05-31 16:37:50', null);
INSERT INTO `oorder` VALUES ('54', '1', null, '湖南省娄底市', '已支付', '', '2019-05-31 16:51:26', null);
INSERT INTO `oorder` VALUES ('55', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:53:30', null);
INSERT INTO `oorder` VALUES ('56', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:54:44', null);
INSERT INTO `oorder` VALUES ('57', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:56:59', null);
INSERT INTO `oorder` VALUES ('58', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:57:17', null);
INSERT INTO `oorder` VALUES ('59', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 16:58:44', null);
INSERT INTO `oorder` VALUES ('60', '1', null, '湖南省娄底市', '已支付', '加辣', '2019-05-31 19:19:40', null);
INSERT INTO `oorder` VALUES ('61', '1', null, '湖南省娄底市', '待支付', null, '2019-05-31 19:56:25', null);
INSERT INTO `oorder` VALUES ('62', '0', null, '湖南省娄底市', '待支付', null, '2019-05-31 20:39:08', null);
INSERT INTO `oorder` VALUES ('63', '1', null, '湖南省娄底市', '待支付', null, '2019-06-01 00:22:21', null);
INSERT INTO `oorder` VALUES ('64', '1', null, '湖南省娄底市', '已支付', '加辣', '2019-06-01 14:19:30', null);
INSERT INTO `oorder` VALUES ('65', '1', null, '湖南省娄底市', '待支付', null, '2019-06-01 19:44:29', null);
INSERT INTO `oorder` VALUES ('66', '1', null, '湖南省娄底市', '待支付', null, '2019-06-01 19:44:49', null);
INSERT INTO `oorder` VALUES ('67', '1', null, '湖南省娄底市', '待支付', null, '2019-06-02 16:25:45', null);
INSERT INTO `oorder` VALUES ('68', '1', null, '湖南省娄底市', '已支付', '', '2019-06-02 16:30:08', null);
INSERT INTO `oorder` VALUES ('69', '1', null, '湖南省娄底市', '待支付', null, '2019-06-02 16:42:58', null);
INSERT INTO `oorder` VALUES ('70', '1', null, '湖南省娄底市', '已支付', '加辣', '2019-06-02 17:10:36', null);
INSERT INTO `oorder` VALUES ('71', '1', null, '湖南省娄底市', '已支付', '加辣', '2019-06-03 23:08:23', null);
INSERT INTO `oorder` VALUES ('72', '1', null, '湖南省娄底市', '待支付', null, '2019-06-04 19:38:27', null);
INSERT INTO `oorder` VALUES ('73', '1', null, '湖南省娄底市', '待支付', null, '2019-06-04 20:14:10', null);
INSERT INTO `oorder` VALUES ('74', '1', null, '湖南省娄底市', '待支付', null, '2019-06-27 16:48:33', null);
INSERT INTO `oorder` VALUES ('75', '1', null, '湖南省娄底市', '已支付', '', '2019-06-29 12:33:10', null);
INSERT INTO `oorder` VALUES ('76', '1', null, '湖南省娄底市', '待支付', null, '2019-07-03 13:03:40', null);
INSERT INTO `oorder` VALUES ('77', '1', null, '湖南省娄底市', '已支付', '加辣', '2019-07-03 13:04:34', null);
INSERT INTO `oorder` VALUES ('78', '1', null, '湖南省娄底市', '待支付', null, '2019-12-05 14:10:07', null);

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `ID` int(11) NOT NULL auto_increment,
  `M_ID` int(11) default NULL,
  `SM_ID` int(11) default NULL,
  `O_NUM` varchar(30) default NULL,
  `O_PRICE` decimal(10,4) default NULL,
  `O_AMOUNT` decimal(10,4) default NULL,
  `O_DATE` datetime default NULL,
  `O_STATE` int(11) default NULL,
  `O_DIS` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------

-- ----------------------------
-- Table structure for peizhi
-- ----------------------------
DROP TABLE IF EXISTS `peizhi`;
CREATE TABLE `peizhi` (
  `id` int(11) NOT NULL auto_increment,
  `peizhi_info` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peizhi
-- ----------------------------
INSERT INTO `peizhi` VALUES ('2', '冰箱');
INSERT INTO `peizhi` VALUES ('3', '沙发');
INSERT INTO `peizhi` VALUES ('4', '冰箱');

-- ----------------------------
-- Table structure for peizhihome
-- ----------------------------
DROP TABLE IF EXISTS `peizhihome`;
CREATE TABLE `peizhihome` (
  `id` int(11) NOT NULL auto_increment,
  `home_id` int(11) default NULL,
  `peizhi_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peizhihome
-- ----------------------------
INSERT INTO `peizhihome` VALUES ('1', '1', '3');
INSERT INTO `peizhihome` VALUES ('2', '1', '2');
INSERT INTO `peizhihome` VALUES ('3', '1', '4');

-- ----------------------------
-- Table structure for pre_home
-- ----------------------------
DROP TABLE IF EXISTS `pre_home`;
CREATE TABLE `pre_home` (
  `id` int(11) NOT NULL auto_increment,
  `home_id` int(11) default NULL,
  `pre_time` varchar(255) default NULL,
  `user_id` int(11) default NULL,
  `status` varchar(255) default NULL,
  `day` int(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pre_home
-- ----------------------------

-- ----------------------------
-- Table structure for real_order
-- ----------------------------
DROP TABLE IF EXISTS `real_order`;
CREATE TABLE `real_order` (
  `id` int(11) NOT NULL auto_increment,
  `foodid` int(11) default NULL,
  `orderid` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of real_order
-- ----------------------------
INSERT INTO `real_order` VALUES ('1', '1', '1');
INSERT INTO `real_order` VALUES ('2', '7', '1');
INSERT INTO `real_order` VALUES ('3', '8', '1');
INSERT INTO `real_order` VALUES ('20', '14', '11');
INSERT INTO `real_order` VALUES ('21', '1', '11');
INSERT INTO `real_order` VALUES ('22', '14', '12');
INSERT INTO `real_order` VALUES ('23', '1', '12');
INSERT INTO `real_order` VALUES ('24', '14', '13');
INSERT INTO `real_order` VALUES ('25', '1', '13');
INSERT INTO `real_order` VALUES ('26', '14', '14');
INSERT INTO `real_order` VALUES ('27', '1', '14');
INSERT INTO `real_order` VALUES ('28', '14', '15');
INSERT INTO `real_order` VALUES ('29', '1', '15');
INSERT INTO `real_order` VALUES ('30', '14', '16');
INSERT INTO `real_order` VALUES ('31', '1', '16');
INSERT INTO `real_order` VALUES ('32', '7', '16');
INSERT INTO `real_order` VALUES ('33', '8', '16');
INSERT INTO `real_order` VALUES ('34', '14', '17');
INSERT INTO `real_order` VALUES ('35', '1', '17');
INSERT INTO `real_order` VALUES ('36', '7', '17');
INSERT INTO `real_order` VALUES ('37', '8', '17');
INSERT INTO `real_order` VALUES ('38', '9', '17');
INSERT INTO `real_order` VALUES ('39', '14', '18');
INSERT INTO `real_order` VALUES ('40', '7', '18');
INSERT INTO `real_order` VALUES ('41', '14', '19');
INSERT INTO `real_order` VALUES ('42', '7', '19');
INSERT INTO `real_order` VALUES ('43', '14', '20');
INSERT INTO `real_order` VALUES ('44', '7', '20');
INSERT INTO `real_order` VALUES ('45', '13', '20');
INSERT INTO `real_order` VALUES ('46', '8', '20');
INSERT INTO `real_order` VALUES ('47', '9', '20');
INSERT INTO `real_order` VALUES ('48', '14', '21');
INSERT INTO `real_order` VALUES ('49', '7', '21');
INSERT INTO `real_order` VALUES ('50', '13', '21');
INSERT INTO `real_order` VALUES ('51', '8', '21');
INSERT INTO `real_order` VALUES ('52', '9', '21');
INSERT INTO `real_order` VALUES ('53', '14', '22');
INSERT INTO `real_order` VALUES ('54', '9', '22');
INSERT INTO `real_order` VALUES ('55', '14', '23');
INSERT INTO `real_order` VALUES ('56', '9', '23');
INSERT INTO `real_order` VALUES ('57', '14', '24');
INSERT INTO `real_order` VALUES ('58', '9', '24');
INSERT INTO `real_order` VALUES ('59', '14', '25');
INSERT INTO `real_order` VALUES ('60', '9', '25');
INSERT INTO `real_order` VALUES ('61', '14', '26');
INSERT INTO `real_order` VALUES ('62', '9', '26');
INSERT INTO `real_order` VALUES ('63', '14', '27');
INSERT INTO `real_order` VALUES ('64', '9', '27');
INSERT INTO `real_order` VALUES ('65', '14', '28');
INSERT INTO `real_order` VALUES ('66', '9', '28');
INSERT INTO `real_order` VALUES ('67', '14', '29');
INSERT INTO `real_order` VALUES ('68', '9', '29');
INSERT INTO `real_order` VALUES ('69', '14', '30');
INSERT INTO `real_order` VALUES ('70', '9', '30');
INSERT INTO `real_order` VALUES ('71', '8', '30');
INSERT INTO `real_order` VALUES ('72', '10', '30');
INSERT INTO `real_order` VALUES ('73', '11', '30');
INSERT INTO `real_order` VALUES ('74', '14', '31');
INSERT INTO `real_order` VALUES ('75', '11', '31');
INSERT INTO `real_order` VALUES ('76', '14', '32');
INSERT INTO `real_order` VALUES ('77', '11', '32');
INSERT INTO `real_order` VALUES ('78', '14', '33');
INSERT INTO `real_order` VALUES ('79', '11', '33');
INSERT INTO `real_order` VALUES ('80', '14', '34');
INSERT INTO `real_order` VALUES ('81', '11', '34');
INSERT INTO `real_order` VALUES ('82', '8', '34');
INSERT INTO `real_order` VALUES ('83', '9', '34');
INSERT INTO `real_order` VALUES ('84', '14', '35');
INSERT INTO `real_order` VALUES ('85', '11', '35');
INSERT INTO `real_order` VALUES ('86', '8', '35');
INSERT INTO `real_order` VALUES ('87', '9', '35');
INSERT INTO `real_order` VALUES ('88', '14', '36');
INSERT INTO `real_order` VALUES ('89', '11', '36');
INSERT INTO `real_order` VALUES ('90', '8', '36');
INSERT INTO `real_order` VALUES ('91', '9', '36');
INSERT INTO `real_order` VALUES ('92', '14', '37');
INSERT INTO `real_order` VALUES ('93', '11', '37');
INSERT INTO `real_order` VALUES ('94', '8', '37');
INSERT INTO `real_order` VALUES ('95', '9', '37');
INSERT INTO `real_order` VALUES ('96', '14', '38');
INSERT INTO `real_order` VALUES ('97', '11', '38');
INSERT INTO `real_order` VALUES ('98', '8', '38');
INSERT INTO `real_order` VALUES ('99', '9', '38');
INSERT INTO `real_order` VALUES ('100', '14', '39');
INSERT INTO `real_order` VALUES ('101', '11', '39');
INSERT INTO `real_order` VALUES ('102', '8', '39');
INSERT INTO `real_order` VALUES ('103', '9', '39');
INSERT INTO `real_order` VALUES ('104', '14', '40');
INSERT INTO `real_order` VALUES ('105', '11', '40');
INSERT INTO `real_order` VALUES ('106', '8', '40');
INSERT INTO `real_order` VALUES ('107', '9', '40');
INSERT INTO `real_order` VALUES ('108', '14', '41');
INSERT INTO `real_order` VALUES ('109', '11', '41');
INSERT INTO `real_order` VALUES ('110', '8', '41');
INSERT INTO `real_order` VALUES ('111', '9', '41');
INSERT INTO `real_order` VALUES ('112', '14', '42');
INSERT INTO `real_order` VALUES ('113', '1', '42');
INSERT INTO `real_order` VALUES ('115', '14', '43');
INSERT INTO `real_order` VALUES ('116', '1', '43');
INSERT INTO `real_order` VALUES ('117', '7', '43');
INSERT INTO `real_order` VALUES ('119', '9', '43');
INSERT INTO `real_order` VALUES ('120', '14', '44');
INSERT INTO `real_order` VALUES ('121', '1', '44');
INSERT INTO `real_order` VALUES ('122', '7', '44');
INSERT INTO `real_order` VALUES ('123', '8', '44');
INSERT INTO `real_order` VALUES ('124', '9', '44');
INSERT INTO `real_order` VALUES ('125', '14', '45');
INSERT INTO `real_order` VALUES ('126', '1', '45');
INSERT INTO `real_order` VALUES ('127', '7', '45');
INSERT INTO `real_order` VALUES ('128', '8', '45');
INSERT INTO `real_order` VALUES ('129', '9', '45');
INSERT INTO `real_order` VALUES ('130', '14', '46');
INSERT INTO `real_order` VALUES ('131', '1', '46');
INSERT INTO `real_order` VALUES ('132', '7', '46');
INSERT INTO `real_order` VALUES ('147', '14', '53');
INSERT INTO `real_order` VALUES ('148', '7', '53');
INSERT INTO `real_order` VALUES ('149', '14', '54');
INSERT INTO `real_order` VALUES ('150', '7', '54');
INSERT INTO `real_order` VALUES ('151', '14', '55');
INSERT INTO `real_order` VALUES ('152', '7', '55');
INSERT INTO `real_order` VALUES ('153', '14', '56');
INSERT INTO `real_order` VALUES ('154', '7', '56');
INSERT INTO `real_order` VALUES ('155', '8', '56');
INSERT INTO `real_order` VALUES ('156', '9', '56');
INSERT INTO `real_order` VALUES ('157', '14', '57');
INSERT INTO `real_order` VALUES ('158', '7', '57');
INSERT INTO `real_order` VALUES ('159', '8', '57');
INSERT INTO `real_order` VALUES ('160', '9', '57');
INSERT INTO `real_order` VALUES ('161', '14', '58');
INSERT INTO `real_order` VALUES ('162', '7', '58');
INSERT INTO `real_order` VALUES ('163', '8', '58');
INSERT INTO `real_order` VALUES ('164', '9', '58');
INSERT INTO `real_order` VALUES ('165', '14', '59');
INSERT INTO `real_order` VALUES ('166', '7', '59');
INSERT INTO `real_order` VALUES ('167', '8', '59');
INSERT INTO `real_order` VALUES ('168', '9', '59');
INSERT INTO `real_order` VALUES ('169', '14', '60');
INSERT INTO `real_order` VALUES ('170', '7', '60');
INSERT INTO `real_order` VALUES ('171', '8', '60');
INSERT INTO `real_order` VALUES ('172', '9', '60');
INSERT INTO `real_order` VALUES ('173', '14', '61');
INSERT INTO `real_order` VALUES ('174', '7', '61');
INSERT INTO `real_order` VALUES ('175', '8', '61');
INSERT INTO `real_order` VALUES ('176', '9', '61');
INSERT INTO `real_order` VALUES ('177', '14', '62');
INSERT INTO `real_order` VALUES ('178', '7', '62');
INSERT INTO `real_order` VALUES ('179', '8', '62');
INSERT INTO `real_order` VALUES ('180', '9', '62');
INSERT INTO `real_order` VALUES ('181', '11', '63');
INSERT INTO `real_order` VALUES ('182', '13', '63');
INSERT INTO `real_order` VALUES ('183', '9', '63');
INSERT INTO `real_order` VALUES ('184', '11', '64');
INSERT INTO `real_order` VALUES ('185', '13', '64');
INSERT INTO `real_order` VALUES ('186', '9', '64');
INSERT INTO `real_order` VALUES ('187', '1', '64');
INSERT INTO `real_order` VALUES ('188', '0', '64');
INSERT INTO `real_order` VALUES ('189', '7', '64');
INSERT INTO `real_order` VALUES ('190', '0', '65');
INSERT INTO `real_order` VALUES ('191', '7', '65');
INSERT INTO `real_order` VALUES ('192', '1', '65');
INSERT INTO `real_order` VALUES ('193', '0', '66');
INSERT INTO `real_order` VALUES ('194', '7', '66');
INSERT INTO `real_order` VALUES ('195', '1', '66');
INSERT INTO `real_order` VALUES ('196', '8', '66');
INSERT INTO `real_order` VALUES ('197', '9', '66');
INSERT INTO `real_order` VALUES ('198', '0', '67');
INSERT INTO `real_order` VALUES ('199', '7', '67');
INSERT INTO `real_order` VALUES ('200', '1', '67');
INSERT INTO `real_order` VALUES ('201', '8', '67');
INSERT INTO `real_order` VALUES ('202', '9', '67');
INSERT INTO `real_order` VALUES ('203', '11', '67');
INSERT INTO `real_order` VALUES ('204', '10', '67');
INSERT INTO `real_order` VALUES ('205', '0', '68');
INSERT INTO `real_order` VALUES ('206', '7', '68');
INSERT INTO `real_order` VALUES ('207', '1', '68');
INSERT INTO `real_order` VALUES ('208', '8', '68');
INSERT INTO `real_order` VALUES ('209', '9', '68');
INSERT INTO `real_order` VALUES ('210', '11', '68');
INSERT INTO `real_order` VALUES ('211', '10', '68');
INSERT INTO `real_order` VALUES ('212', '0', '69');
INSERT INTO `real_order` VALUES ('213', '7', '69');
INSERT INTO `real_order` VALUES ('214', '8', '69');
INSERT INTO `real_order` VALUES ('215', '10', '69');
INSERT INTO `real_order` VALUES ('216', '13', '69');
INSERT INTO `real_order` VALUES ('217', '15', '69');
INSERT INTO `real_order` VALUES ('218', '0', '70');
INSERT INTO `real_order` VALUES ('219', '7', '70');
INSERT INTO `real_order` VALUES ('220', '8', '70');
INSERT INTO `real_order` VALUES ('221', '10', '70');
INSERT INTO `real_order` VALUES ('222', '13', '70');
INSERT INTO `real_order` VALUES ('223', '15', '70');
INSERT INTO `real_order` VALUES ('224', '1', '70');
INSERT INTO `real_order` VALUES ('225', '7', '71');
INSERT INTO `real_order` VALUES ('226', '1', '71');
INSERT INTO `real_order` VALUES ('227', '8', '71');
INSERT INTO `real_order` VALUES ('228', '9', '71');
INSERT INTO `real_order` VALUES ('229', '10', '71');
INSERT INTO `real_order` VALUES ('230', '13', '71');
INSERT INTO `real_order` VALUES ('231', '15', '71');
INSERT INTO `real_order` VALUES ('232', '7', '72');
INSERT INTO `real_order` VALUES ('233', '1', '72');
INSERT INTO `real_order` VALUES ('234', '8', '72');
INSERT INTO `real_order` VALUES ('235', '9', '72');
INSERT INTO `real_order` VALUES ('236', '10', '72');
INSERT INTO `real_order` VALUES ('237', '13', '72');
INSERT INTO `real_order` VALUES ('238', '15', '72');
INSERT INTO `real_order` VALUES ('239', '7', '73');
INSERT INTO `real_order` VALUES ('240', '1', '73');
INSERT INTO `real_order` VALUES ('241', '8', '73');
INSERT INTO `real_order` VALUES ('242', '9', '73');
INSERT INTO `real_order` VALUES ('243', '10', '73');
INSERT INTO `real_order` VALUES ('244', '13', '73');
INSERT INTO `real_order` VALUES ('245', '15', '73');
INSERT INTO `real_order` VALUES ('246', '7', '74');
INSERT INTO `real_order` VALUES ('247', '8', '74');
INSERT INTO `real_order` VALUES ('248', '9', '74');
INSERT INTO `real_order` VALUES ('249', '10', '74');
INSERT INTO `real_order` VALUES ('250', '13', '74');
INSERT INTO `real_order` VALUES ('251', '15', '74');
INSERT INTO `real_order` VALUES ('252', '7', '75');
INSERT INTO `real_order` VALUES ('253', '8', '75');
INSERT INTO `real_order` VALUES ('254', '9', '75');
INSERT INTO `real_order` VALUES ('255', '10', '75');
INSERT INTO `real_order` VALUES ('256', '13', '75');
INSERT INTO `real_order` VALUES ('257', '15', '75');
INSERT INTO `real_order` VALUES ('258', '1', '75');
INSERT INTO `real_order` VALUES ('259', '0', '76');
INSERT INTO `real_order` VALUES ('260', '8', '76');
INSERT INTO `real_order` VALUES ('261', '10', '76');
INSERT INTO `real_order` VALUES ('262', '11', '76');
INSERT INTO `real_order` VALUES ('263', '15', '76');
INSERT INTO `real_order` VALUES ('264', '0', '77');
INSERT INTO `real_order` VALUES ('265', '8', '77');
INSERT INTO `real_order` VALUES ('266', '10', '77');
INSERT INTO `real_order` VALUES ('267', '11', '77');
INSERT INTO `real_order` VALUES ('268', '15', '77');
INSERT INTO `real_order` VALUES ('269', '13', '77');
INSERT INTO `real_order` VALUES ('270', '1', '77');
INSERT INTO `real_order` VALUES ('271', '0', '78');
INSERT INTO `real_order` VALUES ('272', '13', '78');
INSERT INTO `real_order` VALUES ('273', '12', '78');
INSERT INTO `real_order` VALUES ('274', '10', '78');
INSERT INTO `real_order` VALUES ('275', '1', '78');

-- ----------------------------
-- Table structure for res_user
-- ----------------------------
DROP TABLE IF EXISTS `res_user`;
CREATE TABLE `res_user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `pwd` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `type` varchar(255) default NULL,
  `createtime` varchar(255) default NULL,
  `head` varchar(255) default NULL,
  `vip` varchar(255) default NULL,
  `count` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_user
-- ----------------------------
INSERT INTO `res_user` VALUES ('1', '李阳', '123', '16673478081', '2363582258@qq.com', null, '2019-5-17', '....', null, null);
INSERT INTO `res_user` VALUES ('2', 'admin', '123', '16673478081', 'admin', null, '2019-05-17 10:12:09', '/res/upLoad/HUQCZYWL7IA2.jpg', null, null);
INSERT INTO `res_user` VALUES ('3', 'ssy', 'a13521', '15970461521', '1965971645@qq.com', 'vip', '2019-09-22', null, null, null);

-- ----------------------------
-- Table structure for sellerinfo
-- ----------------------------
DROP TABLE IF EXISTS `sellerinfo`;
CREATE TABLE `sellerinfo` (
  `ID` int(11) NOT NULL auto_increment,
  `S_NAME` varchar(20) default NULL,
  `S_PWD` varchar(20) default NULL,
  `S_TEL` varchar(20) default NULL,
  `S_LICENCE` varchar(200) default NULL,
  `S_ADDR` varchar(255) default NULL,
  `S_EMAIL` varchar(50) default NULL,
  `S_PIC` varchar(255) default NULL,
  `S_STATE` int(10) default NULL,
  `AUDIT_STATE` int(10) default NULL,
  `S_DATE` datetime default NULL,
  `S_TEMP1` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sellerinfo
-- ----------------------------
INSERT INTO `sellerinfo` VALUES ('40045', 'lydia', 'a', '18175970260', '../images/14745337023536349.gif', '湖南省', 'qq@qq.com', '../images/14745337022336207.gif', '1', '1', '2019-10-03 08:07:07', null);
INSERT INTO `sellerinfo` VALUES ('40046', 'a', 'a', '18175970212', '../images/14745337314811935.gif', '湖南省', 'qq@qq.com', '../images/14745337314805180.gif', '1', '1', '2019-10-03 08:07:07', null);
INSERT INTO `sellerinfo` VALUES ('40047', 'sd', 'a', '1', '../images/14745494781563160.gif', '1', '1', '../images/14745494781542325.gif', '1', '1', '2019-10-03 08:07:07', null);
INSERT INTO `sellerinfo` VALUES ('40048', 'ggg', 'a', '546', '../images/14745514265514995.gif', '65', '64', '../images/1474551426548637.gif', '1', '1', '2019-10-03 08:07:07', null);
INSERT INTO `sellerinfo` VALUES ('40061', 'q', 'q', '2324', '../images/14751488029774579.gif', 'fdg', '13', '../images/14751488029768730.gif', '1', '1', '2019-10-03 08:07:07', null);

-- ----------------------------
-- Table structure for setmeal
-- ----------------------------
DROP TABLE IF EXISTS `setmeal`;
CREATE TABLE `setmeal` (
  `ID` int(11) NOT NULL auto_increment,
  `GB_ID` int(11) default NULL,
  `SM_NAME` varchar(255) default NULL,
  `SM_CONTENT` varchar(255) default NULL,
  `SM_DIS` varchar(50) default NULL,
  `SM_TEMP` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of setmeal
-- ----------------------------

-- ----------------------------
-- Table structure for text
-- ----------------------------
DROP TABLE IF EXISTS `text`;
CREATE TABLE `text` (
  `id` int(11) NOT NULL auto_increment,
  `href` varchar(255) default NULL,
  `pic` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of text
-- ----------------------------
INSERT INTO `text` VALUES ('2', '', 'images/company-2.png');
INSERT INTO `text` VALUES ('3', '', 'images/company-3.png');
INSERT INTO `text` VALUES ('4', '', 'images/company-4.png');
INSERT INTO `text` VALUES ('5', '', 'images/company-5.png');
INSERT INTO `text` VALUES ('6', '', 'images/company-6.png');
INSERT INTO `text` VALUES ('7', '', 'images/company-1.png');
INSERT INTO `text` VALUES ('8', '', 'images/company-6.png');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `passwd` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `home_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '李阳', '123', '2363582258@qq.com', '123456789', '1');
INSERT INTO `user` VALUES ('2', '张三', '123', '2356', '123456789', '1');
INSERT INTO `user` VALUES ('3', '李四', '123', '4431223', '123456789', '1');
INSERT INTO `user` VALUES ('4', '王五', '123', '12245454', '123456756', '2');
INSERT INTO `user` VALUES ('5', '赵六', '123', '123154212', '122421', '2');
INSERT INTO `user` VALUES ('6', '鸡蛋', '123', '42154', '12544', '2');
