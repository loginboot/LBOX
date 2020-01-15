package cn.ryan.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统角色表实体类
 *
 */
public class SysRole extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int rid;
    private String name;
    private String rdesc;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    /*--------common prop------------*/
    private SysUser creatorUser;
    private String createDate;
    private SysUser modifierUser;
    private String lastModifyDate;

    @JsonIgnore
    public SysUser getCreatorUser() {
        return creatorUser;
    }

    public String getCreatorUserName() {
        if (creatorUser != null) {
            return creatorUser.getName();
        }
        return "";
    }

    public void setCreatorUser(SysUser creatorUser) {
        this.creatorUser = creatorUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @JsonIgnore
    public SysUser getModifierUser() {
        return modifierUser;
    }

    public String getModifierUserName() {
        if (modifierUser != null) {
            return modifierUser.getName();
        }
        return "";
    }

    public void setModifierUser(SysUser modifierUser) {
        this.modifierUser = modifierUser;
    }

    public String getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(String lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
}
