-- 切換到該資料庫
-- \c omochikaeri;

-- =============================================
-- 表結構：地址簿 (address_book)
-- =============================================
DROP TABLE IF EXISTS address_book;
CREATE TABLE address_book (
  id SERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  consignee VARCHAR(50) DEFAULT NULL,
  sex VARCHAR(2) DEFAULT NULL,
  phone VARCHAR(11) NOT NULL,
  province_code VARCHAR(12) DEFAULT NULL,
  province_name VARCHAR(32) DEFAULT NULL,
  city_code VARCHAR(12) DEFAULT NULL,
  city_name VARCHAR(32) DEFAULT NULL,
  district_code VARCHAR(12) DEFAULT NULL,
  district_name VARCHAR(32) DEFAULT NULL,
  detail VARCHAR(200) DEFAULT NULL,
  label VARCHAR(100) DEFAULT NULL,
  is_default SMALLINT NOT NULL DEFAULT 0
);
COMMENT ON TABLE address_book IS '地址簿';
COMMENT ON COLUMN address_book.id IS '主鍵';
COMMENT ON COLUMN address_book.user_id IS '使用者ID';
COMMENT ON COLUMN address_book.consignee IS '收貨人';
COMMENT ON COLUMN address_book.sex IS '性別';
COMMENT ON COLUMN address_book.phone IS '手機號碼';
COMMENT ON COLUMN address_book.detail IS '詳細地址';
COMMENT ON COLUMN address_book.label IS '標籤';
COMMENT ON COLUMN address_book.is_default IS '預設 0 否 1 是';

-- =============================================
-- 表結構：分類 (category)
-- =============================================
DROP TABLE IF EXISTS category;
CREATE TABLE category (
  id SERIAL PRIMARY KEY,
  type INTEGER DEFAULT NULL,
  name VARCHAR(32) NOT NULL UNIQUE,
  sort INTEGER NOT NULL DEFAULT 0,
  status INTEGER DEFAULT NULL,
  create_time TIMESTAMP DEFAULT NULL,
  update_time TIMESTAMP DEFAULT NULL,
  create_user BIGINT DEFAULT NULL,
  update_user BIGINT DEFAULT NULL
);
COMMENT ON TABLE category IS '菜色及套餐分類';
COMMENT ON COLUMN category.type IS '類型 1 菜色分類 2 套餐分類';
COMMENT ON COLUMN category.name IS '分類名稱';
COMMENT ON COLUMN category.sort IS '排序';
COMMENT ON COLUMN category.status IS '分類狀態 0:禁用，1:啟用';

-- 插入分類資料 (已轉繁體)
INSERT INTO category (id, type, name, sort, status, create_time, update_time, create_user, update_user) VALUES
(11, 1, '酒水飲料', 10, 1, '2022-06-09 22:09:18', '2022-06-09 22:09:18', 1, 1),
(12, 1, '傳統主食', 9, 1, '2022-06-09 22:09:32', '2022-06-09 22:18:53', 1, 1),
(13, 2, '人氣套餐', 12, 1, '2022-06-09 22:11:38', '2022-06-10 11:04:40', 1, 1),
(15, 2, '商務套餐', 13, 1, '2022-06-09 22:14:10', '2022-06-10 11:04:48', 1, 1),
(16, 1, '蜀味烤魚', 4, 1, '2022-06-09 22:15:37', '2022-08-31 14:27:25', 1, 1),
(17, 1, '蜀味牛蛙', 5, 1, '2022-06-09 22:16:14', '2022-08-31 14:39:44', 1, 1),
(18, 1, '特色蒸菜', 6, 1, '2022-06-09 22:17:42', '2022-06-09 22:17:42', 1, 1),
(19, 1, '新鮮時蔬', 7, 1, '2022-06-09 22:18:12', '2022-06-09 22:18:28', 1, 1),
(20, 1, '水煮魚', 8, 1, '2022-06-09 22:22:29', '2022-06-09 22:23:45', 1, 1),
(21, 1, '湯類', 11, 1, '2022-06-10 10:51:47', '2022-06-10 10:51:47', 1, 1);

-- =============================================
-- 表結構：菜色 (dish)
-- =============================================
DROP TABLE IF EXISTS dish;
CREATE TABLE dish (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32) NOT NULL UNIQUE,
  category_id BIGINT NOT NULL,
  price DECIMAL(10,2) DEFAULT NULL,
  image VARCHAR(255) DEFAULT NULL,
  description VARCHAR(255) DEFAULT NULL,
  status INTEGER DEFAULT 1,
  create_time TIMESTAMP DEFAULT NULL,
  update_time TIMESTAMP DEFAULT NULL,
  create_user BIGINT DEFAULT NULL,
  update_user BIGINT DEFAULT NULL
);
COMMENT ON TABLE dish IS '菜色';
COMMENT ON COLUMN dish.name IS '菜色名稱';
COMMENT ON COLUMN dish.price IS '菜色價格';
COMMENT ON COLUMN dish.status IS '0 停售 1 開賣';

-- 插入菜色資料 (用語已在地化：西蘭花->花椰菜, 圓白菜->高麗菜)
INSERT INTO dish (id, name, category_id, price, image, description, status, create_time, update_time, create_user, update_user) VALUES
(46, '王老吉', 11, 6.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/41bfcacf-7ad4-4927-8b26-df366553a94c.png', '', 1, '2022-06-09 22:40:47', '2022-06-09 22:40:47', 1, 1),
(47, '北冰洋', 11, 4.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4451d4be-89a2-4939-9c69-3a87151cb979.png', '還是小時候的味道', 1, '2022-06-10 09:18:49', '2022-06-10 09:18:49', 1, 1),
(48, '雪花啤酒', 11, 4.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/bf8cbfc1-04d2-40e8-9826-061ee41ab87c.png', '', 1, '2022-06-10 09:22:54', '2022-06-10 09:22:54', 1, 1),
(49, '白飯', 12, 2.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/76752350-2121-44d2-b477-10791c23a8ec.png', '精選五常大米', 1, '2022-06-10 09:30:17', '2022-06-10 09:30:17', 1, 1),
(50, '饅頭', 12, 1.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/475cc599-8661-4899-8f9e-121dd8ef7d02.png', '優質麵粉', 1, '2022-06-10 09:34:28', '2022-06-10 09:34:28', 1, 1),
(51, '老罈酸菜魚', 20, 56.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4a9cefba-6a74-467e-9fde-6e687ea725d7.png', '原料：湯，草魚，酸菜', 1, '2022-06-10 09:40:51', '2022-06-10 09:40:51', 1, 1),
(52, '經典酸菜鮰魚', 20, 66.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/5260ff39-986c-4a97-8850-2ec8c7583efc.png', '原料：酸菜，江團，鮰魚', 1, '2022-06-10 09:46:02', '2022-06-10 09:46:02', 1, 1),
(53, '蜀味水煮草魚', 20, 38.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a6953d5a-4c18-4b30-9319-4926ee77261f.png', '原料：草魚，湯', 1, '2022-06-10 09:48:37', '2022-06-10 09:48:37', 1, 1),
(54, '清炒小油菜', 19, 18.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/3613d38e-5614-41c2-90ed-ff175bf50716.png', '原料：小油菜', 1, '2022-06-10 09:51:46', '2022-06-10 09:51:46', 1, 1),
(55, '蒜蓉娃娃菜', 19, 18.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/4879ed66-3860-4b28-ba14-306ac025fdec.png', '原料：蒜，娃娃菜', 1, '2022-06-10 09:53:37', '2022-06-10 09:53:37', 1, 1),
(56, '清炒花椰菜', 19, 18.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/e9ec4ba4-4b22-4fc8-9be0-4946e6aeb937.png', '原料：花椰菜', 1, '2022-06-10 09:55:44', '2022-06-10 09:55:44', 1, 1),
(57, '熗炒高麗菜', 19, 18.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/22f59feb-0d44-430e-a6cd-6a49f27453ca.png', '原料：高麗菜', 1, '2022-06-10 09:58:35', '2022-06-10 09:58:35', 1, 1),
(58, '清蒸鱸魚', 18, 98.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png', '原料：鱸魚', 1, '2022-06-10 10:12:28', '2022-06-10 10:12:28', 1, 1),
(59, '東坡肘子', 18, 138.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a80a4b8c-c93e-4f43-ac8a-856b0d5cc451.png', '原料：豬肘棒', 1, '2022-06-10 10:24:03', '2022-06-10 10:24:03', 1, 1),
(60, '梅菜扣肉', 18, 58.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/6080b118-e30a-4577-aab4-45042e3f88be.png', '原料：豬肉，梅菜', 1, '2022-06-10 10:26:03', '2022-06-10 10:26:03', 1, 1),
(61, '剁椒魚頭', 18, 66.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/13da832f-ef2c-484d-8370-5934a1045a06.png', '原料：鰱魚，剁椒', 1, '2022-06-10 10:28:54', '2022-06-10 10:28:54', 1, 1),
(62, '金湯酸菜牛蛙', 17, 88.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png', '原料：鮮活牛蛙，酸菜', 1, '2022-06-10 10:33:05', '2022-06-10 10:33:05', 1, 1),
(63, '香鍋牛蛙', 17, 88.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/f5ac8455-4793-450c-97ba-173795c34626.png', '配料：鮮活牛蛙，蓮藕，青筍', 1, '2022-06-10 10:35:40', '2022-06-10 10:35:40', 1, 1),
(64, '饞嘴牛蛙', 17, 88.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png', '配料：鮮活牛蛙，絲瓜，黃豆芽', 1, '2022-06-10 10:37:52', '2022-06-10 10:37:52', 1, 1),
(65, '草魚2斤', 16, 68.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/b544d3ba-a1ae-4d20-a860-81cb5dec9e03.png', '原料：草魚，黃豆芽，蓮藕', 1, '2022-06-10 10:41:08', '2022-06-10 10:41:08', 1, 1),
(66, '江團魚2斤', 16, 119.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/a101a1e9-8f8b-47b2-afa4-1abd47ea0a87.png', '配料：江團魚，黃豆芽，蓮藕', 1, '2022-06-10 10:42:42', '2022-06-10 10:42:42', 1, 1),
(67, '鮰魚2斤', 16, 72.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/8cfcc576-4b66-4a09-ac68-ad5b273c2590.png', '原料：鮰魚，黃豆芽，蓮藕', 1, '2022-06-10 10:43:56', '2022-06-10 10:43:56', 1, 1),
(68, '雞蛋湯', 21, 4.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/c09a0ee8-9d19-428d-81b9-746221824113.png', '配料：雞蛋，紫菜', 1, '2022-06-10 10:54:25', '2022-06-10 10:54:25', 1, 1),
(69, '平菇豆腐湯', 21, 6.00, 'https://sky-itcast.oss-cn-beijing.aliyuncs.com/16d0a3d6-2253-4cfc-9b49-bf7bd9eb2ad2.png', '配料：豆腐，平菇', 1, '2022-06-10 10:55:02', '2022-06-10 10:55:02', 1, 1);

-- =============================================
-- 表結構：菜色口味關係表 (dish_flavor)
-- =============================================
DROP TABLE IF EXISTS dish_flavor;
CREATE TABLE dish_flavor (
  id SERIAL PRIMARY KEY,
  dish_id BIGINT NOT NULL,
  name VARCHAR(32) DEFAULT NULL,
  value VARCHAR(255) DEFAULT NULL
);
COMMENT ON TABLE dish_flavor IS '菜色口味關係表';
COMMENT ON COLUMN dish_flavor.dish_id IS '菜色ID';
COMMENT ON COLUMN dish_flavor.name IS '口味名稱';
COMMENT ON COLUMN dish_flavor.value IS '口味資料list';

-- 插入口味資料 (內容已在地化，並轉為標準 JSON 格式)
INSERT INTO dish_flavor (id, dish_id, name, value) VALUES
(40, 10, '甜味', '["無糖","少糖","半糖","多糖","全糖"]'),
(41, 7, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(42, 7, '溫度', '["熱飲","常溫","去冰","少冰","多冰"]'),
(45, 6, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(46, 6, '辣度', '["不辣","微辣","中辣","重辣"]'),
(47, 5, '辣度', '["不辣","微辣","中辣","重辣"]'),
(48, 5, '甜味', '["無糖","少糖","半糖","多糖","全糖"]'),
(49, 2, '甜味', '["無糖","少糖","半糖","多糖","全糖"]'),
(50, 4, '甜味', '["無糖","少糖","半糖","多糖","全糖"]'),
(51, 3, '甜味', '["無糖","少糖","半糖","多糖","全糖"]'),
(52, 3, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(86, 52, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(87, 52, '辣度', '["不辣","微辣","中辣","重辣"]'),
(88, 51, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(89, 51, '辣度', '["不辣","微辣","中辣","重辣"]'),
(92, 53, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(93, 53, '辣度', '["不辣","微辣","中辣","重辣"]'),
(94, 54, '忌口', '["不要蔥","不要蒜","不要香菜"]'),
(95, 56, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(96, 57, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(97, 60, '忌口', '["不要蔥","不要蒜","不要香菜","不要辣"]'),
(101, 66, '辣度', '["不辣","微辣","中辣","重辣"]'),
(102, 67, '辣度', '["不辣","微辣","中辣","重辣"]'),
(103, 65, '辣度', '["不辣","微辣","中辣","重辣"]');

-- =============================================
-- 表結構：員工資訊 (employee)
-- =============================================
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  username VARCHAR(32) NOT NULL UNIQUE,
  password VARCHAR(64) NOT NULL,
  phone VARCHAR(11) NOT NULL,
  sex VARCHAR(2) NOT NULL,
  id_number VARCHAR(18) NOT NULL,
  status INTEGER NOT NULL DEFAULT 1,
  create_time TIMESTAMP DEFAULT NULL,
  update_time TIMESTAMP DEFAULT NULL,
  create_user BIGINT DEFAULT NULL,
  update_user BIGINT DEFAULT NULL
);
COMMENT ON TABLE employee IS '員工資訊';
COMMENT ON COLUMN employee.name IS '姓名';
COMMENT ON COLUMN employee.username IS '使用者名稱';
COMMENT ON COLUMN employee.password IS '密碼';
COMMENT ON COLUMN employee.phone IS '手機號碼';
COMMENT ON COLUMN employee.sex IS '性別';
COMMENT ON COLUMN employee.id_number IS '身分證字號';
COMMENT ON COLUMN employee.status IS '狀態 0:禁用，1:啟用';

-- 插入管理員帳號 (密碼通常為 MD5 後的值，這裡保留原始設定)
INSERT INTO employee (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) VALUES
(1, '管理員', 'admin', '123456', '13812312312', '1', '110101199001010047', 1, '2022-02-15 15:51:20', '2022-02-17 09:16:20', 10, 1);

-- =============================================
-- 表結構：訂單明細表 (order_detail)
-- =============================================
DROP TABLE IF EXISTS order_detail;
CREATE TABLE order_detail (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32) DEFAULT NULL,
  image VARCHAR(255) DEFAULT NULL,
  order_id BIGINT NOT NULL,
  dish_id BIGINT DEFAULT NULL,
  setmeal_id BIGINT DEFAULT NULL,
  dish_flavor VARCHAR(50) DEFAULT NULL,
  number INTEGER NOT NULL DEFAULT 1,
  amount DECIMAL(10,2) NOT NULL
);
COMMENT ON TABLE order_detail IS '訂單明細表';
COMMENT ON COLUMN order_detail.name IS '名稱';
COMMENT ON COLUMN order_detail.image IS '圖片';
COMMENT ON COLUMN order_detail.order_id IS '訂單ID';
COMMENT ON COLUMN order_detail.number IS '數量';
COMMENT ON COLUMN order_detail.amount IS '金額';

-- =============================================
-- 表結構：訂單表 (orders)
-- =============================================
DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  number VARCHAR(50) DEFAULT NULL,
  status INTEGER NOT NULL DEFAULT 1,
  user_id BIGINT NOT NULL,
  address_book_id BIGINT NOT NULL,
  order_time TIMESTAMP NOT NULL,
  checkout_time TIMESTAMP DEFAULT NULL,
  pay_method INTEGER NOT NULL DEFAULT 1,
  pay_status SMALLINT NOT NULL DEFAULT 0,
  amount DECIMAL(10,2) NOT NULL,
  remark VARCHAR(100) DEFAULT NULL,
  phone VARCHAR(11) DEFAULT NULL,
  address VARCHAR(255) DEFAULT NULL,
  user_name VARCHAR(32) DEFAULT NULL,
  consignee VARCHAR(32) DEFAULT NULL,
  cancel_reason VARCHAR(255) DEFAULT NULL,
  rejection_reason VARCHAR(255) DEFAULT NULL,
  cancel_time TIMESTAMP DEFAULT NULL,
  estimated_delivery_time TIMESTAMP DEFAULT NULL,
  delivery_status SMALLINT NOT NULL DEFAULT 1,
  delivery_time TIMESTAMP DEFAULT NULL,
  pack_amount INTEGER DEFAULT NULL,
  tableware_number INTEGER DEFAULT NULL,
  tableware_status SMALLINT NOT NULL DEFAULT 1
);
COMMENT ON TABLE orders IS '訂單表';
COMMENT ON COLUMN orders.number IS '訂單編號';
COMMENT ON COLUMN orders.status IS '訂單狀態 1待付款 2待接單 3已接單 4派送中 5已完成 6已取消 7退款';
COMMENT ON COLUMN orders.user_id IS '下單使用者';
COMMENT ON COLUMN orders.pay_method IS '支付方式 1微信/現金, 2支付寶/LinePay';
COMMENT ON COLUMN orders.pay_status IS '支付狀態 0未支付 1已支付 2退款';
COMMENT ON COLUMN orders.amount IS '實收金額';
COMMENT ON COLUMN orders.remark IS '備註';
COMMENT ON COLUMN orders.delivery_status IS '配送狀態 1立即送出 0選擇具體時間';
COMMENT ON COLUMN orders.tableware_status IS '餐具數量狀態 1按餐量提供 0選擇具體數量';

-- =============================================
-- 表結構：套餐 (setmeal)
-- =============================================
DROP TABLE IF EXISTS setmeal;
CREATE TABLE setmeal (
  id SERIAL PRIMARY KEY,
  category_id BIGINT NOT NULL,
  name VARCHAR(32) NOT NULL UNIQUE,
  price DECIMAL(10,2) NOT NULL,
  status INTEGER DEFAULT 1,
  description VARCHAR(255) DEFAULT NULL,
  image VARCHAR(255) DEFAULT NULL,
  create_time TIMESTAMP DEFAULT NULL,
  update_time TIMESTAMP DEFAULT NULL,
  create_user BIGINT DEFAULT NULL,
  update_user BIGINT DEFAULT NULL
);
COMMENT ON TABLE setmeal IS '套餐';
COMMENT ON COLUMN setmeal.name IS '套餐名稱';
COMMENT ON COLUMN setmeal.price IS '套餐價格';
COMMENT ON COLUMN setmeal.status IS '販售狀態 0:停售 1:開賣';

-- =============================================
-- 表結構：套餐菜色關係 (setmeal_dish)
-- =============================================
DROP TABLE IF EXISTS setmeal_dish;
CREATE TABLE setmeal_dish (
  id SERIAL PRIMARY KEY,
  setmeal_id BIGINT DEFAULT NULL,
  dish_id BIGINT DEFAULT NULL,
  name VARCHAR(32) DEFAULT NULL,
  price DECIMAL(10,2) DEFAULT NULL,
  copies INTEGER DEFAULT NULL
);
COMMENT ON TABLE setmeal_dish IS '套餐菜色關係';
COMMENT ON COLUMN setmeal_dish.name IS '菜色名稱 (冗餘欄位)';
COMMENT ON COLUMN setmeal_dish.price IS '菜色單價 (冗餘欄位)';
COMMENT ON COLUMN setmeal_dish.copies IS '菜色份數';

-- =============================================
-- 表結構：購物車 (shopping_cart)
-- =============================================
DROP TABLE IF EXISTS shopping_cart;
CREATE TABLE shopping_cart (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32) DEFAULT NULL,
  image VARCHAR(255) DEFAULT NULL,
  user_id BIGINT NOT NULL,
  dish_id BIGINT DEFAULT NULL,
  setmeal_id BIGINT DEFAULT NULL,
  dish_flavor VARCHAR(50) DEFAULT NULL,
  number INTEGER NOT NULL DEFAULT 1,
  amount DECIMAL(10,2) NOT NULL,
  create_time TIMESTAMP DEFAULT NULL
);
COMMENT ON TABLE shopping_cart IS '購物車';
COMMENT ON COLUMN shopping_cart.name IS '商品名稱';
COMMENT ON COLUMN shopping_cart.dish_flavor IS '口味';
COMMENT ON COLUMN shopping_cart.number IS '數量';
COMMENT ON COLUMN shopping_cart.amount IS '金額';

-- =============================================
-- 表結構：使用者資訊 (user)
-- =============================================
DROP TABLE IF EXISTS "user"; -- PostgreSQL 中 user 是保留字，建議加雙引號
CREATE TABLE "user" (
  id SERIAL PRIMARY KEY,
  openid VARCHAR(45) DEFAULT NULL,
  name VARCHAR(32) DEFAULT NULL,
  phone VARCHAR(11) DEFAULT NULL,
  sex VARCHAR(2) DEFAULT NULL,
  id_number VARCHAR(18) DEFAULT NULL,
  avatar VARCHAR(500) DEFAULT NULL,
  create_time TIMESTAMP DEFAULT NULL
);
COMMENT ON TABLE "user" IS '使用者資訊';
COMMENT ON COLUMN "user".openid IS '微信/Line使用者唯一標識';
COMMENT ON COLUMN "user".name IS '姓名';
COMMENT ON COLUMN "user".phone IS '手機號碼';
COMMENT ON COLUMN "user".sex IS '性別';
COMMENT ON COLUMN "user".id_number IS '身分證字號';
COMMENT ON COLUMN "user".avatar IS '頭像';


-- =============================================
-- 序列校正 (Sequence Reset)
-- =============================================
-- 因為手動插入了指定 ID 的資料，必須將序列重置到目前最大 ID，
-- 否則後續透過程式新增資料時會報錯 (Duplicate key error)。

SELECT setval(pg_get_serial_sequence('address_book', 'id'), COALESCE(MAX(id), 1)) FROM address_book;
SELECT setval(pg_get_serial_sequence('category', 'id'), COALESCE(MAX(id), 1)) FROM category;
SELECT setval(pg_get_serial_sequence('dish', 'id'), COALESCE(MAX(id), 1)) FROM dish;
SELECT setval(pg_get_serial_sequence('dish_flavor', 'id'), COALESCE(MAX(id), 1)) FROM dish_flavor;
SELECT setval(pg_get_serial_sequence('employee', 'id'), COALESCE(MAX(id), 1)) FROM employee;
SELECT setval(pg_get_serial_sequence('order_detail', 'id'), COALESCE(MAX(id), 1)) FROM order_detail;
SELECT setval(pg_get_serial_sequence('orders', 'id'), COALESCE(MAX(id), 1)) FROM orders;
SELECT setval(pg_get_serial_sequence('setmeal', 'id'), COALESCE(MAX(id), 1)) FROM setmeal;
SELECT setval(pg_get_serial_sequence('setmeal_dish', 'id'), COALESCE(MAX(id), 1)) FROM setmeal_dish;
SELECT setval(pg_get_serial_sequence('shopping_cart', 'id'), COALESCE(MAX(id), 1)) FROM shopping_cart;
SELECT setval(pg_get_serial_sequence('"user"', 'id'), COALESCE(MAX(id), 1)) FROM "user";