/*
 Navicat Premium Data Transfer

 Source Server         : 45.145.228.53
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : 45.145.228.53:3306
 Source Schema         : nikon

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 13/07/2024 13:30:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_id` bigint NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subject` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'inventory_id',
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES (1, 1, 3, '2024-06-16 16:32:24', '2024-06-24 19:58:15');
INSERT INTO `inventory` VALUES (2, 2, 2, '2024-06-19 16:53:40', '2024-06-26 03:43:59');
INSERT INTO `inventory` VALUES (3, 3, 3, '2024-06-19 18:19:54', '2024-06-19 18:19:54');
INSERT INTO `inventory` VALUES (4, 4, 7, '2024-06-24 00:35:50', '2024-06-24 00:35:50');
INSERT INTO `inventory` VALUES (5, 5, 2, '2024-06-24 00:44:04', '2024-06-24 00:44:04');
INSERT INTO `inventory` VALUES (6, 6, 6, '2024-06-24 00:45:18', '2024-06-24 00:45:18');
INSERT INTO `inventory` VALUES (7, 7, 4, '2024-06-24 00:46:21', '2024-06-24 00:46:21');
INSERT INTO `inventory` VALUES (8, 8, 6, '2024-06-24 00:47:00', '2024-06-24 00:47:00');
INSERT INTO `inventory` VALUES (9, 9, 8, '2024-06-24 02:06:37', '2024-06-24 02:06:37');
INSERT INTO `inventory` VALUES (10, 10, 5, '2024-06-24 02:08:31', '2024-06-24 02:08:31');
INSERT INTO `inventory` VALUES (11, 11, 9, '2024-06-24 02:09:27', '2024-06-24 02:09:27');
INSERT INTO `inventory` VALUES (13, 13, 9, '2024-06-24 03:13:20', '2024-06-24 03:13:20');
INSERT INTO `inventory` VALUES (14, 14, 7, '2024-06-24 03:14:21', '2024-06-24 03:14:21');
INSERT INTO `inventory` VALUES (15, 15, 15, '2024-06-24 03:15:32', '2024-06-24 03:15:32');
INSERT INTO `inventory` VALUES (16, 16, 12, '2024-06-24 03:16:42', '2024-06-24 03:16:42');
INSERT INTO `inventory` VALUES (17, 17, 12, '2024-06-24 03:17:26', '2024-06-24 03:17:26');
INSERT INTO `inventory` VALUES (18, 18, 10, '2024-06-24 03:18:30', '2024-06-24 03:18:30');
INSERT INTO `inventory` VALUES (19, 19, 7, '2024-06-24 03:19:59', '2024-06-24 03:19:59');
INSERT INTO `inventory` VALUES (20, 20, 14, '2024-06-24 03:21:04', '2024-06-24 03:21:04');
INSERT INTO `inventory` VALUES (21, 21, 6, '2024-06-24 03:21:52', '2024-06-24 03:21:52');
INSERT INTO `inventory` VALUES (22, 22, 18, '2024-06-26 00:26:40', '2024-06-26 00:26:40');
INSERT INTO `inventory` VALUES (23, 23, 12, '2024-06-26 00:28:33', '2024-06-26 00:28:33');
INSERT INTO `inventory` VALUES (24, 24, 10, '2024-06-26 00:31:53', '2024-06-26 00:31:53');
INSERT INTO `inventory` VALUES (25, 25, 10, '2024-06-26 00:34:26', '2024-06-26 00:34:26');
INSERT INTO `inventory` VALUES (26, 26, 6, '2024-06-26 03:46:35', '2024-06-26 03:46:35');
INSERT INTO `inventory` VALUES (27, 27, 10, '2024-07-12 18:18:15', '2024-07-12 18:18:15');

-- ----------------------------
-- Table structure for product_details
-- ----------------------------
DROP TABLE IF EXISTS `product_details`;
CREATE TABLE `product_details`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'product_id',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_price` decimal(10, 2) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_discontinued` tinyint NOT NULL DEFAULT 0,
  `product_url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_details
-- ----------------------------
INSERT INTO `product_details` VALUES (1, '5fdc0616-177a-4b3f-b8b2-db44642d2180', 'nikon z9', 'camera', 24338.99, '2024-06-16 16:32:24', '2024-06-19 16:35:59', 1, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/2401db14085b3641c1bceda9e58f1ccb4e.jpg');
INSERT INTO `product_details` VALUES (2, '5e1e56f1-3bc4-48c0-8fec-d24f5d137c64', 'nikon z8', 'camera', 18338.99, '2024-06-19 16:53:40', '2024-06-24 00:13:04', 1, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/261f0d78694fbe48a88fc7817a76778714.jpg');
INSERT INTO `product_details` VALUES (3, '939234cb-60f3-477a-beea-54358cff354b', 'nikon zfc', 'camera', 5588.99, '2024-06-19 18:19:54', '2024-06-24 00:12:58', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24ea235caa894e4a229cb8591cbe4c755e.jpg');
INSERT INTO `product_details` VALUES (4, '52d33827-a5d3-407a-8925-b1cab6dfd0f7', 'nikon zf', 'camera', 10088.99, '2024-06-24 00:35:50', '2024-06-24 00:35:50', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/241ce212ad996f4e368bef635b59f5528a.jpg');
INSERT INTO `product_details` VALUES (5, '5017cbc5-bd49-43e1-834f-8d40e6bbd18e', 'nikon z7ii', 'camera', 13998.99, '2024-06-24 00:44:04', '2024-06-26 00:37:26', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24cfe20acc94b64786a36296d58c7d208f.jpg');
INSERT INTO `product_details` VALUES (6, '591a5b4a-b53e-4edf-b21a-a13109eee4c1', 'nikon z7', 'camera', 9999.99, '2024-06-24 00:45:18', '2024-06-24 00:45:18', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24f0db9217a1234c59b098dc099fffb03c.jpg');
INSERT INTO `product_details` VALUES (7, 'a8c4fd40-0d28-450c-937a-95ea66921fc0', 'nikon z6iii', 'camera', 17018.99, '2024-06-24 00:46:21', '2024-06-24 00:46:21', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24fa4288875c72457798c1770c451f0a2b.jpg');
INSERT INTO `product_details` VALUES (8, '97595e43-f15a-4be4-a7f1-778a88c6c5d6', 'nikon z6ii', 'camera', 9988.99, '2024-06-24 00:47:00', '2024-06-24 00:47:00', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/243a19d8557ffd42a5b9fd894b4ca5c778.jpg');
INSERT INTO `product_details` VALUES (9, '34fbbe9d-e4b1-49ba-81d2-9819658407ed', 'nikon z5', 'camera', 6850.99, '2024-06-24 02:06:37', '2024-06-24 02:06:37', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/2403b0ba0cf8184635ba317439246a11ff.jpg');
INSERT INTO `product_details` VALUES (10, '9e3ba543-5f9b-4e90-b196-727ca5a1d93c', 'nikon z50', 'camera', 4680.99, '2024-06-24 02:08:31', '2024-06-26 00:37:26', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24162227c5938a49a2aa302a27f4c5d6e4.jpg');
INSERT INTO `product_details` VALUES (11, 'f8212c47-8a83-4739-8590-045afad7e6a7', 'nikon z30', 'camera', 4460.99, '2024-06-24 02:09:27', '2024-06-24 02:09:27', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24fb6e530d216a4f4b8603b6bdbf9e541e.jpg');
INSERT INTO `product_details` VALUES (13, '8272188a-3e75-42d4-9f3e-b8594dfbd8a0', 'nikkor Z 14-24mm f/2.8', 'len', 9166.99, '2024-06-24 03:13:20', '2024-06-24 03:13:20', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/241c5e74e587674c8990d8655908b8ff0d.jpg');
INSERT INTO `product_details` VALUES (14, '4d3a4e8f-58df-4351-8a73-fb99d1b18a70', 'nikkor Z 70-200mm f/2.8', 'len', 9399.99, '2024-06-24 03:14:21', '2024-06-24 03:14:21', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/248f337c35c1594cd08404982fa820ce70.jpg');
INSERT INTO `product_details` VALUES (15, '1b644de8-8a65-4913-9c91-2206c987609b', 'nikkor Z 24-120mm f/4', 'len', 4699.99, '2024-06-24 03:15:32', '2024-06-24 03:15:32', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24be72f3d4eaa949e68b550db9b14b1b44.jpg');
INSERT INTO `product_details` VALUES (16, '7334cd33-8854-46ff-8b1c-9d83bf881fa4', 'nikkor Z 14-30mm f/4', 'len', 4316.99, '2024-06-24 03:16:42', '2024-06-26 00:37:26', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24dfbfcc300a6e46d49918da64a0229bc3.jpg');
INSERT INTO `product_details` VALUES (17, '6e2d000d-c10d-4a4b-bc83-6c07b23290cf', 'nikkor Z 24-70mm f/2.8', 'len', 8640.99, '2024-06-24 03:17:26', '2024-06-24 03:17:26', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/2483de207e8bd34d018d8c4f54053e334f.jpg');
INSERT INTO `product_details` VALUES (18, 'f2d705d7-0b6c-4b2d-82fe-02e43dd8e52c', 'nikkor Z 135mm f/1.8s', 'len', 11888.99, '2024-06-24 03:18:30', '2024-06-24 03:18:30', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/247fc7d13e601047959678e83059a6262a.jpg');
INSERT INTO `product_details` VALUES (19, 'e33beca7-ad7d-41f1-8e8f-aac3cda1c1a6', 'nikkor Z 50mm f/1.2', 'len', 9167.99, '2024-06-24 03:19:59', '2024-06-26 00:37:26', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24f35041f3aa384f96a41a87cb3e2fa0e0.jpg');
INSERT INTO `product_details` VALUES (20, '5ceef571-0a27-49b6-a689-2cb656160981', 'nikkor Z 35mm f/1.8', 'len', 3792.99, '2024-06-24 03:21:04', '2024-06-24 03:21:04', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/24e7add127225b4d0682984bfab1ac5a3f.jpg');
INSERT INTO `product_details` VALUES (21, 'f3a15c07-f473-4c97-b4b8-9f6404cbd410', 'nikkor Z 85mm f/1.8', 'len', 3420.99, '2024-06-24 03:21:52', '2024-06-24 03:21:52', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/247a62745055454618b4f6e07d9d661172.jpg');
INSERT INTO `product_details` VALUES (22, '6808a7a9-ee70-42f5-9ab3-45d52ac13d27', 'nikon Z 105mm f/2.8', 'len', 4074.99, '2024-06-26 00:26:40', '2024-06-26 00:37:26', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/264d9ed30324ec4db0b2acbd1ba83983ea.jpg');
INSERT INTO `product_details` VALUES (23, 'd6dfc00c-5ba6-4dd1-a135-c47f8416e0c3', 'nikon Z 180-600mm f/5.6', 'len', 7899.99, '2024-06-26 00:28:33', '2024-06-26 00:33:18', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/261c3e73337a424b4d9f4b32b65c2f635f.jpg');
INSERT INTO `product_details` VALUES (24, '20ee820a-00ff-46a1-99dd-e5ce577d3486', 'nikon Z 24-200mm f/4-6.3', 'len', 3820.99, '2024-06-26 00:31:53', '2024-06-26 00:31:53', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/266239d506031b4e96ba7004f0d5729480.jpg');
INSERT INTO `product_details` VALUES (25, '4ec8349e-2589-43b9-bb85-3f8e8ff741ea', 'nikon Z 58mm f/0.95', 'len', 32340.99, '2024-06-26 00:34:26', '2024-06-26 00:34:26', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/2656067d50b30743e19ad07e98777a89e1.jpg');
INSERT INTO `product_details` VALUES (26, '3202b40e-64e6-4a4f-b3d9-24687ca35095', 'nikon Z 24mm f/1.8', 'len', 3998.99, '2024-06-26 03:46:35', '2024-06-26 03:46:35', 0, 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/267fab7d59a1d24765995027cfa90dffa2.jpg');

-- ----------------------------
-- Table structure for shipping_records
-- ----------------------------
DROP TABLE IF EXISTS `shipping_records`;
CREATE TABLE `shipping_records`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `credit_card_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `shipping_address` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `price` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shipping_records
-- ----------------------------
INSERT INTO `shipping_records` VALUES (1, 'dbc1f3a4-cea5-4381-939a-fff3c1e65dde', 1, 2, 1, '1234567848394', 'swe2209532@xmu.edu.my', 'xiamen university malaysia', '2024-06-16 22:39:45', 299.99);
INSERT INTO `shipping_records` VALUES (2, 'e6ef6b93-e9fc-4bef-ac26-4d448cc388e7', 1, 2, 1, '1234567848394', 'swe2209532@xmu.edu.my', 'xiamen university malaysia', '2024-06-20 00:11:32', 299.99);
INSERT INTO `shipping_records` VALUES (3, '1657b931-87d6-470d-bc89-f11af09f5a4a', 1, 2, 1, '1234567848394', 'swe2209524@xmu.edu.my', 'xiamen university malaysia', '2024-06-20 00:15:11', 299.99);
INSERT INTO `shipping_records` VALUES (4, '9f47d96c-981d-4e37-984b-88cfa3e96621', 1, 2, 1, '1234567848394', 'swe2209524@xmu.edu.my', 'xiamen university malaysia', '2024-06-24 17:35:56', 24338.99);
INSERT INTO `shipping_records` VALUES (5, '22eff4e7-3e89-41d4-bf0f-96dfe4503dd7', 1, 2, 1, '1234567848394', 'swe2209532@xmu.edu.my', 'xiamen university malaysia', '2024-06-24 17:42:41', 24338.99);
INSERT INTO `shipping_records` VALUES (6, '03972ed5-d586-40fa-9669-716817ec65cd', 2, 2, 1, '1234574573485', 'swe2209532@xmu.edu.my', 'Xiamen University Malaysian', '2024-06-24 19:52:05', 18338.99);
INSERT INTO `shipping_records` VALUES (7, 'a3790741-b8ed-453d-a812-b4237868a7a6', 2, 2, 3, '12347324732832', 'swe2209532@xmu.edu.my', 'Xiamen University Malaysian', '2024-06-25 23:25:51', 55016.97);
INSERT INTO `shipping_records` VALUES (8, '72d64020-43e9-464a-8f00-1695c71a2b94', 1, 2, 3, '432329849234324', 'swe2209532@xmu.edu.my', 'Xiamen University Malaysia', '2024-06-25 23:26:23', 30266.97);
INSERT INTO `shipping_records` VALUES (9, '6533dbe2-1ae9-4d66-afde-6bf74a483816', 7, 2, 1, '54534534534534', 'swe2209532@xmu.edu.my', 'Xiamen University Malaysian', '2024-06-25 23:27:09', 17018.99);
INSERT INTO `shipping_records` VALUES (10, '33441b30-c2ce-41ef-ad3f-927867cf6c3a', 1, 9, 1, '123343558239423', 'SWE2209535@xmu.edu.my', 'Xiamen University Malaysia', '2024-07-01 15:41:10', 24338.99);
INSERT INTO `shipping_records` VALUES (11, '5e5a96f8-9a74-4ff1-959e-adcad283b964', 7, 10, 1, '454545158484818', 'swe2209518@xmu.edu.my', 'Xiamen University Malaysia', '2024-07-01 15:45:00', 17018.99);
INSERT INTO `shipping_records` VALUES (12, 'c2bc1128-b0d2-4375-9ed1-0093e284091d', 1, 11, 2, '53662522672736', 'xingyuanbo3@gmail.com', 'Xiamen University Malaysia ', '2024-07-04 09:27:48', 48677.98);
INSERT INTO `shipping_records` VALUES (13, 'fb9f36cc-325a-42d8-a313-a075f78c69fa', 18, 11, 2, '353672537735367', 'xingyuanbo3@gmail.com', 'Xiamen university Malaysia ', '2024-07-04 09:28:55', 23777.98);
INSERT INTO `shipping_records` VALUES (14, 'a99f9af6-e1ea-4702-86a8-11fa66763050', 26, 12, 1, '1234567890998888', '222@xmu.edu.my', '222', '2024-07-06 11:19:46', 3998.99);
INSERT INTO `shipping_records` VALUES (15, '075a3b77-abe4-4905-8a15-3ab1baeae2db', 3, 14, 1, '123456789123456', '123456@gmail.com', 'sdauiuhw', '2024-07-09 02:33:50', 5588.99);
INSERT INTO `shipping_records` VALUES (16, '8aee5227-6711-4d29-8a2b-ca39a5dddee0', 2, 16, 4, '123456789101112', '312313@qq.com', 'Street 1, anywhere', '2024-07-12 18:03:58', 73355.96);
INSERT INTO `shipping_records` VALUES (17, 'd16ead19-f850-4878-ab1d-b102a9c54b2c', 5, 16, 3, '123456789101112', '123123213132@163.com', 'Street 1, anywhere', '2024-07-12 18:04:22', 41996.97);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_account` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/18d79b9565e4a74902aba4e845b8eabb61.png',
  `gender` tinyint NOT NULL DEFAULT 0,
  `user_role` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'user' COMMENT 'user / admin',
  `user_password` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` tinyint NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'maximum', 'maximum', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/254e1548e211034264843db36d937c8d06.png', 0, 'admin', '9fa74ad9bb3890929771a8a32e0fba7a', '2024-06-15 18:14:25', '2024-06-19 16:35:52', 0);
INSERT INTO `user` VALUES (2, 'nikon', 'nikonbackend', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/26da3ab99f4e404f5fa14c49dd7f61e769.png', 0, 'user', '9fa74ad9bb3890929771a8a32e0fba7a', '2024-06-16 12:23:35', '2024-06-17 15:14:46', 0);
INSERT INTO `user` VALUES (3, 'LXC123', '3414265673@qq.com', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/18d79b9565e4a74902aba4e845b8eabb61.png', 0, 'user', '947194b43dfdef24dc472686798f647a', '2024-06-27 06:38:08', '2024-06-27 06:38:08', 0);
INSERT INTO `user` VALUES (4, 'LXCLXC', 'swe2209524@xmu.edu.my', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/18d79b9565e4a74902aba4e845b8eabb61.png', 0, 'user', 'e4e299a37a5b9f4e7ceee66e897b4621', '2024-06-28 03:22:28', '2024-06-28 03:22:28', 0);
INSERT INTO `user` VALUES (5, 'momingshuai', 'SWE2209535@xmu.edu.my', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/07/014fc2d84cddb4499b9de665fd4820f6d7.jpg', 0, 'user', '9fa74ad9bb3890929771a8a32e0fba7a', '2024-07-01 15:36:11', '2024-07-01 15:36:11', 0);
INSERT INTO `user` VALUES (6, 'momoka', 'swe2209518@xmu.edu.my', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/07/0173671093594849cc8af21d9287d3c4b1.jpg', 0, 'user', '9fa74ad9bb3890929771a8a32e0fba7a', '2024-07-01 15:43:08', '2024-07-01 15:43:08', 0);
INSERT INTO `user` VALUES (7, 'Xing Yuanbo', 'xingyuanbo3@gmail.com', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/07/044e3ce9b11b4d4bfb8ce722606da4070f.jpeg', 0, 'user', 'aa92b68b672f5aca03a223484267bc25', '2024-07-04 09:26:08', '2024-07-04 09:26:08', 0);
INSERT INTO `user` VALUES (8, '12345', '12345', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/18d79b9565e4a74902aba4e845b8eabb61.png', 0, 'user', '9fa74ad9bb3890929771a8a32e0fba7a', '2024-07-06 11:19:03', '2024-07-06 11:19:03', 0);
INSERT INTO `user` VALUES (9, 'MARK', 'MARK001', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/18d79b9565e4a74902aba4e845b8eabb61.png', 0, 'user', '8371d50720254acb2e34ed943d43c366', '2024-07-07 14:32:54', '2024-07-07 14:32:54', 0);
INSERT INTO `user` VALUES (10, 'zhangjunhao', 'SWE2209548@xmu.edu.my', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/18d79b9565e4a74902aba4e845b8eabb61.png', 0, 'user', '9fa74ad9bb3890929771a8a32e0fba7a', '2024-07-09 02:21:45', '2024-07-09 02:21:45', 0);
INSERT INTO `user` VALUES (11, '1111111111', '1111111111', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/18d79b9565e4a74902aba4e845b8eabb61.png', 0, 'user', '0cf3051a8acf34d3543fdc0e75a0d161', '2024-07-12 07:48:48', '2024-07-12 07:48:48', 0);
INSERT INTO `user` VALUES (12, '12345678', '12345678', 'https://cdn.jsdelivr.net/gh/maximum2974/image/2024/06/18d79b9565e4a74902aba4e845b8eabb61.png', 0, 'user', '9fa74ad9bb3890929771a8a32e0fba7a', '2024-07-12 17:30:11', '2024-07-12 17:30:11', 0);

SET FOREIGN_KEY_CHECKS = 1;
