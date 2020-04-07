package cn.ryan.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.ryan.model.SysUser;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统用户调用接口类
 *
 */
@Mapper
public interface SysUserDao extends BaseDao {

    public SysUser findUserByLoginName(String loginName);
}
