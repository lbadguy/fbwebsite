/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : xm-pro

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 09/06/2025 18:47:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (3, 'hh', 'admin', '管理员2', 'ADM', 'http://localhost:9090/files/download/1748875003523_Joji smoke3.jpg');
INSERT INTO `admin` VALUES (4, '12', 'admin', '管理员', 'ADM', NULL);
INSERT INTO `admin` VALUES (5, '刘欢缘', 'admin', '刘欢缘', 'ADM', 'http://localhost:9090/files/download/1748686302851_lhy.jpg');

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES (5, 1, '请假请假请假', '想请假', '2025-04-13 10:10:24', '审核通过', NULL);
INSERT INTO `apply` VALUES (6, 2, '打游戏', '我想打游戏', '2025-04-13 11:12:01', '审核拒绝', '不能');
INSERT INTO `apply` VALUES (7, 3, '健身', '希望可以', '2025-04-13 22:27:20', '待审核', NULL);

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '简介',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '打撒十大', 'http://localhost:9090/files/download/1748694851475_1.png', 'blblblblblbllbdsfkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj啊速度啦的卡卡大量的撒开了打开阿斯达克大量我的确偶滴轻武器让我任青浦阿二二我看了热舞克隆人微软将为人口两万人人离开微软看了看疯狂我认为九二六五', '<blockquote style=\"line-height: 2;\"><span style=\"font-size: 32px; font-family: 黑体;\">矩形的成形</span></blockquote><p><img src=\"http://localhost:9090/files/download/1748695347743_login.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">张婧仪，一个95后的年轻演员，最近在娱乐圈掀起了不小的波澜。她没有追逐流量，也没有被“甜妹”或“古偶女主”这样的标签绑住手脚。她用一个个鲜活的角色，告诉大家：演员不是被贴标签的商品，而是能装下无数人生的容器。</span></h1><p><br></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">婧仪自己也说过，标签是观众认识她的捷径，但她不想被困在里面。刚出道时，她也因为“校园剧女主”这个标签焦虑过。她甚至推掉了一部偶像剧的邀约，不是因为剧不好，而是她不想被定型。她说：“我想演更多不一样的人生。”这句话简单，却透着她对表演的认真。</span></h1>', '2025-05-22 18:15:16');
INSERT INTO `article` VALUES (2, '必啥', NULL, '必', NULL, '2025-05-23 20:53:24');
INSERT INTO `article` VALUES (3, '打撒十大', 'http://localhost:9090/files/download/1748694851475_1.png', 'blblblblblbllbdsfkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj啊速度啦的卡卡大量的撒开了打开阿斯达克大量我的确偶滴轻武器让我任青浦阿二二我看了热舞克隆人微软将为人口两万人人离开微软看了看疯狂我认为九二六五', '<blockquote style=\"line-height: 2;\"><span style=\"font-size: 32px; font-family: 黑体;\">矩形的成形</span></blockquote><p><img src=\"http://localhost:9090/files/download/1748695347743_login.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">张婧仪，一个95后的年轻演员，最近在娱乐圈掀起了不小的波澜。她没有追逐流量，也没有被“甜妹”或“古偶女主”这样的标签绑住手脚。她用一个个鲜活的角色，告诉大家：演员不是被贴标签的商品，而是能装下无数人生的容器。</span></h1><p><br></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">婧仪自己也说过，标签是观众认识她的捷径，但她不想被困在里面。刚出道时，她也因为“校园剧女主”这个标签焦虑过。她甚至推掉了一部偶像剧的邀约，不是因为剧不好，而是她不想被定型。她说：“我想演更多不一样的人生。”这句话简单，却透着她对表演的认真。</span></h1>', '2025-05-24 18:15:16');
INSERT INTO `article` VALUES (4, '必啥', NULL, '必', NULL, '2025-05-25 20:53:24');
INSERT INTO `article` VALUES (5, '打撒十大', 'http://localhost:9090/files/download/1748694851475_1.png', 'blblblblblbllbdsfkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj啊速度啦的卡卡大量的撒开了打开阿斯达克大量我的确偶滴轻武器让我任青浦阿二二我看了热舞克隆人微软将为人口两万人人离开微软看了看疯狂我认为九二六五', '<blockquote style=\"line-height: 2;\"><span style=\"font-size: 32px; font-family: 黑体;\">矩形的成形</span></blockquote><p><img src=\"http://localhost:9090/files/download/1748695347743_login.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">张婧仪，一个95后的年轻演员，最近在娱乐圈掀起了不小的波澜。她没有追逐流量，也没有被“甜妹”或“古偶女主”这样的标签绑住手脚。她用一个个鲜活的角色，告诉大家：演员不是被贴标签的商品，而是能装下无数人生的容器。</span></h1><p><br></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">婧仪自己也说过，标签是观众认识她的捷径，但她不想被困在里面。刚出道时，她也因为“校园剧女主”这个标签焦虑过。她甚至推掉了一部偶像剧的邀约，不是因为剧不好，而是她不想被定型。她说：“我想演更多不一样的人生。”这句话简单，却透着她对表演的认真。</span></h1>', '2025-05-26 18:15:16');
INSERT INTO `article` VALUES (6, '必啥', NULL, '必', NULL, '2025-05-27 20:53:24');
INSERT INTO `article` VALUES (7, '必啥', NULL, '必', NULL, '2025-05-28 20:53:24');
INSERT INTO `article` VALUES (8, '打撒十大', 'http://localhost:9090/files/download/1748694851475_1.png', 'blblblblblbllbdsfkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj啊速度啦的卡卡大量的撒开了打开阿斯达克大量我的确偶滴轻武器让我任青浦阿二二我看了热舞克隆人微软将为人口两万人人离开微软看了看疯狂我认为九二六五', '<blockquote style=\"line-height: 2;\"><span style=\"font-size: 32px; font-family: 黑体;\">矩形的成形</span></blockquote><p><img src=\"http://localhost:9090/files/download/1748695347743_login.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">张婧仪，一个95后的年轻演员，最近在娱乐圈掀起了不小的波澜。她没有追逐流量，也没有被“甜妹”或“古偶女主”这样的标签绑住手脚。她用一个个鲜活的角色，告诉大家：演员不是被贴标签的商品，而是能装下无数人生的容器。</span></h1><p><br></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">婧仪自己也说过，标签是观众认识她的捷径，但她不想被困在里面。刚出道时，她也因为“校园剧女主”这个标签焦虑过。她甚至推掉了一部偶像剧的邀约，不是因为剧不好，而是她不想被定型。她说：“我想演更多不一样的人生。”这句话简单，却透着她对表演的认真。</span></h1>', '2025-05-26 18:15:16');
INSERT INTO `article` VALUES (9, '打撒十大', 'http://localhost:9090/files/download/1748694851475_1.png', 'blblblblblbllbdsfkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj啊速度啦的卡卡大量的撒开了打开阿斯达克大量我的确偶滴轻武器让我任青浦阿二二我看了热舞克隆人微软将为人口两万人人离开微软看了看疯狂我认为九二六五', '<blockquote style=\"line-height: 2;\"><span style=\"font-size: 32px; font-family: 黑体;\">矩形的成形</span></blockquote><p><img src=\"http://localhost:9090/files/download/1748695347743_login.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">张婧仪，一个95后的年轻演员，最近在娱乐圈掀起了不小的波澜。她没有追逐流量，也没有被“甜妹”或“古偶女主”这样的标签绑住手脚。她用一个个鲜活的角色，告诉大家：演员不是被贴标签的商品，而是能装下无数人生的容器。</span></h1><p><br></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">婧仪自己也说过，标签是观众认识她的捷径，但她不想被困在里面。刚出道时，她也因为“校园剧女主”这个标签焦虑过。她甚至推掉了一部偶像剧的邀约，不是因为剧不好，而是她不想被定型。她说：“我想演更多不一样的人生。”这句话简单，却透着她对表演的认真。</span></h1>', '2025-05-26 18:15:16');
INSERT INTO `article` VALUES (10, '打撒十大', 'http://localhost:9090/files/download/1748694851475_1.png', 'blblblblblbllbdsfkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj啊速度啦的卡卡大量的撒开了打开阿斯达克大量我的确偶滴轻武器让我任青浦阿二二我看了热舞克隆人微软将为人口两万人人离开微软看了看疯狂我认为九二六五', '<blockquote style=\"line-height: 2;\"><span style=\"font-size: 32px; font-family: 黑体;\">矩形的成形</span></blockquote><p><img src=\"http://localhost:9090/files/download/1748695347743_login.jpg\" alt=\"\" data-href=\"\" style=\"width: 100%;\"/></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">张婧仪，一个95后的年轻演员，最近在娱乐圈掀起了不小的波澜。她没有追逐流量，也没有被“甜妹”或“古偶女主”这样的标签绑住手脚。她用一个个鲜活的角色，告诉大家：演员不是被贴标签的商品，而是能装下无数人生的容器。</span></h1><p><br></p><h1 style=\"line-height: 2;\"><span style=\"color: rgb(36, 36, 36); background-color: rgb(247, 247, 247);\">婧仪自己也说过，标签是观众认识她的捷径，但她不想被困在里面。刚出道时，她也因为“校园剧女主”这个标签焦虑过。她甚至推掉了一部偶像剧的邀约，不是因为剧不好，而是她不想被定型。她说：“我想演更多不一样的人生。”这句话简单，却透着她对表演的认真。</span></h1>', '2025-05-26 18:15:16');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '图书ID',
  `isbn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ISBN',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书名',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `publisher` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版社',
  `publish_date` date NULL DEFAULT NULL COMMENT '出版日期',
  `category_id` bigint NULL DEFAULT NULL COMMENT '分类ID',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '简介',
  `location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '馆藏位置',
  `status` tinyint NULL DEFAULT 1 COMMENT '1:可借,0:已借出,2:损坏,3:丢失',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_book_category`(`category_id` ASC) USING BTREE,
  CONSTRAINT `fk_book_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '111', '广应科失业指南', '广应科校长', '广应科', '2025-06-09', 11, 30000.00, '四年浪费我二十万', '广东省肇庆市鼎湖区莲花镇', 1, '2025-06-09 18:22:49', '2025-06-09 18:22:49');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '借阅ID',
  `book_id` bigint NOT NULL COMMENT '图书ID',
  `reader_id` bigint NOT NULL COMMENT '读者ID',
  `borrow_date` date NOT NULL COMMENT '借出日期',
  `due_date` date NOT NULL COMMENT '应还日期',
  `return_date` date NULL DEFAULT NULL COMMENT '归还日期',
  `status` tinyint NULL DEFAULT 1 COMMENT '1:已借出,2:已归还,3:已续借,4:已逾期',
  `fine` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '罚金',
  `operator_id` bigint NULL DEFAULT NULL COMMENT '操作员ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_borrow_book`(`book_id` ASC) USING BTREE,
  INDEX `fk_borrow_reader`(`reader_id` ASC) USING BTREE,
  INDEX `fk_borrow_operator`(`operator_id` ASC) USING BTREE,
  CONSTRAINT `fk_borrow_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_borrow_operator` FOREIGN KEY (`operator_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `fk_borrow_reader` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `province_id` int NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `leader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `front` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES (1, '1111', '123', '如家', 'http://localhost:9090/files/download/1744902471734_狗头.jpg', 'BUSINESS', 2, NULL, '3232', 102, '3232', '32', NULL, 'http://localhost:9090/files/download/1744969218141_维也纳酒店.jpg', '张震涛', '3232', NULL, NULL, '通过');
INSERT INTO `business` VALUES (2, 'ttt', '123', '他他他', NULL, 'BUSINESS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父分类ID',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_category_parent`(`parent_id` ASC) USING BTREE,
  CONSTRAINT `fk_category_parent` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '文学', NULL, '包括小说、诗歌、散文等文学作品');
INSERT INTO `category` VALUES (2, '科技', NULL, '包括科学、技术、工程等相关书籍');
INSERT INTO `category` VALUES (3, '历史', NULL, '历史相关书籍');
INSERT INTO `category` VALUES (4, '哲学', NULL, '哲学相关书籍');
INSERT INTO `category` VALUES (5, '艺术', NULL, '艺术相关书籍');
INSERT INTO `category` VALUES (6, '教育', NULL, '教育相关书籍');
INSERT INTO `category` VALUES (7, '计算机', NULL, '计算机相关书籍');
INSERT INTO `category` VALUES (8, '经济', NULL, '经济相关书籍');
INSERT INTO `category` VALUES (9, '医学', NULL, '医学相关书籍');
INSERT INTO `category` VALUES (10, '其他', NULL, '其他类别书籍');
INSERT INTO `category` VALUES (11, '爱情', NULL, '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊');
INSERT INTO `category` VALUES (15, '爱情', NULL, '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '部门' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (2, '销售部');
INSERT INTO `department` VALUES (3, '后勤部');
INSERT INTO `department` VALUES (4, '技术部');
INSERT INTO `department` VALUES (5, '采购部');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名字',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工号',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '个人介绍',
  `department_id` int NULL DEFAULT NULL COMMENT '部门ID',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (3, 'hh', '123456', 'EMP', 'Zywo3', 'male', '333', 23, '', 5, NULL);
INSERT INTO `employee` VALUES (5, 'goat', '123', 'EMP', 's1mple2', '男', '666', 26, '', 3, 'http://localhost:9090/files/download/1748598573900_1-11.jpg');
INSERT INTO `employee` VALUES (25, 'qq', '123', 'EMP', 'qq', '男', '333444', NULL, '', 4, NULL);
INSERT INTO `employee` VALUES (26, 'vv', '222', 'EMP', 'v2', '男', '123', 20, 'hhh', 5, NULL);
INSERT INTO `employee` VALUES (27, 'fw', '123', 'EMP', 'fw', NULL, '12', NULL, NULL, 5, NULL);
INSERT INTO `employee` VALUES (28, '辉煌', '123', 'EMP', '无敌', '女', '7676', 18, '无敌', 2, 'http://localhost:9090/files/download/1748706184671_121_传统.png');
INSERT INTO `employee` VALUES (44, 'sb', '111', 'EMP', 'sb', NULL, '1234', NULL, NULL, NULL, NULL);
INSERT INTO `employee` VALUES (45, 'bg', '114514', 'EMP', 'bg', NULL, '159', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `operation_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'IP',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_log_user`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_log_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log
-- ----------------------------

-- ----------------------------
-- Table structure for province
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of province
-- ----------------------------
INSERT INTO `province` VALUES (2, '广东');
INSERT INTO `province` VALUES (3, '上海');
INSERT INTO `province` VALUES (4, '北京');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '读者ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `card_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '借书证号',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '读者类型',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '简介',
  `max_borrow_num` int NULL DEFAULT 5 COMMENT '最大借书数',
  `max_borrow_days` int NULL DEFAULT 30 COMMENT '最大借书天数',
  `status` tinyint NULL DEFAULT 1 COMMENT '1:正常,0:禁用',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `card_id`(`card_id` ASC) USING BTREE,
  INDEX `fk_reader_user`(`user_id` ASC) USING BTREE,
  CONSTRAINT `fk_reader_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader
-- ----------------------------

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `book_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for reservation
-- ----------------------------
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '预约ID',
  `book_id` bigint NOT NULL COMMENT '图书ID',
  `reader_id` bigint NOT NULL COMMENT '读者ID',
  `reservation_date` date NOT NULL COMMENT '预约日期',
  `expiration_date` date NOT NULL COMMENT '过期日期',
  `status` tinyint NULL DEFAULT 1 COMMENT '1:预约中,2:已取消,3:已借出,4:已过期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_reservation_book`(`book_id` ASC) USING BTREE,
  INDEX `fk_reservation_reader`(`reader_id` ASC) USING BTREE,
  CONSTRAINT `fk_reservation_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `fk_reservation_reader` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reservation
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `status` tinyint NULL DEFAULT 1 COMMENT '状态',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '管理员', NULL, 'ADMIN', 'admin@library.com', NULL, 1, '2025-06-09 11:29:51', NULL);

-- ----------------------------
-- Table structure for user_balance
-- ----------------------------
DROP TABLE IF EXISTS `user_balance`;
CREATE TABLE `user_balance`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `acc_no` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '余额系统生成的账户唯一标示',
  `acc_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '余额账户类型，0-现金；1-赠送金',
  `currency` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账户币种，如usd美元账户，与用户所属国家绑定',
  `balance` bigint NULL DEFAULT NULL COMMENT '账户余额，以分为单位',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_ub_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_ub_acc_no`(`acc_no` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户个人余额账户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_balance
-- ----------------------------

-- ----------------------------
-- Table structure for user_balance_flow
-- ----------------------------
DROP TABLE IF EXISTS `user_balance_flow`;
CREATE TABLE `user_balance_flow`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '业务方用户id',
  `flow_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账户流水号,与业务方发起的流水号映射',
  `acc_no` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账户唯一标示',
  `busi_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '余额流水业务类型,0-订单结费；1-购买月卡',
  `amount` bigint NULL DEFAULT NULL COMMENT '变动金额，以分为单位，区分正负；如：+10，-10',
  `currency` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '币种',
  `begin_balance` bigint NULL DEFAULT NULL COMMENT '变动前余额',
  `end_balance` bigint NULL DEFAULT NULL COMMENT '变动后余额',
  `fund_direct` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '借贷方向，00-借方；01-贷方',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_ubf_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_ubf_acc_no`(`acc_no` ASC) USING BTREE,
  INDEX `idx_ubf_flow_no`(`flow_no` ASC) USING BTREE,
  INDEX `idx_ubf_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '余额变动流水信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_balance_flow
-- ----------------------------

-- ----------------------------
-- Table structure for user_balance_order
-- ----------------------------
DROP TABLE IF EXISTS `user_balance_order`;
CREATE TABLE `user_balance_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单号id',
  `user_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `amount` bigint NULL DEFAULT NULL COMMENT '交易金额',
  `trade_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '交易类型，charge-余额充值；refund-余额退款',
  `currency` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '币种',
  `trade_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付渠道流水号',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付状态，0-待支付；1-支付中；2-支付成功；3-支付失败',
  `is_renew` int NULL DEFAULT 0 COMMENT '是否自动续费充值，0-不自动续费；1-自动续费',
  `trade_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `unique_idx_order_id`(`order_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_trade_time`(`trade_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '电子钱包余额订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_balance_order
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
