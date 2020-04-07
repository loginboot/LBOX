package cn.ryan.model;

import java.io.Serializable;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统用戶與來源關聯表实体类
 *
 */
public class SysUserMode extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;
    private int umId;
    private SysUser user;
    private SysMode mode;

    public int getUmId() {
        return umId;
    }

    public void setUmId(int umId) {
        this.umId = umId;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysMode getMode() {
        return mode;
    }

    public void setMode(SysMode mode) {
        this.mode = mode;
    }

}
