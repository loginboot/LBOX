package cn.ryan.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author ryan.cn
 * @creator xiesw
 * @version 1.0.0
 * @date 2020-01-14
 * @description 通用DB调用接口类
 *
 */
public interface BaseDao {

    // 通用分頁方法
    public <T> List<T> findByPage(Map<String, Object> search);

    // 保存信息
    public <T> void save(T t);

    // 更新一筆記錄
    public <T> void update(T t);

    // 根据ID返回一条记录
    public <T> T findOne(int id);

    // 返回所有記錄
    public <T> List<T> findAll();

    // 根據ID刪除一條記錄
    public <T> void delete(int id);
}
