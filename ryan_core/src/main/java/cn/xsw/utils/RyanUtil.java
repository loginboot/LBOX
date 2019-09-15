package cn.xsw.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RyanUtil {
	
	/**
	 * 日志对象
	 */
	private static Logger log = LogManager.getLogger(RyanUtil.class);

	public static void main(String[] args) {
		String val = "   ";
		boolean bs = isEmpty(val);
		System.out.println("test value isempty:" + bs);
		log.info("is ovk..");
	}

	/**
	 * 判断是否为""或null
	 * 
	 * @param val
	 * @return true.为空格或null false.有内容
	 */
	public static boolean isEmpty(String val) {
		if (val == null || "".equals(trim(val))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 去除两边空格，增强全角空格处理
	 * 
	 * @param val
	 * @return
	 */
	public static String trim(String val) {
		if (val == null) {
			return "";
		} else {
			val = val.replace("　", " ");
			return val.trim();
		}
	}

}
