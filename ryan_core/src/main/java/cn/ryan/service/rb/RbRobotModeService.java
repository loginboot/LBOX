package cn.ryan.service.rb;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ryan.dao.rb.RbRobotDao;
import cn.ryan.model.rb.RbRobot;

@Service
public class RbRobotModeService {

    @Resource
    private RbRobotDao rbRobotDao;

    public List<RbRobot> findAllRobots() {
        return rbRobotDao.findAll();
    }
    
    
}
