package cn.ryan.model;

import java.io.Serializable;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统角色与菜单关联表实体类
 *
 */
public class SysRoleMenu extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int rmId;
    private SysRole role;
    private SysMenu menu;

    public int getRmId() {
        return rmId;
    }

    public void setRmId(int rmId) {
        this.rmId = rmId;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public SysMenu getMenu() {
        return menu;
    }

    public void setMenu(SysMenu menu) {
        this.menu = menu;
    }

}
