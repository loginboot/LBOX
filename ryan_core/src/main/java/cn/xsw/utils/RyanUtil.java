package cn.xsw.utils;

public class RyanUtil {

	public static void main(String[] args) {
		String val = "   ";
		boolean bs = isEmpty(val);
		System.out.println("test value isempty:" + bs);
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
