package cn.ryan.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.ryan.model.SysMode;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统来源调用接口类
 *
 */
@Mapper
public interface SysModeDao {

    // 根据SYSCODE返回一笔模式对象
    public SysMode findOne(String sysCode);
}
