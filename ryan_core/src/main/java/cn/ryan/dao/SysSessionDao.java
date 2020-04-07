package cn.ryan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.ryan.model.SysSession;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 系统在线记录调用接口类
 *
 */
@Mapper
public interface SysSessionDao {

    // 根據操作日期查詢過期的SESSION
    List<SysSession> findByLastOpTime(String optime);

    // 刪除已經過期的SESSION
    void deleteTimeoutSession(String lastOpTime);

    // 刪除在線記錄
    void delete(int userId);

    // 通過用戶查詢一條在線SESSION
    SysSession findOne(int userId);

    // 保存SESSION
    void saveOrUpdate(SysSession sysSession);
}
