package cn.ryan.exception;

import cn.ryan.utils.RyanUtil;

/**
 * 
 * @author ryan.cn
 * 
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-10
 * @description 系统自定异常处理类 - 创建
 *
 */
public class RyanException extends Exception {

    /**
     * serial version UID
     */
    private static final long serialVersionUID = 1L;

    public RyanException(String errcode) {
        super(errcode);
    }

    public RyanException(int ierrcode) {
        super("ERRCODE." + RyanUtil.leftFillZero("" + ierrcode, 4));
    }

}
