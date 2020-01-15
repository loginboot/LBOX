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
--  MySQL 5.8                                                       --
--                                                                  --
--  rev 1.0.0                                                       --
-- -------------------------------------------------------------------

-- INSERT SYS USER
INSERT INTO T_SYS_USER(USERID,NAME,LOGIN_NAME,INIT_PWD,FAIL_TIMES,STATUS,SALT,PASSWORD,CREATOR_ID,MODIFIER_ID,CREATE_DATE,LAST_MODIFY_DATE)
VALUES(1,'administrator','admin',0,0,0,'247b29c80c634031','5496d0d23e0d4900af586cd8edfdea67c9ff3b2feffbe42d6bc4df00527bb24f',1,1,'2020-01-01 12:00:00.000000','2020-06-30 12:00:00.000000');

-- INSERT SYS ROLE
INSERT INTO T_SYS_ROLE(RID,NAME,RDESC,CREATOR_ID,MODIFIER_ID,CREATE_DATE,LAST_MODIFY_DATE)
VALUES(1,'admin','admin role',1,1,'2020-01-01 12:00:00.000000','2020-06-30 12:00:00.000000');

-- INSERT SYS PARAMETERS

-- INSERT SYS MENU

