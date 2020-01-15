package cn.ryan.model;

import java.io.Serializable;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统用户与角色关联表实体类
 *
 */
public class SysUserRole extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int urId;
    private SysUser user;
    private SysRole role;

    public int getUrId() {
        return urId;
    }

    public void setUrId(int urId) {
        this.urId = urId;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

}
