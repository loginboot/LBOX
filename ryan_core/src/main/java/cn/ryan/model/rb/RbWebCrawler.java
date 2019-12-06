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
 * @description 爬蟲存儲目錄表
 *
 */
public class RbWebCrawler extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private String fkey;
    private RbRobotMode rmode;
    private String pkey;
    private String path;
    private int level;

    public String getFkey() {
        return fkey;
    }

    public void setFkey(String fkey) {
        this.fkey = fkey;
    }

    public RbRobotMode getRmode() {
        return rmode;
    }

    public void setRmode(RbRobotMode rmode) {
        this.rmode = rmode;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
