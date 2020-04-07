package cn.ryan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.ryan.model.SysUserMode;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统用户与来源调用接口类
 *
 */
@Mapper
public interface SysUserModeDao {

    // 根據用户ID刪除系统来源關聯表
    public void deleteByUserId(int userId);

    // 根據用戶ID查詢所有關聯系统来源
    public List<SysUserMode> findByUserId(int userId);

    // 保存或更新角色和用戶關聯信息
    public void savelist(List<SysUserMode> ums);

}
