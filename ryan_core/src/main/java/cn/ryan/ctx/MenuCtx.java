package cn.ryan.ctx;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import cn.ryan.model.SysMenu;

/**
 * 
 * @author lyodssoft.com
 * 
 * @creator Kevin
 * @version 1.0.0
 * @date 2013-12-11
 * @descrption Menu Context - Create
 *
 */
public class MenuCtx implements Serializable {
    private static final long serialVersionUID = 1L;
    private SysMenu menu;
    private int depth = 0;// 菜单层级
    private MenuCtx parent;
    private List<MenuCtx> children = new LinkedList<MenuCtx>();

    public MenuCtx(SysMenu menu) {
        setMenu(menu);
    }

    /**
     * 获取父节点
     * 
     * @return MenuCtx
     */
    public MenuCtx getParent() {
        return parent;
    }

    /**
     * 设置父节点
     * 
     * @param parent
     */
    public void setParent(MenuCtx parent) {
        this.parent = parent;
    }

    /**
     * 是否根节点
     * 
     * @return true.是 false.否
     */
    public boolean isRoot() {
        return parent == null ? true : false;
    }

    /**
     * 是否存在子Menu
     * 
     * @return true.是 false.否
     */
    public boolean hasChildren() {
        return children.size() > 0 ? true : false;
    }

    /**
     * 设置主Menu
     * 
     * @param menu
     */
    public void setMenu(SysMenu menu) {
        this.menu = menu;
        this.depth = menu.getDepth();
    }

    /**
     * 获取主Menu
     * 
     * @return Menu
     */
    public SysMenu getMenu() {
        return this.menu;
    }

    /**
     * 获 取主菜单层级
     * 
     * @return int
     */
    public int getDepth() {
        return this.depth;
    }

    /**
     * 增加子MenuCtx
     * 
     * @param child 子MenuCtx
     */
    public void addChildMenuCtx(MenuCtx child) {
        children.add(child);
    }

    /**
     * 获取所有子菜单MenuCtx
     * 
     * @return List<Menu>
     */
    public List<MenuCtx> getChildren() {
        return children;
    }
}
