# 🥡 Omochikaeri (お持ち帰り) - 外送訂餐系統後端

![Java](https://img.shields.io/badge/Java-11-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.3-green) ![MyBatis](https://img.shields.io/badge/MyBatis-Spring-red) ![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue) ![AWS S3](https://img.shields.io/badge/Cloud-AWS%20S3-yellow)

## 📖 專案簡介 (Introduction)
Omochikaeri 是一個基於 **Spring Boot** 與 **MyBatis** 構建的後端 RESTful API 系統，旨在模擬真實餐飲外送平台的業務邏輯。
本專案不僅包含基礎的 CRUD，更著重於**企業級開發規範**的實踐，包括 AOP 切面日誌、ThreadLocal 上下文管理、Redis 快取應用以及 WebSocket 即時訊息推播。

## 🛠 技術棧 (Tech Stack)

### 核心框架
* **開發語言:** Java 11
* **Web 框架:** Spring Boot 2.7.3
* **持久層:** MyBatis + PageHelper (分頁插件)
* **資料庫:** PostgreSQL 16
* **連線池:** Druid / HikariCP

### 進階技術應用
* **快取機制:** Redis (Spring Data Redis) - 用於快取店鋪營業狀態與熱點資料
* **即時通訊:** WebSocket - 實作商家端「來單語音提醒」與「客戶催單」
* **雲端儲存:** AWS S3 (Amazon Simple Storage Service) - 處理菜品圖片上傳
* **API 文件:** Knife4j (Swagger 增強版)
* **工具庫:** Lombok, Fastjson, Apache HttpClient, Apache POI (報表匯出)

## 🌟 核心亮點 (Key Features & Implementation)

### 1. AOP 自動填充欄位 (Aspect Oriented Programming)
為了解決在每個 Controller/Service 手動設定 `createTime`, `updateTime`, `createUser` 的重複性代碼，專案實作了 **自定義註解 `@AutoFill`**。
* **實作原理:** 定義切面 (`AutoFillAspect`) 攔截 Mapper 層的新增/修改方法，利用 **Java Reflection** 動態賦值。
* **效益:** 大幅提升代碼可維護性與簡潔度。

### 2. ThreadLocal 上下文管理
在無狀態的 JWT 驗證機制下，為了讓 Service 層能隨時獲取當前登入使用者的 ID，實作了基於 `ThreadLocal` 的 `BaseContext` 工具類。
* **流程:** 攔截器 (`JwtTokenUserInterceptor`) 解析 Token -> 存入 ThreadLocal -> Service 層直接取用 -> 請求結束後清除。

### 3. WebSocket 即時推播
模擬真實餐廳場景，當用戶下單 (`OrderServiceImpl.submitOrder`) 成功後，後端透過 WebSocket 向商家管理後台推播 JSON 訊息，觸發前端的語音提醒與彈窗。

### 4. 複雜訂單邏輯處理
* **狀態流轉:** 嚴謹處理 `待付款` -> `待接單` -> `製作中` -> `派送中` -> `已完成` 的狀態機邏輯。
* **交易一致性:** 使用 `@Transactional` 確保「訂單表插入」與「訂單明細表插入」的原子性，防止資料不一致。

## 📂 專案結構 (Module Structure)
本專案採用 Maven 多模組架構設計：

* `omochikaeri-common`: 通用模組 (自定義異常、工具類、常數、Json 處理器)
* `omochikaeri-pojo`: 資料模型 (Entity, DTO, VO)
* `omochikaeri-server`: 核心業務模組 (Controller, Service, Mapper, Config)

## 🚀 快速開始 (Quick Start)

### 環境需求
* **JDK:** 11+
* **Build Tool:** Maven 3.x
* **Infrastructure:** Docker (用於快速部署 PostgreSQL 與 Redis)

### 開發環境建置 (Development Setup)
本專案建議使用 Docker 快速啟動所需的資料庫與快取服務，避免繁瑣的環境安裝。

1. **啟動基礎設施 (Infrastructure):**
   使用 Docker 執行 PostgreSQL 與 Redis：
   ```bash
   # 啟動 PostgreSQL (預設帳密: postgres/123456)
   docker run --name omochi-pg -e POSTGRES_PASSWORD=123456 -p 5432:5432 -d postgres:16

   # 啟動 Redis
   docker run --name omochi-redis -p 6379:6379 -d redis
   ```
2. **資料庫初始化:**
   本專案已在 Docker Compose 配置中掛載 `sql/init.sql`。
   首次啟動 `docker-compose up -d` 時，PostgreSQL 容器會自動執行該腳本，完成資料表建置、預設資料匯入。