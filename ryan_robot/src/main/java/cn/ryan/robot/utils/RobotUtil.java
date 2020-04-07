package cn.ryan.robot.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.ryan.robot.Robot;
import javafx.application.Platform;
import javafx.stage.Stage;

public class RobotUtil {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RobotUtil.class);

    public static <T> boolean showWindow(Class<T> ctype) {
        boolean isOk = false;
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    Stage stg = Robot.stgMap.get(ctype.getSimpleName());
                    if (stg != null) {
                        stg.show();
                        log.info("Show " + ctype.getName() + " window for successful.");
                    }
                } catch (Exception e) {
                    log.error("Show " + ctype.getName() + " window for error:", e);
                }
            }
        });
        return isOk;
    }

    public static <T> boolean hideWindow(Class<T> ctype) {
        boolean isOk = false;
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    Stage stg = Robot.stgMap.get(ctype.getSimpleName());
                    if (stg != null) {
                        stg.hide();
                        log.info("Hide " + ctype.getName() + " window for successful.");
                    }
                } catch (Exception e) {
                    log.error("Show " + ctype.getName() + " window for error:", e);
                }
            }
        });
        return isOk;
    }
}
