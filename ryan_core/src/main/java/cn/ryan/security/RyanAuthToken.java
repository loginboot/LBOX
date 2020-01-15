package cn.ryan.security;

import org.apache.shiro.authc.UsernamePasswordToken;

import cn.ryan.utils.JsonMapper;

public class RyanAuthToken extends UsernamePasswordToken {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    // Database登錄標識,默認為false,如果進行DB登錄則設置為true
    private boolean dbflag = false;

    public RyanAuthToken(String userName, String pwd) {
        super(userName, pwd);
    }

    public boolean isDbflag() {
        return dbflag;
    }

    public void setDbflag(boolean dbflag) {
        this.dbflag = dbflag;
    }

    @Override
    public String toString() {
        JsonMapper mapper = new JsonMapper();
        return mapper.toJson(this);
    }
}
