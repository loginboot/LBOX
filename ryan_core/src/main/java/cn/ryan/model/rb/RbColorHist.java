package cn.ryan.model.rb;

import java.io.Serializable;
import java.math.BigDecimal;

import cn.ryan.model.AbstractEntity;

/**
 * 
 * @author cn.ryan
 * @creator xiesw
 * @version 1.0.0
 * @date 2019-12-04
 * @description 彩票歷史數據表
 *
 */
public class RbColorHist extends AbstractEntity implements Serializable {

    /**
     * ID
     */
    private static final long serialVersionUID = 1L;

    private int id;
    private RbRobotMode rmode;
    private String issueNum;
    private String rball;
    private BigDecimal totalAml;
    private String prizeDate;
    private String rmk1;
    private String rmk2;
    private String rmk3;
    private String createDate;

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

    public String getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(String issueNum) {
        this.issueNum = issueNum;
    }

    public String getRball() {
        return rball;
    }

    public void setRball(String rball) {
        this.rball = rball;
    }

    public BigDecimal getTotalAml() {
        return totalAml;
    }

    public void setTotalAml(BigDecimal totalAml) {
        this.totalAml = totalAml;
    }

    public String getPrizeDate() {
        return prizeDate;
    }

    public void setPrizeDate(String prizeDate) {
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
