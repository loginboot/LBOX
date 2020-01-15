package cn.ryan.model;

import java.io.Serializable;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统日志表实体类
 *
 */
public class SysLog extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int lid;
    private SysUser user;
    private String operTime;
    private int type;
    private String host;
    private String permission;
    private String details;

    private Integer userId;

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 通过系统用户取得ID
     * @return
     */
    public Integer getUserId() {
        if (user != null) {
            userId = user.getUserId();
        }
        return userId;
    }

}
