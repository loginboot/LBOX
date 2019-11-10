package cn.xsw.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RyanUtil {
	
	/**
	 * ��־����
	 */
	private static Logger log = LogManager.getLogger(RyanUtil.class);

	public static void main(String[] args) {
		String val = "   ";
		boolean bs = isEmpty(val);
		System.out.println("test value isempty:" + bs);
		log.info("is ovk..");
	}

	/**
	 * �ж��Ƿ�Ϊ""��null
	 * 
	 * @param val
	 * @return true.Ϊ�ո��null false.������
	 */
	public static boolean isEmpty(String val) {
		if (val == null || "".equals(trim(val))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ȥ�����߿ո���ǿȫ�ǿո���
	 * 
	 * @param val
	 * @return
	 */
	public static String trim(String val) {
		if (val == null) {
			return "";
		} else {
			val = val.replace("��", " ");
			return val.trim();
		}
	}

}
