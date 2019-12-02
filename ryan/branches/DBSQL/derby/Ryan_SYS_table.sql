-- Ryan for MySQL
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
-- 系统用户表
CREATE TABLE T_SYS_USER
(
  USERID                 INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
  NAME                   VARCHAR(200) NOT NULL,
  LOGIN_NAME             VARCHAR(50) NOT NULL,
  SALT                   VARCHAR(128),
  PASSWORD               VARCHAR(200),
  INT_PWD                INTEGER NOT NULL,
  FAIL_TIMES             INTEGER NOT NULL,
  STATUS                 INTEGER NOT NULL,
  HISTORY_PWD            VARCHAR(2000),
  LOCK_REASON            INTEGER,
  PWD_LAST_MODIFY_DATE   VARCHAR(26),
  LAST_LOGIN_DATE        VARCHAR(26),
  CREATOR_ID             INTEGER NOT NULL,
  CREATE_DATE            VARCHAR(26),
  MODIFIER_ID            INTEGER NOT NULL,
  LAST_MODIFY_DATE       VARCHAR(26) NOT NULL
);

CREATE INDEX SYS_USER_IDX1 ON T_SYS_USER(NAME);
CREATE INDEX SYS_USER_IDX2 ON T_SYS_USER(LOGIN_NAME);

-- 系统用户扩展表
CREATE TABLE T_SYS_USER_EXT
(
  USERID                 INTEGER NOT NULL,
  LABEL_NAME             VARCHAR(50) NOT NULL,
  FIELD_VALUE            VARCHAR(500)
);
CREATE INDEX SYS_USER_EXT_IDX1 ON T_SYS_USER_EXT(USERID);
CREATE INDEX SYS_USER_EXT_IDX2 ON T_SYS_USER_EXT(LABEL_NAME);


-- 系统菜单表
CREATE TABLE T_SYS_MENU
(
  MID                    INTEGER NOT NULL PRIMARY KEY,
  SYSCODE                VARCHAR(50) NOT NULL,
  NAME                   VARCHAR(100) NOT NULL,
  TYPE                   INTEGER NOT NULL,
  PID                    INTEGER NOT NULL,
  URI                    VARCHAR(200) NOT NULL,
  PERMISSION             VARCHAR(100) NOT NULL,
  DEPTH                  INTEGER NOT NULL,
  SEQ                    INTEGER NOT NULL
);
CREATE INDEX SYS_MENU_IDX1 ON T_SYS_MENU(SYSCODE);
CREATE INDEX SYS_MENU_IDX2 ON T_SYS_MENU(NAME);
