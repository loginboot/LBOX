package cn.ryan.model;

import java.io.Serializable;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统用户扩展表实体类
 *
 */
public class SysUserExt extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int userId;
    private String labelName;
    private String fieldValue;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

}
