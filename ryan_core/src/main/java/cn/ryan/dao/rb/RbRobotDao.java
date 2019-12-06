package cn.ryan.dao.rb;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.ryan.model.rb.RbRobot;

@Mapper
public interface RbRobotDao {

    // 返回一條機器類型
    public RbRobot findOne(String mode);

    // 返回所有機器類型
    public List<RbRobot> findAll();
}
