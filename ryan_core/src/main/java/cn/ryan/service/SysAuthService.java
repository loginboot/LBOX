package cn.ryan.service;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.ryan.constant.RConstant;
import cn.ryan.constant.RStatus;
import cn.ryan.dao.SysLogDao;
import cn.ryan.dao.SysSessionDao;
import cn.ryan.dao.SysUserDao;
import cn.ryan.exception.RyanException;
import cn.ryan.model.SysSession;
import cn.ryan.model.SysUser;
import cn.ryan.utils.RyanUtil;

@Service
public class SysAuthService {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(SysAuthService.class);

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysSessionDao sysSessionDao;

    @Resource
    private SysLogDao sysLogDao;

    /**
     * 根据loginName获取用户信息
     * 
     * @param loginName
     * @return User, 无则返回null
     * @throws RyanException
     */
    public SysUser findUserByLoginName(String loginName) {
        SysUser user = sysUserDao.findUserByLoginName(loginName);
        return user;
    }

    /**
     * 更新用戶登錄失敗次數
     * 
     * @param uId
     * @throws LyodsException
     */
    public void updateFailureTimes(String loginName) {
        SysUser user = findUserByLoginName(loginName);
        int pwdFailMaxTimes = 5;

        user.setFailTimes(user.getFailTimes() + 1);
        if ((user.getFailTimes()) == pwdFailMaxTimes) {
            user.setStatus(RStatus.LOCK);
            user.setLockReason(RConstant.USER_PWD_LOCK);
        }
        sysUserDao.update(user);
    }

    /**
     * 用户登录成功
     * 
     * @param loginName
     */
    public void loginSuccessful(String loginName, String sessionId) {
        SysUser user = findUserByLoginName(loginName);
        if (user.getFailTimes() > 0) {
            user.setFailTimes(0);
        }
        // 更新用户最后登录日期
        user.setLastLoginDate(RyanUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        sysUserDao.update(user);
        // insert into AtSession
        SysSession as = sysSessionDao.findOne(user.getUserId());
        if (as == null) {
            as = new SysSession();
        }
        as.setUserId(user.getUserId());
        as.setLastOpTime(RyanUtil.timeStampToStr(new Date()));
        as.setSessionId(sessionId);
        sysSessionDao.saveOrUpdate(as);
    }

    /**
     * 用户退出
     * 
     * @param userId
     */
    public void logoutSuccessful(int userId) {
        sysSessionDao.delete(userId);
    }

}
