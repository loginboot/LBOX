package cn.ryan.model.rb;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.ryan.model.AbstractEntity;
import cn.ryan.model.SysUser;
import cn.ryan.utils.RyanUtil;

/**
 * 
 * @author cn.ryan
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-12-04
 * @description 機器模型表
 *
 */
public class RbRobotMode extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int rbid;
    private RbRobot mode;
    private String mdesc;
    private int status;

    public int getRbid() {
        return rbid;
    }

    public void setRbid(int rbid) {
        this.rbid = rbid;
    }

    public RbRobot getMode() {
        return mode;
    }

    public void setMode(RbRobot mode) {
        this.mode = mode;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    private String modeName;

    public String getModeName() {
        if (RyanUtil.isEmpty(modeName) && mode != null) {
            return mode.getLabel();
        }
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    private String statusName;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
