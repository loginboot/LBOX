package cn.ryan.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统用户表实体类
 *
 */
public class SysUser extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;
    private int userId;
    private String name;
    private String loginName;
    private String salt;
    private String password;
    private int failTimes;
    private int initPwd;
    private int status;
    private String historyPwd;
    private Integer lockReason;
    private String pwdLastModifyDate;
    private String lastLoginDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailTimes() {
        return failTimes;
    }

    public void setFailTimes(int failTimes) {
        this.failTimes = failTimes;
    }

    public int getInitPwd() {
        return initPwd;
    }

    public void setInitPwd(int initPwd) {
        this.initPwd = initPwd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHistoryPwd() {
        return historyPwd;
    }

    public void setHistoryPwd(String historyPwd) {
        this.historyPwd = historyPwd;
    }

    public Integer getLockReason() {
        return lockReason;
    }

    public void setLockReason(Integer lockReason) {
        this.lockReason = lockReason;
    }

    public String getPwdLastModifyDate() {
        return pwdLastModifyDate;
    }

    public void setPwdLastModifyDate(String pwdLastModifyDate) {
        this.pwdLastModifyDate = pwdLastModifyDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
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
