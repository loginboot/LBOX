package cn.ryan.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author ryan.cn
 * @creator ryan
 * @version 1.0.0
 * @description Ryan主要工具实现类
 *
 */
public class RyanUtil {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RyanUtil.class);

    public static void main(String[] args) {
        String val = "   ";
        boolean bs = isEmpty(val);
        System.out.println("test value isempty:" + bs);
        log.info("is ovk..");
    }

    /**
     *  判断内容是否为空
     * 
     * @param val
     * @return true
     */
    public static boolean isEmpty(String val) {
        if (val == null || "".equals(trim(val))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 去除两边空格
     * 
     * @param val
     * @return
     */
    public static String trim(String val) {
        if (val == null) {
            return "";
        } else {
            val = StringUtils.replace(val, "　", " ");
            return val.trim();
        }
    }

}
