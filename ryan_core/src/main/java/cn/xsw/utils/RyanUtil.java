package cn.xsw.utils;

public class RyanUtil {

	public static void main(String[] args) {
		String val = "   ";
		boolean bs = isEmpty(val);
		System.out.println("test value isempty:" + bs);
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
