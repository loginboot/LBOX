package cn.ryan.model;

import java.io.Serializable;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统用户在线表实体类
 *
 */
public class SysSession extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int userId;
    private String sessionId;
    private String lastOpTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLastOpTime() {
        return lastOpTime;
    }

    public void setLastOpTime(String lastOpTime) {
        this.lastOpTime = lastOpTime;
    }

}
