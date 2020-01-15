package cn.ryan.constant;

/**
 * 
 * @author ryan.cn
 * @creator ryan
 * @version 1.0.0
 * @description Ryan静态常量
 *
 */
public class RConstant {

    //默认系统管理员ID
    public static final int DEFAULT_ADMIN_ID = 1;
    // 默认语言
    public static final String DEFAUL_LANG = "zh_CN";
    // 默认分页笔数
    public static final int DEFAULT_PAGE_SIZE = 10;

    // 输入错误密码次数超过最大允许次数
    public static final Integer USER_PWD_LOCK = 1;
    // 密码超过有效期
    public static final Integer USER_PWD_HAS_EXPIRED = 2;
    // 账户超过登录最小到期天数
    public static final Integer USER_ACCOUNT_EXPIRE = 3;

    // HASH编码类型
    public static final String HASH_ALGORITHM = "SHA-256";
    public static final int HASH_INTERATIONS = 256;

}
