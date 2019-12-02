package cn.ryan.model;

import java.io.Serializable;

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

}
