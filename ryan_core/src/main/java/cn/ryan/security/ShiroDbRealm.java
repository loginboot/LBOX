package cn.ryan.security;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import cn.ryan.constant.RConstant;
import cn.ryan.model.SysUser;
import cn.ryan.service.SysAuthService;
import cn.ryan.utils.Encodes;
import cn.ryan.utils.RyanUtil;

public class ShiroDbRealm extends AuthorizingRealm {

    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(ShiroDbRealm.class);

    private SysAuthService sysAuthService;

    public void setSysAuthService(SysAuthService sysAuthService) {
        this.sysAuthService = sysAuthService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        RyanAuthToken authToken = (RyanAuthToken) token;
        log.info("doGetAuthenticationInfo--" + authToken.getUsername());
        SysUser user = null;
        try {
            user = sysAuthService.findUserByLoginName(authToken.getUsername());
        } catch (Exception e) {
            log.error("doGetAuthenticationInfo--exception,", e);
        }
        // 如果用户存在，则进行密码验证
        if (user != null) {
            try {
                log.info("Login user for loginName:" + user.getLoginName());
                // 密码验证
                byte[] salt = Encodes.decodeHex(RyanUtil.trim(user.getSalt()));
                String userPwd = user.getPassword();
                SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(user, userPwd, ByteSource.Util.bytes(salt),
                        getName());
                return sai;
            } catch (Exception e) {
                log.error("Login DB error:", e);
                throw new UnknownAccountException();
            }
        } else {
            throw new UnknownAccountException();
        }
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(RConstant.HASH_ALGORITHM);
        matcher.setHashIterations(RConstant.HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }
}
