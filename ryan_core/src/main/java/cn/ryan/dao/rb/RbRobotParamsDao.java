package cn.ryan.dao.rb;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.ryan.model.rb.RbRobotParams;

@Mapper
public interface RbRobotParamsDao {

    // 根据主ID删除所有参数内容
    public void deleteByRbId(int rbid);

    // 保存机器参数详细信息
    public void savelist(@Param("rps") List<RbRobotParams> rps);
}
