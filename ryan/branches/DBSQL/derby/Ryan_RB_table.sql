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
  PKEY                   VARCHAR(64),
  PATH                   VARCHAR(300),
  LEVEL                  INTEGER NOT NULL DEFAULT 0,
  CREATE_DATE            VARCHAR(23)
);
CREATE INDEX RB_FILE_PATH_IDX1 ON T_RB_FILE_PATH(PKEY);
CREATE INDEX RB_FILE_PATH_IDX2 ON T_RB_FILE_PATH(PATH);


-- 文件索引名称表
CREATE TABLE T_RB_FILE_INDEX
(
  ID                     INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  PKEY                   VARCHAR(64),
  FILE_TYPE              INTEGER NOT NULL,
  FILE_NAME              VARCHAR(300),
  FILE_INDEX             VARCHAR(100),
  FILE_UPDATE_DATE       VARCHAR(23),
  FILE_SIZE              BIGINT,
  READ_TIMES             INT
);
CREATE INDEX RB_FILE_INDEX_IDX1 ON T_RB_FILE_INDEX(PKEY);
CREATE INDEX RB_FILE_INDEX_IDX2 ON T_RB_FILE_INDEX(FILE_INDEX);
