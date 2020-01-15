package cn.ryan.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.ryan.model.SysUser;

@Mapper
public interface SysUserDao extends BaseDao {

    public SysUser findUserByLoginName(String loginName);
}
