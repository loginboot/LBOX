package cn.ryan.robot.config;

import java.io.FileInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;

import cn.ryan.robot.RobotLogin;

public class RobotConfig {

    /**
     * Config目录
     */
    public final static String CFG_PATH = "./config";

    /**
     * LOG4J2配置文件路径
     */
    public final static String LOG4J2_XML = CFG_PATH + "/log4j2.xml";

    private static RobotConfig rcfg = null;

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RobotLogin.class);

    /**
     * 內部構造函數
     */
    private RobotConfig() {
        try {
            ConfigurationSource source = new ConfigurationSource(new FileInputStream(LOG4J2_XML));
            LoggerContext context = Configurator.initialize(null, source);
            XmlConfiguration xmlConfig = new XmlConfiguration(context, source);
            context.start(xmlConfig);
        } catch (Exception e) {
            log.error("Load log4j2 xml file:[" + LOG4J2_XML + "] error:", e);
        }
    }

    /**
     * 單例實現方法
     * @return
     */
    public synchronized static RobotConfig getInstance() {
        if (rcfg == null) {
            rcfg = new RobotConfig();
        }
        return rcfg;
    }
}
