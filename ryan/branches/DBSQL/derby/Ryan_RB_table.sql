-- Ryan for Derby
-- -------------------------------------------------------------------
--  ______  __   __     __       _    _ _                           --
-- |  __  | \ \ / /   / /\ \    | |  / / |                          --
-- | |__|_|  \ / /   / /__\ \   | | / /| |                          --
-- | | \ \    / /   / /____\ \  | |/ / | |                          --
-- |_|  \_\  /_/   /_/      \_\ |_/_/  |_                           --
--                                                                  --
--                                                                  --
--  COMMON, Table Creation                                          --
--  Version 1.0.0.0                                                 --
--                                                                  --
--  Derby 5.8                                                       --
--                                                                  --
--  rev 1.0.0                                                       --
-- -------------------------------------------------------------------

-- 创建表
-- 機器人基本信息表
CREATE TABLE T_RB_ROBOT
(
  MODE                   VARCHAR(50) NOT NULL PRIMARY KEY,
  LABEL                  VARCHAR(100) NOT NULL,
  SKEY                   VARCHAR(200)
);

-- 機器模型表
CREATE TABLE T_RB_ROBOT_MODE
(
  RBID                   INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  MODE                   VARCHAR(50) NOT NULL,
  MDESC                  VARCHAR(500),
  STATUS                 INTEGER NOT NULL,
  CREATOR_ID             INTEGER NOT NULL,
  CREATE_DATE            VARCHAR(26),
  MODIFIER_ID            INTEGER NOT NULL,
  LAST_MODIFY_DATE       VARCHAR(26) NOT NULL
);
CREATE INDEX RB_ROBOT_MODE_IDX1 ON T_RB_ROBOT_MODE(MODE);

-- 機器模型參數表
CREATE TABLE T_RB_ROBOT_PARAMS
(
  RPID                   INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  RMID                   INTEGER NOT NULL,
  RP_NAME                VARCHAR(50) NOT NULL,
  RP_VALUE               VARCHAR(2000),
  RP_DESC                VARCHAR(2000)
);

-- 彩票表
CREATE TABLE T_RB_COLOR_BALL
(
  ID                     INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  RMID                   INTEGER NOT NULL,
  BDESC                  VARCHAR(500),
  RBALL                  VARCHAR(2000) NOT NULL,
  STATUS                 INT NOT NULL,
  PRIZE_STATUS           INT NOT NULL,
  PRIZE_DATE             VARCHAR(10),
  RMK1                   VARCHAR(500),
  RMK2                   VARCHAR(500),
  RMK3                   VARCHAR(500),
  CREATOR_ID             INTEGER NOT NULL,
  CREATE_DATE            VARCHAR(26),
  MODIFIER_ID            INTEGER NOT NULL,
  LAST_MODIFY_DATE       VARCHAR(26) NOT NULL
);
CREATE INDEX RB_COLOR_BALL_IDX1 ON T_RB_COLOR_BALL(PRIZE_DATE);

-- 彩票歷史數據表
CREATE TABLE T_RB_COLOR_HIST
(
  ID                     INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  RMID                   INTEGER NOT NULL,
  ISSUE_NUM              VARCHAR(32) NOT NULL,
  RBALL                  VARCHAR(2000) NOT NULL,
  TOTAL_AML              DECIMAL(18,2) NOT NULL,
  PRIZE_DATE             VARCHAR(10) NOT NULL,
  RMK1                   VARCHAR(500),
  RMK2                   VARCHAR(500),
  RMK3                   VARCHAR(500),
  CREATE_DATE            VARCHAR(26)
);

CREATE INDEX RB_COLOR_HIST_IDX1 ON T_RB_COLOR_HIST(ISSUE_NUM);
CREATE INDEX RB_COLOR_HIST_IDX2 ON T_RB_COLOR_HIST(PRIZE_DATE);

-- 文件类型表
CREATE TABLE T_RB_FILE_TYPE
(
  ID                     INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  NAME                   VARCHAR(200) NOT NULL,
  PATTERN                VARCHAR(500)
);

CREATE INDEX RB_FILE_TYPE_IDX1 ON T_RB_FILE_TYPE(NAME);


-- 文件目录结构树
CREATE TABLE T_RB_FILE_PATH
(
  FKEY                   VARCHAR(64) NOT NULL PRIMARY KEY,
  RMID                   INTEGER NOT NULL,
  PKEY                   VARCHAR(64),
  PATH                   VARCHAR(300),
  LEVEL                  INTEGER NOT NULL DEFAULT 0,
  CREATOR_ID             INTEGER NOT NULL,
  CREATE_DATE            VARCHAR(26),
  MODIFIER_ID            INTEGER NOT NULL,
  LAST_MODIFY_DATE       VARCHAR(26) NOT NULL
);
CREATE INDEX RB_FILE_PATH_IDX1 ON T_RB_FILE_PATH(PKEY);
CREATE INDEX RB_FILE_PATH_IDX2 ON T_RB_FILE_PATH(PATH);


-- 文件索引名称表
CREATE TABLE T_RB_FILE_INDEX
(
  ID                     INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  FKEY                   VARCHAR(64),
  FILE_TYPE              INTEGER NOT NULL,
  FILE_NAME              VARCHAR(300),
  FILE_INDEX             VARCHAR(100),
  FILE_UPDATE_DATE       VARCHAR(26),
  FILE_SIZE              BIGINT,
  READ_TIMES             INTEGER
);
CREATE INDEX RB_FILE_INDEX_IDX1 ON T_RB_FILE_INDEX(PKEY);
CREATE INDEX RB_FILE_INDEX_IDX2 ON T_RB_FILE_INDEX(FILE_INDEX);


-- 爬蟲存儲目錄表
CREATE TABLE T_RB_WEB_CRAWLER
(
  FKEY                   VARCHAR(64) NOT NULL PRIMARY KEY,
  RMID                   INTEGER NOT NULL,
  PKEY                   VARCHAR(64),
  PATH                   VARCHAR(300),
  LEVEL                  INTEGER NOT NULL DEFAULT 0,
  CREATOR_ID             INTEGER NOT NULL,
  CREATE_DATE            VARCHAR(26),
  MODIFIER_ID            INTEGER NOT NULL,
  LAST_MODIFY_DATE       VARCHAR(26) NOT NULL
);
CREATE INDEX RB_WEB_CRAWLER_IDX1 ON T_RB_WEB_CRAWLER(PKEY);
CREATE INDEX RB_WEB_CRAWLER_IDX2 ON T_RB_WEB_CRAWLER(PATH);

-- 爬蟲存儲文件信息表
CREATE TABLE T_RB_WEB_DATA
(
  ID                     INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  FKEY                   VARCHAR(64),
  FILE_TYPE              INTEGER NOT NULL,
  FILE_NAME              VARCHAR(300),
  FILE_REAL_NAME         VARCHAR(500),
  FILE_INDEX             VARCHAR(100),
  FILE_UPDATE_DATE       VARCHAR(26),
  FILE_SIZE              BIGINT,
  READ_TIMES             INTEGER
);
CREATE INDEX RB_WEB_DATA_IDX1 ON T_RB_WEB_DATA(PKEY);
CREATE INDEX RB_WEB_DATA_IDX2 ON T_RB_WEB_DATA(FILE_INDEX);

