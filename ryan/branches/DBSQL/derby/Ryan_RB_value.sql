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

-- 機器參數
INSERT INTO T_RB_ROBOT(MODE,LABEL,SKEY) VALUES('COLOR_BOLL','robot.CB','R1');
INSERT INTO T_RB_ROBOT(MODE,LABEL,SKEY) VALUES('FILE_INDEX','robot.FI','R2');
INSERT INTO T_RB_ROBOT(MODE,LABEL,SKEY) VALUES('WEB_CRAWLER','robot.WC','R3');

-- 文件类型
INSERT INTO T_RB_FILE_TYPE(NAME,PATTERN) VALUES('robot.fileMV','avi|mp4|mkv|wmv|rmvb');
INSERT INTO T_RB_FILE_TYPE(NAME,PATTERN) VALUES('robot.filePic','jpg|jpeg|bmp|gif|png');
INSERT INTO T_RB_FILE_TYPE(NAME,PATTERN) VALUES('robot.fileBT','bt|torrent');

