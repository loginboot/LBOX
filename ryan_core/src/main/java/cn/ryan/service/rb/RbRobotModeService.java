package cn.ryan.service.rb;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cn.ryan.dao.rb.RbRobotDao;
import cn.ryan.dao.rb.RbRobotModeDao;
import cn.ryan.dao.rb.RbRobotParamsDao;
import cn.ryan.model.rb.RbRobot;
import cn.ryan.model.rb.RbRobotMode;
import cn.ryan.model.rb.RbRobotParams;

@Service
public class RbRobotModeService {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(RbRobotModeService.class);

    @Resource
    private RbRobotDao rbRobotDao;

    @Resource
    private RbRobotModeDao rbRobotModeDao;

    @Resource
    private RbRobotParamsDao rbRobotParamsDao;

    /**
     * 根据查询条件进行客户信息的分页查询
     * 
     * @param search
     * @return
     */
    public Page<RbRobotMode> findByPage(Map<String, Object> search) {
        // 分頁
        Integer page = (Integer) search.get("page");
        Integer pageSize = (Integer) search.get("pageSize");
        log.debug("***search RbRobotMode by Page for page:[" + page + "] and pageSize:[" + pageSize + "]***");
        List<RbRobotMode> lst = rbRobotModeDao.findByPage(search);
        Integer total = (Integer) search.get("TOTAL_SIZE");
        // 返回結果集
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<RbRobotMode> datas = new PageImpl<RbRobotMode>(lst, pageRequest, total);
        return datas;
    }

    public List<RbRobot> findAllRobots() {
        return rbRobotDao.findAll();
    }

    /**
     * 更新或保存數據庫
     * @param rrm
     * @param rrplst
     */
    public void saveOrUpdate(RbRobotMode rrm, List<RbRobotParams> rrplst) {
        //判断是否修改
        if (rrm.getRbid() == 0) {
            rbRobotModeDao.save(rrm);
        } else {
            // 先删除参数信息
            rbRobotParamsDao.deleteByRbId(rrm.getRbid());
            rbRobotModeDao.update(rrm);
        }

        // 统一添加关联信息
        if (rrplst != null) {
            for (RbRobotParams rrp : rrplst) {
                rrp.setRmode(rrm);
            }
            // 重新保存参数
            rbRobotParamsDao.savelist(rrplst);
        }
    }
}
