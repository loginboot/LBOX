package cn.ryan.model.rb;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.ryan.model.AbstractEntity;
import cn.ryan.model.SysUser;

/**
 * 
 * @author cn.ryan
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-12-04
 * @description 彩票表
 *
 */
public class RbColorBall extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private RbRobotMode rmode;
    private String bdesc;
    private String rball;
    private int status;
    private int prizeStatus;
    private int prizeDate;
    private String rmk1;
    private String rmk2;
    private String rmk3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RbRobotMode getRmode() {
        return rmode;
    }

    public void setRmode(RbRobotMode rmode) {
        this.rmode = rmode;
    }

    public String getBdesc() {
        return bdesc;
    }

    public void setBdesc(String bdesc) {
        this.bdesc = bdesc;
    }

    public String getRball() {
        return rball;
    }

    public void setRball(String rball) {
        this.rball = rball;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrizeStatus() {
        return prizeStatus;
    }

    public void setPrizeStatus(int prizeStatus) {
        this.prizeStatus = prizeStatus;
    }

    public int getPrizeDate() {
        return prizeDate;
    }

    public void setPrizeDate(int prizeDate) {
        this.prizeDate = prizeDate;
    }

    public String getRmk1() {
        return rmk1;
    }

    public void setRmk1(String rmk1) {
        this.rmk1 = rmk1;
    }

    public String getRmk2() {
        return rmk2;
    }

    public void setRmk2(String rmk2) {
        this.rmk2 = rmk2;
    }

    public String getRmk3() {
        return rmk3;
    }

    public void setRmk3(String rmk3) {
        this.rmk3 = rmk3;
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
