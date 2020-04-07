package cn.ryan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    /**
     * 函数功能：如果源字符串的长度(不包括符号)小于指定长度，则在前面补零，使之达到指定长度
     * 
     * @param accno 输入字符串
     * @param strLen 需要输出字符串长度，不包括符号，
     * @return 补零后的字符串
     *         <p>
     *         <blockquote>
     * 
     *         <pre>
     * 例如：
     *      leftFillZero(&quot;-1&quot;,3) 输出为-001，
     *      leftFillZero(&quot;1&quot;,3) 输出为001
     *      leftFillZero(&quot;&quot;,4)  输出0000
     * leftFillZero(null,4) 输出null
     * 
     *         </p>
     *         </blockquote></pre>
     */
    public static String leftFillZero(String accno, int strLen) {
        if (accno == null) {
            return null;
        }
        int tempLen = accno.length();
        StringBuffer retVal = new StringBuffer(accno);
        if (tempLen == 0) {
            for (int i = 0; i < strLen; i++) {
                retVal.insert(0, "0");
            }
            return retVal.toString();
        }
        if (accno.charAt(0) == '-') {
            if (tempLen > strLen) {
                return accno;
            }
            for (int i = 0; i <= (strLen - tempLen); i++) {
                retVal.insert(1, "0");
            }
        } else {
            if (tempLen >= strLen) {
                return accno;
            }
            for (int i = 0; i < (strLen - tempLen); i++) {
                retVal.insert(0, "0");
            }
        }
        return retVal.toString();
    }

    /**
     * 字符串转换为时间戳
     * 
     * @param times YYYY-MM-DD HH:mm:SS.ssssss
     * @return Date
     * @throws ParseException
     */
    public static Date strToTimeStamp(String times) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        return sdf.parse(times);
    }

    /**
     * 日期转换为字符串
     * 
     * @param time Date
     * @return yyyy-MM-dd
     */
    public static String dateToStr(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    /**
     * 日期时间转换为字符串
     * 
     * @param time Date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String timeToStr(Date time) {
        return dateToStr(time, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 时间日期转换
     * 
     * @param datetime
     * @param pattern 默认"yyyy-MM-dd HH:mm"
     * @return
     */
    public static Date strToDateTime(String datetime, String pattern) throws ParseException {
        if (isEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(datetime);
    }

    /**
     * 将日期转换为指定格式的字符串
     * 
     * @param date
     * @param pattern 默认为 "yyyy-MM-dd HH:mm"
     * @return String
     */
    public static String dateToStr(Date date, String pattern) {
        if (isEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
        return sdf.format(date);
    }

    /**
     * 字符串转换为日期
     * 
     * @param times YYYY-MM-DD
     * @return Date
     * @throws ParseException
     */
    public static Date strToDate(String times) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (isEmpty(times)) {
            return sdf.parse("9999-12-31");
        } else {
            return sdf.parse(times);
        }
    }

    /**
     * 时间戳转换为字符串
     * 
     * @param time Date
     * @return yyyy-MM-dd HH:mm:ss.SSSSSS
     */
    public static String timeStampToStr(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        return sdf.format(time);
    }

    /**
     * 判斷是否數據字
     * @param val
     * @return
     */
    public static boolean isNumber(String val) {
        return StringUtils.isNumeric(val);
    }
}
