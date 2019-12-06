package cn.ryan.model.rb;

import java.io.Serializable;

import cn.ryan.model.AbstractEntity;

/**
 * 
 * @author cn.ryan
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-12-04
 * @description 機器模型參數表
 *
 */
public class RbRobotParams extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int rpid;
    private RbRobotMode rmode;
    private String rpName;
    private String rpValue;
    private String rpDesc;

    public int getRpid() {
        return rpid;
    }

    public void setRpid(int rpid) {
        this.rpid = rpid;
    }

    public RbRobotMode getRmode() {
        return rmode;
    }

    public void setRmode(RbRobotMode rmode) {
        this.rmode = rmode;
    }

    public String getRpName() {
        return rpName;
    }

    public void setRpName(String rpName) {
        this.rpName = rpName;
    }

    public String getRpValue() {
        return rpValue;
    }

    public void setRpValue(String rpValue) {
        this.rpValue = rpValue;
    }

    public String getRpDesc() {
        return rpDesc;
    }

    public void setRpDesc(String rpDesc) {
        this.rpDesc = rpDesc;
    }

}
