/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : newbee_mall_db

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 01/08/2023 00:54:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for p2mj_mall_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `p2mj_mall_goods_info`;
CREATE TABLE `p2mj_mall_goods_info`  (
  `goods_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品表主键id',
  `goods_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名',
  `goods_intro` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品简介',
  `goods_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联分类id',
  `goods_cover_img` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品主图',
  `goods_carousel` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品轮播图',
  `goods_detail_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品详情',
  `original_price` int(11) NOT NULL DEFAULT 1 COMMENT '商品价格',
  `selling_price` int(11) NOT NULL DEFAULT 1 COMMENT '商品实际售价',
  `stock_num` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存数量',
  `tag` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品标签',
  `goods_sell_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '商品上架状态 0-下架 1-上架',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '添加者主键id',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '商品添加时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者主键id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '商品修改时间',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10907 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
